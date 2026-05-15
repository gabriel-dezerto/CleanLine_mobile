package com.example.cleanline.model;

public class Supervisor {
    private int id;
    private String Nome;
    private String Email;
    private String Senha;
    private String CPF;
    private String Tel;
    private String Estado;
    private String Cidade;
    private String Bairro;
    private String Logradouro;
    private int N;
    private String CEP;
    private String status;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return Nome; }
    public void setNome(String nome) { this.Nome = nome; }

    public String getEmail() { return Email; }
    public void setEmail(String email) { this.Email = email; }

    public String getCPF() { return CPF; }
    public void setCPF(String CPF) { this.CPF = CPF; }

    public String getTel() { return Tel; }
    public void setTel(String tel) { this.Tel = tel; }

    public String getEstado() { return Estado; }
    public void setEstado(String estado) { this.Estado = estado; }

    public String getCidade() { return Cidade; }
    public void setCidade(String cidade) { this.Cidade = cidade; }

    public String getBairro() { return Bairro; }
    public void setBairro(String bairro) { this.Bairro = bairro; }

    public String getLogradouro() { return Logradouro; }
    public void setLogradouro(String logradouro) { this.Logradouro = logradouro; }

    public int getN() { return N; }
    public void setN(int n) { this.N = n; }

    public String getCEP() { return CEP; }
    public void setCEP(String CEP) { this.CEP = CEP; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}