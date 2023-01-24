package com.example.u6ap1menus_en_android;

public class MangaVo {
    private String titulo;
    private String portada;

    public MangaVo(String titulo, String portada) {
        this.titulo = titulo;
        this.portada = portada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }
}
