package com.example.cleanline.model;

public class Rotas {
    private String setor;
    private String nfcTagEsperada;
    private int idSetor;

    public Rotas(String setor, String nfcTagEsperada, int idSetor) {
        this.setor = setor;
        this.nfcTagEsperada = nfcTagEsperada;
        this.idSetor = idSetor;
    }

    public String getSetor() {
        return setor;
    }

    public String getNfcTagEsperada() {
        return nfcTagEsperada;
    }

    public int getIdSetor() {
        return idSetor;
    }
}
