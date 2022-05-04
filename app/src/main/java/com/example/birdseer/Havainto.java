package com.example.birdseer;

public class Havainto {

    private String laji;
    private String sijainti;
    private int paiva;
    private int kuukausi;
    private int vuosi;

    public Havainto(String laji, String sijainti, int paiva, int kuukausi, int vuosi) {
        this.laji = laji;
        this.sijainti = sijainti;
        this.paiva = paiva;
        this.kuukausi = kuukausi;
        this.vuosi = vuosi;
    }

    public Havainto() {

    }

    public String getLaji() {
        return laji;
    }

    public String getSijainti() {
        return sijainti;
    }

    public int getPaiva() {

        return paiva;
    }

    public int getKuukausi() {

        return kuukausi;
    }

    public int getVuosi() {

        return vuosi;
    }

    public void setPaiva(int day) {

        day = paiva;
    }
}
