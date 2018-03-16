/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Przemek
 */
@MappedSuperclass
@Table(name = "Cennik")
public class Cennik implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
  
    
    private Integer idTowar;
    private BigDecimal cena;
    private Date dataPoczatkowa;
    private Date dataKoncowa;
    private int typ;

    public Cennik() {
    }
    public Cennik(Integer id, Integer idTowar, BigDecimal cena, Date dataPoczatkowa, int typ) {
        this.id = id;
        this.idTowar=idTowar;
        this.cena = cena;
        this.dataPoczatkowa = dataPoczatkowa;
        this.typ = typ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public Date getDataPoczatkowa() {
        return dataPoczatkowa;
    }

    public void setDataPoczatkowa(Date dataPoczatkowa) {
        this.dataPoczatkowa = dataPoczatkowa;
    }

    public Date getDataKoncowa() {
        return dataKoncowa;
    }

    public void setDataKoncowa(Date dataKoncowa) {
        this.dataKoncowa = dataKoncowa;
    }

    public int getTyp() {
        return typ;
    }

    public void setTyp(int typ) {
        this.typ = typ;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        Cennik other = (Cennik) object;
        if (!(object instanceof Cennik)) {
            return false;
        }
        else if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        else return true;
    }

    @Override
    public String toString() {
        return "db.Cennik[ id=" + id + " ]";
    }
    public void zmienCene(Double c){}
}
