package com.example.cleanline.model;

import java.util.Date;

public class Vistoria {
    private Integer Id_Setor;
    private Integer Id_Super;
    private Double Pontuacao;
    private String Image;
    private String q1, q2, q3, q4, q5, q6, q7, q8;

    public Vistoria() {}

    public Integer getId_Setor() {
        return Id_Setor;
    }

    public void setId_Setor(Integer id_Setor) {
        Id_Setor = id_Setor;
    }

    public Integer getId_Super() {
        return Id_Super;
    }

    public void setId_Super(Integer id_Super) {
        Id_Super = id_Super;
    }

    public Double getPontuacao() {
        return Pontuacao;
    }

    public void setPontuacao(Double pontuacao) {
        Pontuacao = pontuacao;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getQ1() {
        return q1;
    }

    public void setQ1(String q1) {
        this.q1 = q1;
    }

    public String getQ2() {
        return q2;
    }

    public void setQ2(String q2) {
        this.q2 = q2;
    }

    public String getQ3() {
        return q3;
    }

    public void setQ3(String q3) {
        this.q3 = q3;
    }

    public String getQ4() {
        return q4;
    }

    public void setQ4(String q4) {
        this.q4 = q4;
    }

    public String getQ5() {
        return q5;
    }

    public void setQ5(String q5) {
        this.q5 = q5;
    }

    public String getQ6() {
        return q6;
    }

    public void setQ6(String q6) {
        this.q6 = q6;
    }

    public String getQ7() {
        return q7;
    }

    public void setQ7(String q7) {
        this.q7 = q7;
    }

    public String getQ8() {
        return q8;
    }

    public void setQ8(String q8) {
        this.q8 = q8;
    }
}
