/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enity;

/**
 *
 * @author Przemek
 */
public class pozycjaPZ
{
    private String id;
    private String idTowar;
    private String jm;
    private String cenaZ;
    private String wartoscc;
    private String nazwa;
    private String ilosc;

    public pozycjaPZ(String idTowar, String jm, String cenaZ, String wartoscc) {
        this.idTowar = idTowar;
        this.jm = jm;
        this.cenaZ = cenaZ;
        this.wartoscc = wartoscc;
    }

    public pozycjaPZ(String id, String idTowar, String jm, String cenaZ, String wartoscc) {
        this.id = id;
        this.idTowar = idTowar;
        this.jm = jm;
        this.cenaZ = cenaZ;
        this.wartoscc = wartoscc;
    }

    public pozycjaPZ() {
    }

    public String getIlosc() {
        return ilosc;
    }

    public void setIlosc(String ilosc) {
        this.ilosc = ilosc;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdTowar() {
        return idTowar;
    }

    public void setIdTowar(String idTowar) {
        this.idTowar = idTowar;
    }

    public String getJm() {
        return jm;
    }

    public void setJm(String jm) {
        this.jm = jm;
    }

    public String getCenaZ() {
        return cenaZ;
    }

    public void setCenaZ(String cenaZ) {
        this.cenaZ = cenaZ;
    }

    public String getWartoscc() {
        return wartoscc;
    }

    public void setWartoscc(String wartoscc) {
        this.wartoscc = wartoscc;
    }
    
}
