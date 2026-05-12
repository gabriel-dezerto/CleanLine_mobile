package com.example.cleanline.model;

import java.util.Date;

public class Vistoria {
    private int Id;
    private int Id_Setor;
    private int Id_Super;
    private int Id_Rota;
    private String Image;
    private String Pontuacao;
    private String Data_e_Hora;
    private String q1;
    private String q2;
    private String q3;
    private String q4;
    private String q5;
    private String q6;
    private String q7;
    private String q8;

    public Vistoria(int id_Setor, int id_Super, int id_Rota, String image, String pontuacao, String data_e_Hora, String q1, String q2, String q3, String q4, String q5, String q6, String q7, String q8) {
        Id_Setor = id_Setor;
        Id_Super = id_Super;
        Id_Rota = id_Rota;
        Image = image;
        Pontuacao = pontuacao;
        Data_e_Hora = data_e_Hora;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.q5 = q5;
        this.q6 = q6;
        this.q7 = q7;
        this.q8 = q8;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId_Setor() {
        return Id_Setor;
    }

    public void setId_Setor(int id_Setor) {
        Id_Setor = id_Setor;
    }

    public int getId_Super() {
        return Id_Super;
    }

    public void setId_Super(int id_Super) {
        Id_Super = id_Super;
    }

    public int getId_Rota() {
        return Id_Rota;
    }

    public void setId_Rota(int id_Rota) {
        Id_Rota = id_Rota;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPontuacao() {
        return Pontuacao;
    }

    public void setPontuacao(String pontuacao) {
        Pontuacao = pontuacao;
    }

    public String getData_e_Hora() {
        return Data_e_Hora;
    }

    public void setData_e_Hora(String data_e_Hora) {
        Data_e_Hora = data_e_Hora;
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
