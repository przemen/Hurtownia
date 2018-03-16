package db;

import java.util.Date;


/**
 *
 * @author Przemek
 */

public class Klient  {
    private Integer id;
    private Integer idFirmy;
    private Date dataDodania;
    private String notatka;

    public Klient() {
    }

    public Klient(int id) {
        this.id = id;
    }
   
    public Klient(int id,int idFirmy, Date dataDodania) {
        this.id=id;
        this.idFirmy = idFirmy;
        this.dataDodania = dataDodania;
    }
    
    public Klient(int idFirmy, Date dataDodania) {
        this.idFirmy = idFirmy;
        this.dataDodania = dataDodania;
    }
    
       
  

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataDodania() {
        return dataDodania;
    }

    public void setDataDodania(Date dataDodania) {
        this.dataDodania = dataDodania;
    }

    public String getNotatka() {
        return notatka;
    }

    public void setNotatka(String notatka) {
        this.notatka = notatka;
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
        if (!(object instanceof Klient)) {
            return false;
        }
        Klient other = (Klient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

   
    
}
