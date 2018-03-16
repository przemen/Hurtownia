package okno;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Drukowanie extends JFrame implements ActionListener, ItemListener {

    JButton print = new JButton("Drukuj"),
            cancel = new JButton("Anuluj");
    Pageable pg = null;
    double scale = 1.0;
     Druk druk;
    Page page[] = null;
    JComboBox jcb = new JComboBox();
    CardLayout cl = new CardLayout();
    JPanel p = new JPanel(cl);
    JButton back = new JButton("<<"), forward = new JButton(">>");

    public Drukowanie(Pageable pg) {
        super("Podgląd wydruku");
        this.pg = pg;
        createPreview();
    }

    public Drukowanie(final Printable pr, final PageFormat p, Druk druk) {
        super("Podgląd wydruku");
        this.druk = druk;
        this.pg = new Pageable() {
            @Override
            public int getNumberOfPages() {
                Graphics g = new java.awt.image.BufferedImage(2, 2, java.awt.image.BufferedImage.TYPE_INT_RGB).getGraphics();
                int n = 0;
                try {
                    while (pr.print(g, p, n) == Printable.PAGE_EXISTS) {
                        n++;
                    }
                } catch (Exception ex) {
                }
                return n;
            }

            @Override
            public PageFormat getPageFormat(int x) {
                return p;
            }

            @Override
            public Printable getPrintable(int x) {
                return pr;
            }
        };
        createPreview();
    }

    private void createPreview() {
        page = new Page[pg.getNumberOfPages()];
        FlowLayout fl = new FlowLayout();
        PageFormat pf = pg.getPageFormat(0);
        Dimension size = new Dimension((int) pf.getPaper().getWidth(), (int) pf.getPaper().getHeight());
        if (pf.getOrientation() != PageFormat.PORTRAIT) {
            size = new Dimension(size.height, size.width);
        }
        JPanel temp = null;
        for (int i = 0; i < page.length; i++) {
            jcb.addItem("" + (i + 1));
            page[i] = new Page(i, size);
            p.add("" + (i + 1), new JScrollPane(page[i]));
        }
        setTopPanel();
        this.getContentPane().add(p, "Center");
        Dimension d = this.getToolkit().getScreenSize();
        this.setSize(d.width, d.height - 60);
      
        this.setVisible(true);
        page[jcb.getSelectedIndex()].refreshScale();
    }

    private void setTopPanel() {
        FlowLayout fl = new FlowLayout();
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel topPanel = new JPanel(gbl), temp = new JPanel(fl);
    
        back.addActionListener(this);
        forward.addActionListener(this);
        back.setEnabled(false);
        forward.setEnabled(page.length > 1);
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        
        temp.add(back);
        temp.add(jcb);
        temp.add(forward);
        temp.add(cancel);
        temp.add(print);
       
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbl.setConstraints(temp, gbc);
        topPanel.add(temp);
        print.addActionListener(this);
         
        cancel.addActionListener(this);
        jcb.addItemListener(this);
  
        this.getContentPane().add(topPanel, "North");
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        cl.show(p, (String) jcb.getSelectedItem());
        page[jcb.getSelectedIndex()].refreshScale();
        back.setEnabled((jcb.getSelectedIndex() != 0));
        forward.setEnabled((jcb.getSelectedIndex() != jcb.getItemCount() - 1));
        this.validate();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object o = ae.getSource();
        if (o == print) {
            try {
                PrinterJob pj = PrinterJob.getPrinterJob();
                pj.defaultPage(pg.getPageFormat(0));
                pj.setPageable(pg);
                if (pj.printDialog()) {
                    pj.print();
                    
                   druk.blok();
                    this.dispose();
                }
                
                
            } catch (IndexOutOfBoundsException | NullPointerException | HeadlessException | PrinterException ex) {
                JOptionPane.showMessageDialog(null, "Wystąpił nie obsługiwany błąd drukowania", "Błąd drukowania ", 1);
            } catch (SQLException ex) {
                
            }
        
        } else if (o == back) {
            jcb.setSelectedIndex(jcb.getSelectedIndex() == 0 ? 0 : jcb.getSelectedIndex() - 1);
            if (jcb.getSelectedIndex() == 0) {
                back.setEnabled(false);
            }
        } else if (o == forward) {
            jcb.setSelectedIndex(jcb.getSelectedIndex() == jcb.getItemCount() - 1 ? 0 : jcb.getSelectedIndex() + 1);
            if (jcb.getSelectedIndex() == jcb.getItemCount() - 1) {
                forward.setEnabled(false);
            }
        } else if (o == cancel) {
            this.dispose();
        }
    }

    public void printCurrentPage() {
        try {
            PrinterJob pj = PrinterJob.getPrinterJob();
            pj.defaultPage(pg.getPageFormat(0));
            pj.setPrintable(new PsuedoPrintable());
            javax.print.attribute.HashPrintRequestAttributeSet pra
                    = new javax.print.attribute.HashPrintRequestAttributeSet();
            if (pj.printDialog(pra)) {
                pj.print(pra);
            }
        } catch (IndexOutOfBoundsException | HeadlessException | PrinterException ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error in Printing", 1);
        }
    }

   
    

    class Page extends JLabel {

        final int n;
        final PageFormat pf;
        java.awt.image.BufferedImage bi = null;
        Dimension size = null;

        public Page(int x, Dimension size) {
            this.size = size;
            bi = new java.awt.image.BufferedImage(size.width, size.height, java.awt.image.BufferedImage.TYPE_INT_RGB);
            n = x;
            pf = pg.getPageFormat(n);
            Graphics g = bi.getGraphics();
            Color c = g.getColor();
            g.setColor(Color.white);
            g.fillRect(0, 0, (int) pf.getWidth(), (int) pf.getHeight());
            g.setColor(c);
            try {
                g.clipRect(0, 0, (int) pf.getWidth(), (int) pf.getHeight());
                pg.getPrintable(n).print(g, pf, n);
            } catch (IndexOutOfBoundsException | PrinterException ex) {
            }
            this.setIcon(new ImageIcon(bi));
        }

        public void refreshScale() {
            if (scale != 1.0) {
                this.setIcon(new ImageIcon(bi.getScaledInstance((int) (size.width * scale), (int) (size.height * scale), BufferedImage.SCALE_FAST)));
            } else {
                this.setIcon(new ImageIcon(bi));
            }
            this.validate();
        }
    }

    class PsuedoPrintable implements Printable {

        @Override
        public int print(Graphics g, PageFormat fmt, int index) {
            if (index > 0) {
                return Printable.NO_SUCH_PAGE;
            }
            int n = jcb.getSelectedIndex();
            try {
                return pg.getPrintable(n).print(g, fmt, n);
            } catch (IndexOutOfBoundsException | PrinterException ex) {
            }
            return Printable.PAGE_EXISTS;
        }
    }
}

    
    