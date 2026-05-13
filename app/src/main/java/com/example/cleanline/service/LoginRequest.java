package com.example.cleanline.service;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("Email")
    private String email;

    @SerializedName("Senha")
    private String senha;

    public LoginRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    // Getters e Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
