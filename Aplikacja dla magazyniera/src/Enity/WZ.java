/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enity;

import java.util.Date;

/**
 *
 * @author Przemek
 */
public class WZ {

    private String id;
    private String idKlient;
    private String klientNazwa;
    private String klientNIP;
    private String numer;
    private Date dataWystawienia;
    private String Notatka;
    private String uwagi;
    private double kwota;
    private int stan;
    private String nrFaktura="";
    public WZ(Date dataWystawienia, double kwota) {
        this.dataWystawienia = dataWystawienia;
        this.kwota = kwota;
    }

    public WZ() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdDostawca() {
        return idKlient;
    }

    public void setIdDostawca(String idDostawca) {
        this.idKlient = idDostawca;
    }

    public String getDostawcaNazwa() {
        return klientNazwa;
    }

    public void setDostawcaNazwa(String dostawcaNazwa) {
        this.klientNazwa = dostawcaNazwa;
    }

    public String getDostawcaNIP() {
        return klientNIP;
    }

    public void setDostawcaNIP(String dostawcaNIP) {
        this.klientNIP = dostawcaNIP;
    }

    public Date getDataWystawienia() {
        return dataWystawienia;
    }

    public void setDataWystawienia(Date dataWystawienia) {
        this.dataWystawienia = dataWystawienia;
    }

    public double getKwota() {
        return kwota;
    }

    public void setKwota(double kwota) {
        this.kwota = kwota;
    }

    public String getNumer() {
        return numer;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    public int getStan() {
        return stan;
    }

    public void setStan(int stan) {
        this.stan = stan;
    }

    public String getNotatka() {
        return Notatka;
    }

    public void setNotatka(String Notatka) {
        this.Notatka = Notatka;
    }

    public String getUwagi() {
        return uwagi;
    }

    public void setUwagi(String uwagi) {
        this.uwagi = uwagi;
    }

    public String getNrFaktura() {
        return nrFaktura;
    }

    public void setNrFaktura(String nrFaktura) {
        this.nrFaktura = nrFaktura;
    }

    

}
