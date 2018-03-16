/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Lob;


/**
 *
 * @author Przemek
 */

public class Magazyn  {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Nazwa")
    private String nazwa;
    @Lob
    @Column(name = "Opis")
    private String opis;
    @Basic(optional = false)
    @Column(name = "Sktot")
    private String sktot;

    public Magazyn() {
    }

    public Magazyn(Integer id) {
        this.id = id;
    }

    public Magazyn(Integer id, String nazwa, String sktot) {
        this.id = id;
        this.nazwa = nazwa;
        this.sktot = sktot;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getSktot() {
        return sktot;
    }

    public void setSktot(String sktot) {
        this.sktot = sktot;
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
        if (!(object instanceof Magazyn)) {
            return false;
        }
        Magazyn other = (Magazyn) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Magazyn[ id=" + id + " ]";
    }
    
}
