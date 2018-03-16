/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Start;

import Enity.Connector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

/**
 *
 * @author Przemek
 */
public class Druk {

    public String html;
    public int rodzaj; // 1 - faktura, 
    public String id;
    public Connector con;

    public Druk(Connector con, int rodzaj, String id) throws SQLException {
        this.con = con;

        if (rodzaj == 1) {
            DrukFaktura(id);
        }
    }

    public void DrukFaktura(String id) throws SQLException {
        String query = null;
        query = "SELECT * FROM Faktura WHERE Id = " + id;
        ResultSet rs = con.querySelect(query);
        String KlientNazwa = null;
        String KlientNIP = null;
        String KlientAdres = null;
        String WartoscBrutto;
        String Datawystawienia = null;
        String TerminPlatnosci = null;
        String numer = null;
        String formaPl = null;
        String Uwagi;
        String idF = null;
        String Fsuma = null;
        DecimalFormat df = new DecimalFormat("#,###.00");
        
        while (rs.next()) {
            idF = rs.getString(1);
            numer = rs.getString(2);
            Datawystawienia = rs.getString(3);
            TerminPlatnosci = rs.getString(4);
            formaPl = rs.getString(5);
            Uwagi = rs.getString(6);
            KlientNazwa = rs.getString(9);
            KlientNIP = rs.getString(10);
            KlientAdres = rs.getString(11);
            Fsuma = rs.getString(12);
           
        }

        html = "<html><body><h2>FAKTURA VAT nr " + numer + "</h2><br/>";
        html += "<div style=\"font-size:8px;\">";
        html += "Miejsce wystawienia : Miejscowość<br/> ";
        html += "Data wystawienia: " + Datawystawienia + "<br/>";
        html += "Termin płatności: " + TerminPlatnosci + "<br/>";
        html += "Forma płatności: " + formaPl + "<br/>";
        html += "</div>";
        html += "<hr />";
        html = html + "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:100%\">\n"
                + "	<tbody >\n"
                + "		<tr>\n"
                + "			<td style=\"width:50%\">\n"
                + "			<h3><strong><span style=\"font-size:10px;\">Nabywca</span></strong></h3>\n"
                + "			</td>\n"
                + "			<td>&nbsp;</td>\n"
                + "			<td style=\"width:50%\">\n"
                + "			<h3><strong><span style=\"font-size:10px;\">Sprzedawca</span></strong></h3>\n"
                + "			</td>\n"
                + "		</tr>\n"
                + "		<tr>\n"
                + "			<td>\n"
                + "			<p><span style=\"font-size:8px;\">Nazwa: " + KlientNazwa + "</span></p>\n"
                + "\n"
                + "			<p><span style=\"font-size:8px;\">NIP: " + KlientNIP + "</span></p>\n"
                + "\n"
                + "			<p><span style=\"font-size:8px;\">Adres: " + KlientAdres + "</span></p>\n"
                + "			</td>\n"
                + "			<td>&nbsp;</td>\n"
                + "			<td>\n"
                + "			<p><span style=\"font-size:8px;\">Nazwa</span></p>\n"
                + "\n"
                + "			<p><span style=\"font-size:8px;\">NIP</span></p>\n"
                + "\n"
                + "			<p><span style=\"font-size:8px;\">Adres</span></p>\n"
                + "			</td>\n"
                + "		</tr>\n"
                + "		<tr>\n"
                + "			<td>&nbsp;</td>\n"
                + "			<td>&nbsp;</td>\n"
                + "			<td>&nbsp;</td>\n"
                + "		</tr>\n"
                + "	</tbody>\n"
                + "</table>"
                + "<br/>";

        html = html + "<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width: 100%;  font-size : 8;\">\n"
                + "	<thead style = \"background-color: #eeeeee;\">"
                + "		<tr>"
                + "			<th style=\"width: 6%; font-size 6;\">lp</th>"
                + "			<th style=\"width: 40%;\">Nazwa towaru</th>"
                + "			<th style=\"width: 12%;\">Cena Netto [zł]</th>"
                + "			<th style=\"width: 8%;\">Ilosc</th>"
                + "			<th style=\"width: 12%;\">JM</th>"
                + "			<th style=\"width: 12%;\">Wartosc Netto [zł]</th>"
                + "			<th style=\"width: 6%;\">VAT</th>"
                + "			<th style=\"width: 12%;\">Wartosc Vat</th>"
                + "			<th scope=\"col\">Wartosc Brutto</th>"
                + "		</tr>"
                + "	</thead>"
                + "	<tbody>";

        query = "SELECT * FROM PozycjaFaktury INNER JOIN TOWAR ON Towar = Towar.ID  WHERE IdFaktura = " + idF;
       
        int lp = 1;

        Double[] stawki = new Double[4];
        Double wnet23 = 00.0;
        Double wbru23 = 00.0;

        Double wnet8 = 00.0;
        Double wbru8 = 00.0;

        Double wnet5 = 00.0;
        Double wbru5 = 00.0;

        Double wnet0 = 00.0;
        Double wbru0 = 00.0;
        
        String[] stawSTR = {"23%", "8%", "5%", "0%"};
        rs = con.querySelect(query);
        while (rs.next()) {

            String ilosc = rs.getString(4);
            String cNetto = rs.getString(5);
            String VAT = rs.getString(6);

            Double il, cN, V;

            il = Double.parseDouble(ilosc);
            cN = Double.parseDouble(cNetto);
            V = Double.parseDouble(VAT);

           
            Double wNetto = il * cN;
            Double wVAT = (il * V * cN) / 100;
            Double wBrutto = wNetto + wVAT;

 
            if (V==23.0) {
                wnet23+=wNetto;
                wbru23+=wBrutto;
            } else if (V==8.0) {
                wnet8+=wNetto;
                wbru8+=wBrutto;
            } else if (V==5.0) {
                wnet5+=wNetto;
                wbru5+=wBrutto;
            } else if (V==0.0) {
                wnet0+=wNetto;
                wbru0+=wBrutto;
            }
            
            

            html = html
                    + "		<tr>\n"
                    + "			<td style=\"text-align: right;\">" + lp + "&nbsp; </td>"
                    + "			<td style=\" \">" + rs.getString(11) + "&nbsp; </td>"
                    + "			<td style=\"text-align: right;\">" + df.format(Double.parseDouble(rs.getString(5))) + "&nbsp; </td>"
                    + "			<td style=\"text-align: right;\">" + rs.getString(4) + "&nbsp; </td>"
                    + "			<td style=\"  \">" + rs.getString(8) + "&nbsp; </td>"
                    + "			<td style=\"text-align: right;\">" + df.format(wNetto) + " &nbsp;</td>"
                    + "			<td style=\"text-align: right;\">" + rs.getString(6) + "&nbsp; </td>"
                    + "			<td style=\"text-align: right;\">" + df.format(wVAT) + " &nbsp;</td>"
                    + "			<td style=\"text-align: right;\">" + df.format(wBrutto) + "&nbsp; </td>"
                    + "		</tr>\n";

            lp++;

        }
        Double razem = wbru0+wbru23+wbru5+wbru8;
        html = html + "</tbody></table>  <br/> <br/>"
                + "<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width: 150px; font-size : 8; \">\n" +
"	<tbody>\n" +
"		<tr style = \"background-color: #eeeeee;\">\n" +
"			<td>Stawka VAT</td>\n" +
"			<td>Wartosć netto</td>\n" +
"			<td>Wartość brutto</td>\n" +
"		</tr>\n";
        
        if(wnet23!=0.0){ html+=
"		<tr>\n" +
"			<td>23%;</td>\n" +
"			<td>"+df.format(wnet23)+" zł</td>\n" +
"			<td>"+df.format(wbru23)+" zł</td>\n" +
"		</tr>\n";
        }
        if(wnet8!=0.0){ html+=
"		<tr>\n" +
"			<td>8%</td>\n" +
"			<td>"+df.format(wnet8)+" zł</td>\n" +
"			<td>"+df.format(wbru8)+" zł</td>\n" +
"		</tr>\n";
        }
        if(wnet5!=0.0){ html+=

"		<tr>\n"+
"			<td>5%</td>\n" +
"			<td>"+df.format(wnet5)+" zł</td>\n" +
"			<td>"+df.format(wbru5)+" zł</td>\n" +
"		</tr>\n";

        }
        if(wnet0!=0.0){ html+=
  "		<tr>\n"+
"			<td>0%</td>\n" +
"			<td>"+df.format(wnet0)+" zł</td>\n" +
"			<td>"+df.format(wbru0)+" zł</td>\n" +
"		</tr>\n";
        }
html+=
"	</tbody>\n" +
"</table>"
                
                + "<br/>"
                +"<h4> Do zapłaty "+df.format(razem)+"zł</h4>"
                + "</body></html>";

      

        TestPreview tp = new TestPreview(html, this);
        
        

    }
    public void blok(){
    String query = "UPDATE Faktura SET editable = 0 WHERE Id =" +id;
                     con.query(query);
    }

}
