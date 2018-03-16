package okno;
import javax.swing.*;
 
import java.awt.print.*;
import java.text.MessageFormat;

/**
 *
 * @author Przemek
 */
public class  PodgladDruk extends JFrame  {

    PrinterJob pj = PrinterJob.getPrinterJob();
    javax.print.attribute.HashPrintRequestAttributeSet att
            = new javax.print.attribute.HashPrintRequestAttributeSet();
    JEditorPane panel = null;
 
    String html;
    Druk druk;

    

    public PodgladDruk(String html, Druk druk) {
   
        
        this.html=html;
        this.druk = druk;
        JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        sp.setTopComponent(createTextPane());
       

        this.getContentPane().add(sp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);

        sp.setDividerLocation(0.5);
        this.validate();
        
        Drukowanie printPreview;
        printPreview = new Drukowanie(panel.getPrintable(null,
                new MessageFormat("{0}")), pj.getPageFormat(att), druk);
        
        
        
    }
    
    

    private JPanel createTextPane() {
         
        panel = new JEditorPane("text/html", "");
        try {
            panel.setText(html);
        } catch (Exception ex) {
        }
        JPanel p = new JPanel(new java.awt.BorderLayout()), top = new JPanel(new java.awt.FlowLayout());
        p.add(new JScrollPane(panel), "Center");
        return p;
    }

   

}
