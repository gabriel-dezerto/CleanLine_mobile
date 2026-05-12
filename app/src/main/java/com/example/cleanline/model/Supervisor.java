package com.example.cleanline.model;

public class Supervisor {
    private int id;
    private String Email;
    private String Senha;
    private String Nome;
    private String CPF;
    private String Tel;
    private String Estado;
    private String Cidade;
    private String Bairro;
    private String Logradouro;
    private String N;

    public Supervisor(String email, String senha, String nome, String CPF, String tel, String estado, String cidade, String bairro, String logradouro, String n) {
        this.Email = email;
        this.Senha = senha;
        this.Nome = nome;
        this.CPF = CPF;
        this.Tel = tel;
        this.Estado = estado;
        this.Cidade = cidade;
        this.Bairro = bairro;
        this.Logradouro = logradouro;
        this.N = n;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return Email;
    }

    public String getSenha() {
        return Senha;
    }

    public String getNome() {
        return Nome;
    }

    public String getCPF() {
        return CPF;
    }

    public String getTel() {
        return Tel;
    }

    public String getEstado() {
        return Estado;
    }

    public String getCidade() {
        return Cidade;
    }

    public String getBairro() {
        return Bairro;
    }

    public String getLogradouro() {
        return Logradouro;
    }

    public String getN() {
        return N;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
    }

    public void setLogradouro(String logradouro) {
        Logradouro = logradouro;
    }

    public void setN(String n) {
        N = n;
    }
}
