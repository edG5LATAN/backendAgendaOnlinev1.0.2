package com.agenda.agenda.domain.model;


import jakarta.persistence.Embeddable;

@Embeddable
public class RedesSociales {

    private String facebook;
    private String instagran;
    private String whatsapp;
    private String tiktok;

    //constructor
    public RedesSociales() {
    }
    public RedesSociales(String facebook, String instagran, String tiktok, String whatsapp) {
        this.facebook = facebook;
        this.instagran = instagran;
        this.tiktok = tiktok;
        this.whatsapp = whatsapp;
    }
    //getter and setter
    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagran() {
        return instagran;
    }

    public void setInstagran(String instagran) {
        this.instagran = instagran;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getTiktok() {
        return tiktok;
    }

    public void setTiktok(String tiktok) {
        this.tiktok = tiktok;
    }
}
