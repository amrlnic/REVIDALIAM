package com.unisa.ium.revidaliam.revidaliam.db;

public class ProdottoBean {
    private int idProdotto;
    private String nome;
    private float prezzo;
    private String descrizione;

    public ProdottoBean() {
    }

    public ProdottoBean(int idProdotto, String nome, float prezzo, String descrizione) {
        this.idProdotto = idProdotto;
        this.nome = nome;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

}
