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
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Przemek
 */

public class CennikIndywidualny implements Serializable {
    private Integer id;
    private BigDecimal cena;
    private Date dataPoczatkowa;
    @Temporal(TemporalType.DATE)
    private Date dataKoncowa;

    public CennikIndywidualny() {
    }

    public CennikIndywidualny(Integer id) {
        this.id = id;
    }

    public CennikIndywidualny(Integer id, BigDecimal cena, Date dataPoczatkowa) {
        this.id = id;
        this.cena = cena;
        this.dataPoczatkowa = dataPoczatkowa;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CennikIndywidualny)) {
            return false;
        }
        CennikIndywidualny other = (CennikIndywidualny) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.CennikIndywidualny[ id=" + id + " ]";
    }
    
}
