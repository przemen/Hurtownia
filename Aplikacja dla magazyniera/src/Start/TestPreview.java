package Start;
import javax.swing.*;
import java.awt.event.*;
import java.awt.print.*;
import java.text.MessageFormat;

/**
 *
 * @author Przemek
 */
public class  TestPreview extends JFrame  {

    PrinterJob pj = PrinterJob.getPrinterJob();
    javax.print.attribute.HashPrintRequestAttributeSet att
            = new javax.print.attribute.HashPrintRequestAttributeSet();
    JEditorPane tp = null;
    JTable tab = null;
    String html;
    Druk druk;

    

    public TestPreview(String html, Druk druk) {
   
        
        this.html=html;
        this.druk = druk;
        JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        sp.setTopComponent(createTextPane());
        java.awt.Dimension d = this.getToolkit().getScreenSize();

        this.getContentPane().add(sp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);

        sp.setDividerLocation(0.5);
        this.validate();
        
        PrintPreview printPreview;
        printPreview = new PrintPreview(tp.getPrintable(null,
                new MessageFormat("{0}")), pj.getPageFormat(att), druk);
        
        
        
    }
    
    

    private JPanel createTextPane() {
        tp = new JEditorPane("text/html", "");
        tp.setEditable(false);
        try {
            tp.setText(html);
        } catch (Exception ex) {
        }
    
        JPanel p = new JPanel(new java.awt.BorderLayout()), top = new JPanel(new java.awt.FlowLayout());
        
        
        p.add(new JScrollPane(tp), "Center");
        return p;
    }

   

}
