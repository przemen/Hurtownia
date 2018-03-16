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
public class PZ {

    private String id;
    private String idDostawca;
    private String dostawcaNazwa;
    private String dostawcaNIP;
    private String numer;
    private Date dataWystawienia;
    private String Notatka;
    private String uwagi;
    private double kwota;
    private int stan;
    public PZ(Date dataWystawienia, double kwota) {
        this.dataWystawienia = dataWystawienia;
        this.kwota = kwota;
    }

    public PZ() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdDostawca() {
        return idDostawca;
    }

    public void setIdDostawca(String idDostawca) {
        this.idDostawca = idDostawca;
    }

    public String getDostawcaNazwa() {
        return dostawcaNazwa;
    }

    public void setDostawcaNazwa(String dostawcaNazwa) {
        this.dostawcaNazwa = dostawcaNazwa;
    }

    public String getDostawcaNIP() {
        return dostawcaNIP;
    }

    public void setDostawcaNIP(String dostawcaNIP) {
        this.dostawcaNIP = dostawcaNIP;
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

    

}
