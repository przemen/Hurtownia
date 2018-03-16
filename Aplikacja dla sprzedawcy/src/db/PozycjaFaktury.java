package db;

import java.io.Serializable;
import java.sql.ResultSet;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

/**
 *
 * @autor Przemyslaw Manka
 */
@MappedSuperclass
@Table(name = "PozycjaFaktury")
public class PozycjaFaktury implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    public Integer id;
    @Basic(optional = false)
    @Column(name = "ilosc")
    public String ilosc;

    public PozycjaFaktury() {
    }

    public PozycjaFaktury(Integer id) {
        this.id = id;
    }

    public PozycjaFaktury(Integer id, String ilosc) {
        this.id = id;
        this.ilosc = ilosc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIlosc() {
        return ilosc;
    }

    public void setIlosc(String ilosc) {
        this.ilosc = ilosc;
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
        if (!(object instanceof PozycjaFaktury)) {
            return false;
        }
        PozycjaFaktury other = (PozycjaFaktury) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.PozycjaFaktury[ id=" + id + " ]";
    }
    
 
    
}
