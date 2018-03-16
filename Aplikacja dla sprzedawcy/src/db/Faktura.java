/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.Date;


/**
 *
 * @author Przemek
 */

public class Faktura  {
    private Integer id;
    private String numer;
    private Date dataWystawienia;
    private Date dataZaplaty;
    private Date terminZaplaty;
    private String formaZaplaty;
    private String uwagi;
    private String notatki;
    private Integer idKlient;
    public Faktura() {
    }

    public Faktura(Integer id) {
        this.id = id;
    }

    public Faktura(Integer id, String numer, Date dataWystawienia, Date terminZaplaty, String formaZaplaty, Integer idKlient) {
        this.id = id;
        this.numer = numer;
        this.dataWystawienia = dataWystawienia;
        this.terminZaplaty = terminZaplaty;
        this.formaZaplaty = formaZaplaty;
        this.idKlient=idKlient;
    }
    
        public Faktura(String numer, Date dataWystawienia, Date terminZaplaty, String formaZaplaty, Integer idKlient) {
        this.numer = numer;
        this.dataWystawienia = dataWystawienia;
        this.terminZaplaty = terminZaplaty;
        this.formaZaplaty = formaZaplaty;
        this.idKlient=idKlient;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumer() {
        return numer;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    public Date getDataWystawienia() {
        return dataWystawienia;
    }

    public void setDataWystawienia(Date dataWystawienia) {
        this.dataWystawienia = dataWystawienia;
    }

    public Date getDataZaplaty() {
        return dataZaplaty;
    }

    public void setDataZaplaty(Date dataZaplaty) {
        this.dataZaplaty = dataZaplaty;
    }

    public Date getTerminZaplaty() {
        return terminZaplaty;
    }

    public void setTerminZaplaty(Date terminZaplaty) {
        this.terminZaplaty = terminZaplaty;
    }

    public String getFormaZaplaty() {
        return formaZaplaty;
    }

    public void setFormaZaplaty(String formaZaplaty) {
        this.formaZaplaty = formaZaplaty;
    }

    public String getUwagi() {
        return uwagi;
    }

    public void setUwagi(String uwagi) {
        this.uwagi = uwagi;
    }

    public String getNotatki() {
        return notatki;
    }

    public void setNotatki(String notatki) {
        this.notatki = notatki;
    }
    public Integer getIdKlient(){
        return idKlient;
    } 
    public void setIdKliet(Integer idKlient)
    {
        this.idKlient = idKlient;
                
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Faktura)) {
            return false;
        }
        Faktura other = (Faktura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    
    
}
