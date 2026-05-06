package com.example.cleanline.formulario;

import java.util.List;

public class FormularioRequest {

    private List<String> respostas;
    private String nota;
    private String foto;

    public FormularioRequest(List<String> respostas, String nota, String foto) {
        this.respostas = respostas;
        this.nota = nota;
        this.foto = foto;
    }

    public List<String> getRespostas() {
        return respostas;
    }

    public String getNota() {
        return nota;
    }

    public String getFoto() {
        return foto;
    }
}
