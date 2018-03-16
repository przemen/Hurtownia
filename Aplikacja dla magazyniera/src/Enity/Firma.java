package Enity;

import java.io.Serializable;

/**
 *
 * @author Przemek
 */

public class Firma implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nazwa;
    private String nip;
    private String nrBudynku;
    private String nrLokalu;
    private String ulica;
    private String kodPocztowy;
    private String miejscowosc;
    private String kod;

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }
    
    
    private String notatka;
    
    public Firma() {
    }

    public Firma(Integer id) {
        this.id = id;
    }

    public Firma(String nazwa, String nip, String nrBudynku,String nrLokalu, String ulica, String kodPocztowy, String miejscowosc, String kod, String notatka) {
        
        this.nazwa = nazwa;
        this.nip = nip;
        this.nrBudynku = nrBudynku;
        this.nrLokalu= nrLokalu;
        this.ulica = ulica;
        this.kodPocztowy = kodPocztowy;
        this.miejscowosc = miejscowosc;
        this.kod=kod;
        this.notatka=notatka;
    }
        public Firma(String nazwa, String nip, String nrBudynku,String nrLokalu, String ulica, String kodPocztowy, String miejscowosc) {
      
        this.nazwa = nazwa;
        this.nip = nip;
        this.nrBudynku = nrBudynku;
        this.nrLokalu=nrLokalu;
        this.ulica = ulica;
        this.kodPocztowy = kodPocztowy;
        this.miejscowosc = miejscowosc;
    }

    public String getNotatka() {
        return notatka;
    }

    public void setNotatka(String notatka) {
        this.notatka = notatka;
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

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNrBudynku() {
        return nrBudynku;
    }

    public void setNrBudynku(String nrBudynku) {
        this.nrBudynku = nrBudynku;
    }

    public String getNrLokalu() {
        return nrLokalu;
    }

    public void setNrLokalu(String nrLokalu) {
        this.nrLokalu = nrLokalu;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
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
        if (!(object instanceof Firma)) {
            return false;
        }
        Firma other = (Firma) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString(){
    
    return nazwa;
    }


  
    
 
    
}
