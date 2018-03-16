package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Przemek
 */
public final class wystawWZ {

    private Connector con;
    private String id;
    private String queryPz = "";
    private String querypFV = "";
    private ArrayList<Faktura_pozycja> listaPozycjiF;

    public wystawWZ(Connector con, String id) throws SQLException {
        this.con = con;
        this.id = id;
 if(czyNieZatwierdzonaF()){
        pobierzFp();

        liczenie();

        String query = "";
        String poczTrans = " BEGIN  TRANSACTION ";
        String koniTrans = " IF @@ERROR <> 0 "
                + "            BEGIN "
                + "                        ROLLBACK TRANSACTION "
                + "            END "
                + " COMMIT TRANSACTION"
                + "  GO ";

        query += poczTrans;
        query += " UPDATE Faktura SET editable = 0 WHERE Id =" + id;
        query += queryPz;
        query += querypFV;
        query += koniTrans;
        System.out.println(query);
        con.query(query);
        con.reconnect();
 } 
    }

    public boolean czyNieZatwierdzonaF() throws SQLException {
        String query = "SELECT * FROM Faktura  WHERE Id =" + id;
        int wartosc = 0;
        ResultSet rs = con.querySelect(query);
                while (rs.next()){
                    wartosc = rs.getInt(13);
                }
                if(wartosc == 1)
      return true;
                else return false;
    }

    public Connector getCon() {
        return con;
    }

    public void setCon(Connector con) {
        this.con = con;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private void liczenie() {
        listaPozycjiF.stream().forEach((Faktura_pozycja listaPozycjiF1) -> {
            double wartoscWydaniaF = 0;
            double iloscDoWydania = listaPozycjiF1.ilosc;
            double cenaWydania = 0;
            ArrayList<PZ_pozycjaTowar> lista1 = pobierzTowarPZ(listaPozycjiF1.idTowar);
            for (PZ_pozycjaTowar lista11 : lista1) {
                double iloscZPz = lista11.iloscPozostala;
                if (iloscZPz < iloscDoWydania) {
                    lista11.iloscPozostala = 0;
                    iloscDoWydania -= iloscZPz;
                    wartoscWydaniaF += iloscZPz * lista11.cenaZakupu;
                } else {
                    lista11.iloscPozostala -= iloscDoWydania;
                    wartoscWydaniaF += iloscDoWydania * lista11.cenaZakupu;
                    iloscDoWydania = 0;
                }
                queryPz += " UPDATE PozycjaDostawy SET IloscPozostala =  " + lista11.iloscPozostala + " WHERE Id = " + lista11.id;
            }
            cenaWydania = wartoscWydaniaF / listaPozycjiF1.ilosc;
            querypFV += " UPDATE PozycjaFaktury SET CenaWydanie = " + cenaWydania + " WHERE Id = " + listaPozycjiF1.idPozycjaFaktury;
        });
    }

    private ArrayList<PZ_pozycjaTowar> pobierzTowarPZ(String idTowar) {

        ArrayList<PZ_pozycjaTowar> lista = new ArrayList();

        String query = "DECLARE @idTowar int = " + idTowar
                + " SELECT * FROM Dostawa "
                + " INNER JOIN PozycjaDostawy ON "
                + " Dostawa.id = PozycjaDostawy.IdDostawa "
                + " WHERE (PozycjaDostawy.Towar = @idTowar) AND (PozycjaDostawy.IloscPozostala >0) "
                + " AND (Dostawa.stan =1) "
                + " ORDER BY Dostawa.Data ASC";

        ResultSet rs = con.querySelect(query);

        PZ_pozycjaTowar pzt = null;
        try {
            while (rs.next()) {
                pzt = new PZ_pozycjaTowar();
                pzt.cenaZakupu = rs.getDouble(10);
                pzt.id = rs.getString(7);
                pzt.idTowar = rs.getString(8);
                pzt.iloscPozostala = rs.getDouble(13);

                lista.add(pzt);
            }
        } catch (SQLException ex) {

        }

        return lista;
    }

    private void pobierzFp() throws SQLException {

        String query = "SELECT PozycjaFaktury.Id, PozycjaFaktury.Towar,"
                + " PozycjaFaktury.ilosc FROM Faktura  "
                + " INNER JOIN PozycjaFaktury ON "
                + " Faktura.id = PozycjaFaktury.IdFaktura "
                + " WHERE Faktura.id = " + id;
        ResultSet rs = con.querySelect(query);
        listaPozycjiF = new ArrayList();

        Faktura_pozycja pf = null;
        while (rs.next()) {
            pf = new Faktura_pozycja();
            pf.idPozycjaFaktury = rs.getString(1);
            pf.idTowar = rs.getString(2);
            pf.ilosc = rs.getDouble(3);
            listaPozycjiF.add(pf);
        }
    }

    class PZ_pozycjaTowar {

        protected String id;
        protected String idTowar;
        protected double iloscPozostala;
        protected double cenaZakupu;

        public PZ_pozycjaTowar() {
        }
    }

    class Faktura_pozycja {

        protected String idPozycjaFaktury;
        protected String idTowar;
        protected double ilosc;
        protected double cenaWydanie;

        public Faktura_pozycja() {
        }
    }
}
