package com.unisa.ium.revidaliam.revidaliam.db;

public class OrdineBean {
    private String idUtente;
    private int idOrdine;

    public OrdineBean() {

    }

    public OrdineBean(String idUtente, int idOrdine) {
        this.idUtente = idUtente;
        this.idOrdine = idOrdine;
    }

    public String getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(String idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }
}
