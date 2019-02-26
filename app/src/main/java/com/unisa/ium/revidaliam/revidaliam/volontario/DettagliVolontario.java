package com.unisa.ium.revidaliam.revidaliam.volontario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.unisa.ium.revidaliam.revidaliam.MainActivity;
import com.unisa.ium.revidaliam.revidaliam.R;
import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.db.UtenteBean;
import com.unisa.ium.revidaliam.revidaliam.db.VolontarioBean;
import com.unisa.ium.revidaliam.revidaliam.utente.HomeUtente;

public class DettagliVolontario extends AppCompatActivity {
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
        setContentView(R.layout.volontario_activity_dettagli_utente);

        userText = findViewById(R.id.username);
        nomeText = findViewById(R.id.nome);
        cognomeText = findViewById(R.id.cognome);
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
            VolontarioBean u =db.retrieveVolontarioContacts(username);
            Intent i = new Intent(v.getContext(), ModificaUtente.class);

            i.putExtra("username",u.getUsername());
            i.putExtra("nome",u.getNome());
            i.putExtra("cognome", u.getCognome());
            i.putExtra("email",u.getEmail());
            i.putExtra("indirizzo",u.getIndirizzo());
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   //permette di non tornare più alla login e registrazione
            startActivity(i);
            finish();

        }
    };

    public void mostraDettagliUtente(){

        username = getIntent().getStringExtra("username");
        DBHelper db=new DBHelper(getApplicationContext());
        VolontarioBean volontario = db.retrieveVolontarioContacts(username);

        userText.setText(volontario.getUsername());
        nomeText.setText(volontario.getNome());
        cognomeText.setText(volontario.getCognome());
        emailText.setText(volontario.getEmail());
        indirizzoText.setText(volontario.getIndirizzo());
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, HomeVolontario.class);
        Log.d("TAG", "Ritorniamo alla HomeVolontario dall'activity DettagliVolontario");

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
        emailText.setText(email);
        indirizzoText.setText(indirizzo);
        i.putExtra("username", volontario.getUsername());
        i.putExtra("Nome", volontario.getNome());
        i.putExtra("email", volontario.getEmail());
        i.putExtra("indirizzo", volontario.getIndirizzo());
        startActivity(i);
        super.onBackPressed();
    }
}
