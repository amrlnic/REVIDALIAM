package com.unisa.ium.revidaliam.revidaliam.volontario;

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
import com.unisa.ium.revidaliam.revidaliam.db.VolontarioBean;
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
        setContentView(R.layout.volontario_activity_modifica_utente);
        Intent i = getIntent();
        Log.d("TAG", "effettuiamo modifica");

        userText = findViewById(R.id.username);
        nomeText = findViewById(R.id.nome);
        cognomeText = findViewById(R.id.cognome);
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
        cognomeText.setText(i.getStringExtra("cognome"));
        Log.d("TAG", "Abbiamo settato la textView indirizzo a: "+cognomeText.getText().toString());
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
            cognome = cognomeText.getText().toString();
            email = emailText.getText().toString();
            indirizzo = indirizzoText.getText().toString();
            VolontarioBean volontario = new VolontarioBean();
            volontario.setUsername(username);
            volontario.setIndirizzo(indirizzo);
            volontario.setNome(nome);
            volontario.setCognome(cognome);
            volontario.setEmail(email);
            db.updateContact(volontario, key);
            userText.setText(username);
            nomeText.setText(nome);
            cognomeText.setText(cognome);
            emailText.setText(email);
            indirizzoText.setText(indirizzo);
            Intent i = new Intent(v.getContext(), DettagliVolontario.class);
            i.putExtra("username", volontario.getUsername());
            i.putExtra("Nome", volontario.getNome());
            i.putExtra("Cognome", volontario.getCognome());
            i.putExtra("email", volontario.getEmail());
            i.putExtra("indirizzo", volontario.getIndirizzo());
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
            cognome = cognomeText.getText().toString();
            email = emailText.getText().toString();
            indirizzo = indirizzoText.getText().toString();
            VolontarioBean volontario = new VolontarioBean();
            volontario.setUsername(username);
            volontario.setIndirizzo(indirizzo);
            volontario.setNome(nome);
            volontario.setCognome(cognome);
            volontario.setEmail(email);
            db.updateContact(volontario, key);
            userText.setText(username);
            nomeText.setText(nome);
            cognomeText.setText(cognome);
            emailText.setText(email);
            indirizzoText.setText(indirizzo);
            Intent i = new Intent(v.getContext(), DettagliVolontario.class);
            i.putExtra("username", volontario.getUsername());
            i.putExtra("Nome", volontario.getNome());
            i.putExtra("Cognome", volontario.getCognome());
            i.putExtra("email", volontario.getEmail());
            i.putExtra("indirizzo", volontario.getIndirizzo());
            startActivity(i);
            finish();
        }
    };

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, DettagliVolontario.class);
        Log.d("TAG", "Ritorniamo al dettagli Volontario dall'activity ModificaUtente");

        DBHelper db = new DBHelper(this);

        String key = getIntent().getStringExtra("username");

        username = userText.getText().toString();
        nome = nomeText.getText().toString();
        cognome = cognomeText.getText().toString();
        email = emailText.getText().toString();
        indirizzo = indirizzoText.getText().toString();
        VolontarioBean volontario = new VolontarioBean();
        volontario.setUsername(username);
        volontario.setIndirizzo(indirizzo);
        volontario.setNome(nome);
        volontario.setCognome(cognome);
        volontario.setEmail(email);
        db.updateContact(volontario, key);
        userText.setText(username);
        nomeText.setText(nome);
        cognomeText.setText(cognome);
        emailText.setText(email);
        indirizzoText.setText(indirizzo);
        i.putExtra("username", volontario.getUsername());
        i.putExtra("Nome", volontario.getNome());
        i.putExtra("Cognome", volontario.getCognome());
        i.putExtra("email", volontario.getEmail());
        i.putExtra("indirizzo", volontario.getIndirizzo());
        startActivity(i);
        super.onBackPressed();
    }

}
