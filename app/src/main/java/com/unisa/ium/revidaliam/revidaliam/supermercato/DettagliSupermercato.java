package com.unisa.ium.revidaliam.revidaliam.supermercato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.unisa.ium.revidaliam.revidaliam.MainActivity;
import com.unisa.ium.revidaliam.revidaliam.R;
import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.db.SupermercatoBean;
import com.unisa.ium.revidaliam.revidaliam.db.UtenteBean;

public class DettagliSupermercato extends AppCompatActivity {
    private String username;
    private String psw;
    private String nome;
    private String cognome;
    private String email;
    private String indirizzo;
    private TextView userText;
    private TextView pswText;
    private TextView nomeText;
    private TextView cognomeText;
    private TextView emailText;
    private TextView indirizzoText;
    private Button modifica;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supermercato_activity_dettagli_utente);

        userText = findViewById(R.id.username);
        nomeText = findViewById(R.id.nome);
        indirizzoText = findViewById(R.id.indirizzo);
        emailText = findViewById(R.id.email);
        logout = findViewById(R.id.logout);
        modifica = findViewById(R.id.modificaDati);
        mostraDettagliUtente();
        modifica.setOnClickListener(modificaDati);
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

    View.OnClickListener modificaDati = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DBHelper db= new DBHelper(v.getContext());
            username = userText.getText().toString();
            //manca password
            nome = nomeText.getText().toString();
            email =emailText.getText().toString();
            indirizzo = indirizzoText.getText().toString();
            SupermercatoBean supermercato =db.retrieveSupermercatoContacts(username);
            Intent i = new Intent(v.getContext(), ModificaUtente.class);

            i.putExtra("username",supermercato.getUsername());
            i.putExtra("nome",supermercato.getNome());
            i.putExtra("email",supermercato.getEmail());
            i.putExtra("indirizzo",supermercato.getIndirizzo());
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);   //permette di non tornare più alla login e registrazione
            startActivity(i);
            finish();

        }
    };

    public void mostraDettagliUtente(){

        username = getIntent().getStringExtra("username");
        DBHelper db=new DBHelper(getApplicationContext());
        SupermercatoBean supermercato = db.retrieveSupermercatoContacts(username);

        userText.setText(supermercato.getUsername());
        nomeText.setText(supermercato.getNome());
        emailText.setText(supermercato.getEmail());
        indirizzoText.setText(supermercato.getIndirizzo());
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, HomeSupermercato.class);
        Log.d("TAG", "Ritorniamo alla HomeSupermercato dall'activity DettagliSupermercato");

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
