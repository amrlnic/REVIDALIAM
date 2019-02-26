package com.unisa.ium.revidaliam.revidaliam.utente;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.unisa.ium.revidaliam.revidaliam.R;
import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.db.UtenteBean;

public class RegistrazioneActivity extends AppCompatActivity {

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
        setContentView(R.layout.utente_activity_registrazione);

        nomeText = findViewById(R.id.nome);
        cognomeText = findViewById(R.id.cognome);
        emailText = findViewById(R.id.email);
        indirizzoText = findViewById(R.id.indirizzo);
        userEditText = findViewById(R.id.username);
        pswEditText = findViewById(R.id.password);
        registratiButton = findViewById(R.id.registrati);

        registratiButton.setOnClickListener(registraUtente);
    }

    View.OnClickListener registraUtente = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            String nome = nomeText.getText().toString();
            String cognome = cognomeText.getText().toString();
            String email = emailText.getText().toString();
            String indirizzo = indirizzoText.getText().toString();
            String username = userEditText.getText().toString();
            String psw = pswEditText.getText().toString();
            UtenteBean u = new UtenteBean(username, nome, cognome, indirizzo, email, psw);
            DBHelper db = new DBHelper(getApplicationContext());
            db.insertContact(u);

            //Setta i paramentri per la prossima activity
            Intent i = new Intent(v.getContext(), LoginUtenteActivity.class);
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

    public boolean controllaRegistrazione() {
        //Da implementare


        return true;
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, LoginUtenteActivity.class);
        Log.d("TAG", "Ritorniamo al login Utente dall'activity Registrazione");
        startActivity(i);
        super.onBackPressed();
    }
}
