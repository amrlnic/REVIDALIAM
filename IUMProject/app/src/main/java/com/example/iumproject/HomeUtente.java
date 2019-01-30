package com.example.iumproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

public class HomeUtente extends AppCompatActivity {

    CardView faiSpesa;
    CardView carrello;
    CardView dettagliUtente;
    CardView ordiniUtente;
    private String username;
    private String psw;
    private String nome;
    private String cognome;
    private String email;
    private String indirizzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_utente);

        faiSpesa = findViewById(R.id.spesaUtente);
        carrello = findViewById(R.id.carrello);
        dettagliUtente = findViewById(R.id.dettagliUtente);
        ordiniUtente = findViewById(R.id.ordiniUtente);

        username = getIntent().getStringExtra("username");
        psw = getIntent().getStringExtra("psw");
        nome = getIntent().getStringExtra("nome");
        cognome = getIntent().getStringExtra("cognome");
        email = getIntent().getStringExtra("email");
        indirizzo = getIntent().getStringExtra("indirizzo");

        dettagliUtente.setOnClickListener(goDettagliUtente);
    }

    View.OnClickListener goToSpesa = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //avvia l' activity per la spesa
        }
    };

    View.OnClickListener goCarrello = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //avvia l' activity per la spesa
        }
    };

    View.OnClickListener goDettagliUtente = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), DettagliUtente.class);

            //Setta i paramentri per la prossima activity
            i.putExtra("nome", nome);
            i.putExtra("cognome", cognome);
            i.putExtra("email", email);
            i.putExtra("indirizzo", indirizzo);
            i.putExtra("username", username);
            i.putExtra("psw", psw);
            startActivity(i);
        }
    };

    View.OnClickListener goOrdiniUtente = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //avvia l' activity per la spesa
        }
    };
}
