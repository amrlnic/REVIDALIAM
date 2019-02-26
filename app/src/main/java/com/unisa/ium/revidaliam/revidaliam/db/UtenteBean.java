package com.unisa.ium.revidaliam.revidaliam.db;

public class UtenteBean {
    private String username;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String email;
    private String password;

    public UtenteBean() {

    }

    public UtenteBean(String username, String nome, String cognome, String indirizzo, String email, String password) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
