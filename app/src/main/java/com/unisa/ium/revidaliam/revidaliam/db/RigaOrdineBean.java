package com.unisa.ium.revidaliam.revidaliam.db;

public class RigaOrdineBean {
    private int idOrdine;
    private int idRigaOrdine;
    private int idProdotto;
    private String idSupermercato;
    private int quantita;

    public RigaOrdineBean() {

    }

    public RigaOrdineBean(int idOrdine, int idRigaOrdine, int idProdotto, String idSupermercato, int quantita) {
        this.idOrdine = idOrdine;
        this.idRigaOrdine = idRigaOrdine;
        this.idProdotto = idProdotto;
        this.idSupermercato = idSupermercato;
        this.quantita = quantita;
    }

    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public int getIdRigaOrdine() {
        return idRigaOrdine;
    }

    public void setIdRigaOrdine(int idRigaOrdine) {
        this.idRigaOrdine = idRigaOrdine;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public String getIdSupermercato() {
        return idSupermercato;
    }

    public void setIdSupermercato(String idSupermercato) {
        this.idSupermercato = idSupermercato;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
