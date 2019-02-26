package com.unisa.ium.revidaliam.revidaliam.db;

public class RichiestaBean {
    private int idRichiesta;
    private String idUtente;
    private int idOrdine;
    private boolean stato;

    public RichiestaBean() {

    }

    public RichiestaBean(int idRichiesta, String idUtente, int idOrdine, boolean stato) {
        this.idRichiesta = idRichiesta;
        this.idUtente = idUtente;
        this.idOrdine = idOrdine;
        this.stato = stato;
    }

    public int getIdRichiesta() {
        return idRichiesta;
    }

    public void setIdRichiesta(int idRichiesta) {
        this.idRichiesta = idRichiesta;
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

    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }
}
