package com.example.iumproject.Supermercato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iumproject.R;

public class RegistrazioneSupermercatoActivity extends AppCompatActivity{

    EditText nomeText;
    EditText cognomeText;
    EditText emailText;
    EditText indirizzoText;
    EditText userEditText;
    EditText pswEditText;
    Button registratiButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione_supermercato);

        nomeText = findViewById(R.id.nome);
        cognomeText = findViewById(R.id.cognome);
        emailText = findViewById(R.id.cognome);
        indirizzoText = findViewById(R.id.indirizzo);
        userEditText = findViewById(R.id.username);
        pswEditText = findViewById(R.id.password);
        registratiButton = findViewById(R.id.registrati);

        registratiButton.setOnClickListener(registraUtente);
    }

    View.OnClickListener registraUtente = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controllaRegistrazione(); //Controllo dei campi

            String nome = nomeText.getText().toString();
            String cognome = cognomeText.getText().toString();
            String email = emailText.getText().toString();
            String indirizzo = indirizzoText.getText().toString();
            String username = userEditText.getText().toString();
            String psw = pswEditText.getText().toString();

            //Setta i paramentri per la prossima activity
            Intent i = new Intent(v.getContext(), LoginSupermercatoActivity.class);
            i.putExtra("nome", nome);
            i.putExtra("cognome", cognome);
            i.putExtra("email", email);
            i.putExtra("indirizzo", indirizzo);
            i.putExtra("username", username);
            i.putExtra("psw", psw);
            startActivity(i);
            finish();
        }
    };

    public boolean controllaRegistrazione(){
        //Da implementare


        return true;
    }
}
