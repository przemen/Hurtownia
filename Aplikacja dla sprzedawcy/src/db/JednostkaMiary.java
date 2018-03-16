/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Przemek
 */
@Entity
@Table(name = "JednostkaMiary")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JednostkaMiary.findAll", query = "SELECT j FROM JednostkaMiary j")})
public class JednostkaMiary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Nazwa")
    private String nazwa;
    @Column(name = "skrot")
    private String skrot;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jednostkaMiary")
    private Set<Towar> towarSet;

    public JednostkaMiary() {
    }

    public JednostkaMiary(Integer id) {
        this.id = id;
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

    public String getSkrot() {
        return skrot;
    }

    public void setSkrot(String skrot) {
        this.skrot = skrot;
    }

    @XmlTransient
    public Set<Towar> getTowarSet() {
        return towarSet;
    }

    public void setTowarSet(Set<Towar> towarSet) {
        this.towarSet = towarSet;
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
        if (!(object instanceof JednostkaMiary)) {
            return false;
        }
        JednostkaMiary other = (JednostkaMiary) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nazwa+" ["+skrot+"]";
    }
    
}
