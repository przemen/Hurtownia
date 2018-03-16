/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Przemek
 */
public class CennikIndywidualnyDB {

    private Connector con;

    public CennikIndywidualnyDB(Connector con) {
        this.con = con;
    }

    public ArrayList<CennikIndywidualny> selectCennikIndywidualny() throws SQLException {
        ArrayList listaCennikIndywidualny = new ArrayList();
        CennikIndywidualny CennikIndywidualny;
        String query;
        query = "SELECT * FROM CennikIndywidualny";
        ResultSet rs = con.querySelect(query);

        while (rs.next()) {
            CennikIndywidualny = new CennikIndywidualny();
            CennikIndywidualny.setId(rs.getInt(1));
            CennikIndywidualny.setCena(rs.getBigDecimal(2));
            CennikIndywidualny.setDataPoczatkowa(rs.getDate(3));
            CennikIndywidualny.setDataKoncowa(rs.getDate(4));
            listaCennikIndywidualny.add(CennikIndywidualny);
        }
        return listaCennikIndywidualny;
    }

    public ArrayList<Towar> selectCennikIndywidualnyID(String id) throws SQLException {
        ArrayList listaCennikIndywidualny = new ArrayList();
        CennikIndywidualny CennikIndywidualny;
        String query;
        query = "   SELECT * FROM TOWAR "
                + " INNER JOIN  CennikIndywidualny ON Towar.Id = CennikIndywidualny.IdTowar "
                + " WHERE CennikIndywidualny.IdKlient =  " + id;
        ResultSet rs = con.querySelect(query);

        while (rs.next()) {
            CennikIndywidualny = new CennikIndywidualny();
            CennikIndywidualny.setId(rs.getInt(1));
            CennikIndywidualny.setCena(rs.getBigDecimal(2));
            CennikIndywidualny.setDataPoczatkowa(rs.getDate(3));
            CennikIndywidualny.setDataKoncowa(rs.getDate(4));
            listaCennikIndywidualny.add(CennikIndywidualny);
        }
        return listaCennikIndywidualny;
    }

}
