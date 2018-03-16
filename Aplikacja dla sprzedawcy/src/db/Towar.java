/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Przemek
 */
@Entity
@Table(name = "Towar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Towar.findAll", query = "SELECT t FROM Towar t")})
public class Towar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Nazwa")
    private String nazwa;
    @Column(name = "Opis")
    private String opis;
    @Basic(optional = false)
    @Column(name = "KodTowaru")
    private String kodTowaru;
    @Column(name = "KodKreskowy")
    private String kodKreskowy;
    @Basic(optional = false)
    @Column(name = "PKWiU")
    private String pKWiU;
    @JoinColumn(name = "JednostkaMiary", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private String jednostkaMiary;
    
    private String kategoria;
    private String ilosc;
    private String dostepne;
    private String VAT;
    private String Cena;

    public Towar() {
    }

    public Towar(Integer id) {
        this.id = id;
    }

    public Towar(Integer id, String nazwa, String kodTowaru, String pKWiU) {
        this.id = id;
        this.nazwa = nazwa;
        this.kodTowaru = kodTowaru;
        this.pKWiU = pKWiU;
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

    public String getKodTowaru() {
        return kodTowaru;
    }

    public void setKodTowaru(String kodTowaru) {
        this.kodTowaru = kodTowaru;
    }

    public String getKodKreskowy() {
        return kodKreskowy;
    }

    public void setKodKreskowy(String kodKreskowy) {
        this.kodKreskowy = kodKreskowy;
    }

    public String getPKWiU() {
        return pKWiU;
    }

    public void setPKWiU(String pKWiU) {
        this.pKWiU = pKWiU;
    }

    public String getJednostkaMiary() {
        return jednostkaMiary;
    }

    public String getCena() {
        return Cena;
    }

    public void setCena(String Cena) {
        this.Cena = Cena;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public String getVAT() {
        return VAT;
    }

    public void setpKWiU(String pKWiU) {
        this.pKWiU = pKWiU;
    }

    public void setVAT(String VAT) {
        this.VAT = VAT;
    }

    public String getIlosc() {
        return ilosc;
    }

    public void setIlosc(String ilosc) {
        this.ilosc = ilosc;
    }

    public String getDostepne() {
        return dostepne;
    }

    public void setDostepne(String dostepne) {
        this.dostepne = dostepne;
    }
    
    
    

    public void setJednostkaMiary(String jednostkaMiary) {
        this.jednostkaMiary = jednostkaMiary;
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
        if (!(object instanceof Towar)) {
            return false;
        }
        Towar other = (Towar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Towar[ id=" + id + " ]";
    }
    
}
