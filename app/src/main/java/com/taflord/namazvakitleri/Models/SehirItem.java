package com.taflord.namazvakitleri.Models;

public class SehirItem {
    public String SehirAdi;
    public String SehirID;

    public SehirItem() {
    }

    public SehirItem(String sehirAdi, String sehirID) {
        SehirAdi = sehirAdi;
        SehirID = sehirID;
    }

    public String getSehirAdi() {
        return SehirAdi;
    }

    public void setSehirAdi(String sehirAdi) {
        SehirAdi = sehirAdi;
    }

    public String getSehirID() {
        return SehirID;
    }

    public void setSehirID(String sehirID) {
        SehirID = sehirID;
    }
}
