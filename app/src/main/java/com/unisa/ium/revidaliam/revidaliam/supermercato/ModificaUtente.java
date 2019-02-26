package com.unisa.ium.revidaliam.revidaliam.supermercato;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.unisa.ium.revidaliam.revidaliam.R;
import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.db.SupermercatoBean;
import com.unisa.ium.revidaliam.revidaliam.db.UtenteBean;
import com.unisa.ium.revidaliam.revidaliam.utente.DettagliUtente;

public class ModificaUtente extends AppCompatActivity {

    private String username;
    private String psw;
    private String nome;
    private String cognome;
    private String email;
    private String indirizzo;
    private EditText userText;
    private EditText pswText;
    private EditText nomeText;
    private EditText cognomeText;
    private EditText emailText;
    private EditText indirizzoText;
    private Button modifica;
    private Button annulla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supermercato_activity_modifica_utente);
        Intent i = getIntent();
        Log.d("TAG", "effettuiamo modifica");

        userText = findViewById(R.id.username);
        nomeText = findViewById(R.id.nome);
        indirizzoText = findViewById(R.id.indirizzo);
        emailText = findViewById(R.id.email);
        annulla = findViewById(R.id.annulla);
        modifica = findViewById(R.id.conferma);

        modifica.setOnClickListener(modificaDati);
        annulla.setOnClickListener(returnBack);

        userText.setText(i.getStringExtra("username"));
        Log.d("TAG", "Abbiamo settato la textView utente a: "+userText.getText().toString());
        nomeText.setText(i.getStringExtra("nome"));
        Log.d("TAG", "Abbiamo settato la textView nome a: "+nomeText.getText().toString());
        indirizzoText.setText(i.getStringExtra("indirizzo"));
        Log.d("TAG", "Abbiamo settato la textView indirizzo a: "+indirizzoText.getText().toString());
        emailText.setText(i.getStringExtra("email"));
        Log.d("TAG", "Abbiamo settato la textView email a: "+emailText.getText().toString());

    }

    View.OnClickListener modificaDati = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DBHelper db = new DBHelper(v.getContext());

            String key = getIntent().getStringExtra("username");

            username = userText.getText().toString();
            //manca password
            nome = nomeText.getText().toString();
            email = emailText.getText().toString();
            indirizzo = indirizzoText.getText().toString();
            SupermercatoBean supermercato = new SupermercatoBean();
            supermercato.setUsername(username);
            supermercato.setIndirizzo(indirizzo);
            supermercato.setNome(nome);
            supermercato.setEmail(email);
            db.updateContact(supermercato, key);
            userText.setText(username);
            nomeText.setText(nome);
            emailText.setText(email);
            indirizzoText.setText(indirizzo);
            Intent i = new Intent(v.getContext(), DettagliSupermercato.class);
            i.putExtra("username", supermercato.getUsername());
            i.putExtra("Nome", supermercato.getNome());
            i.putExtra("email", supermercato.getEmail());
            i.putExtra("indirizzo", supermercato.getIndirizzo());
            startActivity(i);
            finish();
        }
    };

    View.OnClickListener returnBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DBHelper db = new DBHelper(v.getContext());

            String key = getIntent().getStringExtra("username");

            username = userText.getText().toString();
            nome = nomeText.getText().toString();
            email = emailText.getText().toString();
            indirizzo = indirizzoText.getText().toString();
            SupermercatoBean supermercato = new SupermercatoBean();
            supermercato.setUsername(username);
            supermercato.setIndirizzo(indirizzo);
            supermercato.setNome(nome);
            supermercato.setEmail(email);
            db.updateContact(supermercato, key);
            userText.setText(username);
            nomeText.setText(nome);
            emailText.setText(email);
            indirizzoText.setText(indirizzo);
            Intent i = new Intent(v.getContext(), DettagliSupermercato.class);
            i.putExtra("username", supermercato.getUsername());
            i.putExtra("Nome", supermercato.getNome());
            i.putExtra("email", supermercato.getEmail());
            i.putExtra("indirizzo", supermercato.getIndirizzo());
            startActivity(i);
            finish();
        }
    };

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, DettagliSupermercato.class);
        Log.d("TAG", "Ritorniamo al dettagli Supermercato dall'activity ModificaUtente");

        DBHelper db = new DBHelper(this);

        String key = getIntent().getStringExtra("username");

        username = userText.getText().toString();
        nome = nomeText.getText().toString();
        email = emailText.getText().toString();
        indirizzo = indirizzoText.getText().toString();
        SupermercatoBean supermercato = new SupermercatoBean();
        supermercato.setUsername(username);
        supermercato.setIndirizzo(indirizzo);
        supermercato.setNome(nome);
        supermercato.setEmail(email);
        db.updateContact(supermercato, key);
        userText.setText(username);
        nomeText.setText(nome);
        emailText.setText(email);
        indirizzoText.setText(indirizzo);
        i.putExtra("username", supermercato.getUsername());
        i.putExtra("Nome", supermercato.getNome());
        i.putExtra("email", supermercato.getEmail());
        i.putExtra("indirizzo", supermercato.getIndirizzo());
        startActivity(i);
        super.onBackPressed();
    }

}
