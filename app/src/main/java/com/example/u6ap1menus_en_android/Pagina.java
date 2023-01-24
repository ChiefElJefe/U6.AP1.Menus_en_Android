package com.example.u6ap1menus_en_android;


import java.io.Serializable;

public class Pagina implements Serializable {
    private String volumen;
    private String sinopsis;
    private String portada;

    public Pagina(String volumen, String sinopsis, String portada) {
        this.volumen = volumen;
        this.sinopsis = sinopsis;
        this.portada = portada;
    }

    public String getVolumen() {
        return volumen;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public String getPortada() {
        return portada;
    }
}
