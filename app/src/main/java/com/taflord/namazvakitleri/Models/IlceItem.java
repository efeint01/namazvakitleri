package com.taflord.namazvakitleri.Models;

public class IlceItem {
    public String IlceAdi;
    public String IlceID;

    public IlceItem() {
    }

    public IlceItem(String ilceAdi, String ilceID) {
        IlceAdi = ilceAdi;
        IlceID = ilceID;
    }

    public String getIlceAdi() {
        return IlceAdi;
    }

    public void setIlceAdi(String ilceAdi) {
        IlceAdi = ilceAdi;
    }

    public String getIlceID() {
        return IlceID;
    }

    public void setIlceID(String ilceID) {
        IlceID = ilceID;
    }
}


