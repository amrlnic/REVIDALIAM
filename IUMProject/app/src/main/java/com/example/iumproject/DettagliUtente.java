package com.example.iumproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DettagliUtente extends AppCompatActivity {

    private String username;
    private String psw;
    private String nome;
    private String cognome;
    private String email;
    private String indirizzo;
    private TextView userText;
    private TextView nomeText;
    private TextView cognomeText;
    private TextView emailText;
    private TextView indirizzoText;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettagli_utente);

        userText = findViewById(R.id.username);
        nomeText = findViewById(R.id.nome);
        cognomeText = findViewById(R.id.cognome);
        indirizzoText = findViewById(R.id.indirizzo);
        emailText = findViewById(R.id.email);
        logout = findViewById(R.id.logout);

        cambiaDettaagliUtente();
        logout.setOnClickListener(disconnettiUtente);
    }

    View.OnClickListener disconnettiUtente = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);   //permette di non tornare più alla login e registrazione
            startActivity(i);
        }
    };

    public void cambiaDettaagliUtente(){

        username = getIntent().getStringExtra("username");
        psw = getIntent().getStringExtra("psw");
        nome = getIntent().getStringExtra("nome");
        cognome = getIntent().getStringExtra("cognome");
        email = getIntent().getStringExtra("email");
        indirizzo = getIntent().getStringExtra("indirizzo");

        userText.setText("Username : "+username);
        nomeText.setText("Nome : "+nome);
        cognomeText.setText("Cognome : "+cognome);
        emailText.setText("Email : "+email);
        indirizzoText.setText("Indirizzo : "+indirizzo);
    }
}
