package com.taflord.namazvakitleri.Models;

public class CountryItem {
    public String UlkeAdi;
    public String UlkeID;

    public CountryItem() {
    }

    public CountryItem(String ulkeAdi, String ulkeID) {
        UlkeAdi = ulkeAdi;
        UlkeID = ulkeID;
    }


    public String getUlkeAdi() {
        return UlkeAdi;
    }

    public void setUlkeAdi(String ulkeAdi) {
        UlkeAdi = ulkeAdi;
    }

    public String getUlkeID() {
        return UlkeID;
    }

    public void setUlkeID(String ulkeID) {
        UlkeID = ulkeID;
    }
}
