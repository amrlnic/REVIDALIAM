package com.unisa.ium.revidaliam.revidaliam.db;

public class ProdottoSupermercatoBean {

    private int idProdotto;
    private String usernameSupermercato;

    public ProdottoSupermercatoBean() {

    }

    public ProdottoSupermercatoBean(int idProdotto, String usernameSupermercato) {
        this.idProdotto = idProdotto;
        this.usernameSupermercato = usernameSupermercato;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public String getUsernameSupermercato() {
        return usernameSupermercato;
    }

    public void setUsernameSupermercato(String usernameSupermercato) {
        this.usernameSupermercato = usernameSupermercato;
    }
}
