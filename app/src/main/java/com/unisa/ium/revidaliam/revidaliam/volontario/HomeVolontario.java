package com.unisa.ium.revidaliam.revidaliam.volontario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.unisa.ium.revidaliam.revidaliam.R;
import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.db.VolontarioBean;
import com.unisa.ium.revidaliam.revidaliam.utente.DettagliUtente;


public class HomeVolontario extends AppCompatActivity {

    CardView aggiungiRichiesta;
    CardView listaRichieste;
    CardView dettagliUtente;
    CardView regoleVolontario;
    private ImageView setting;

    private String username;
    private String psw;
    private String nome;
    private String cognome;
    private String email;
    private String indirizzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volontario_activity_home);

        aggiungiRichiesta = findViewById(R.id.aggiungirichiesta);
        listaRichieste = findViewById(R.id.listarichieste);
        regoleVolontario = findViewById(R.id.regoleVolontario);
        dettagliUtente = findViewById(R.id.profilo);

        setting = findViewById(R.id.setting);

        username = getIntent().getStringExtra("username");
        psw = getIntent().getStringExtra("password");
        aggiungiRichiesta.setOnClickListener(goAggiungiRichiesta);
        dettagliUtente.setOnClickListener(goDettagliUtente);
        setting.setOnClickListener(goSetting);
        listaRichieste.setOnClickListener(goToRichiesteAccettate);
        regoleVolontario.setOnClickListener(goToRegoleVolontario);
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

    View.OnClickListener goAggiungiRichiesta = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent i = new Intent(v.getContext(), AggiungiRichiestaVolontario.class);
            DBHelper db = new DBHelper(v.getContext());
            VolontarioBean u = db.retrieveVolontarioContacts(username);
            Log.d("Username",u.getUsername());
            i.putExtra("username", u.getUsername());

            i.putExtra("password", u.getPassword());
            startActivity(i);
        }
    };

    View.OnClickListener goDettagliUtente = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            username = getIntent().getStringExtra("username");
            psw = getIntent().getStringExtra("password");
            Intent i = new Intent(v.getContext(), DettagliVolontario.class);

            DBHelper db=new DBHelper(v.getContext());
            VolontarioBean u=db.retrieveVolontarioContacts(username);
            i.putExtra("nome", u.getNome());
            i.putExtra("cognome", u.getCognome());
            i.putExtra("email", u.getEmail());
            i.putExtra("indirizzo", u.getIndirizzo());
            i.putExtra("username", u.getUsername());
            i.putExtra("psw", u.getPassword());
            startActivity(i);
        }
    };

    View.OnClickListener goSetting = new View.OnClickListener() {

        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), ImpostazioniActivity.class);
            startActivity(i);
        }
    };

    View.OnClickListener goToRichiesteAccettate  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), ListaRichiesteAccettateVolontario.class);
            i.putExtra("username", username);
            startActivity(i);
        }
    };

    View.OnClickListener goToRegoleVolontario  = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), RegoleVolontario.class);
            i.putExtra("username", username);
            startActivity(i);
        }
    };
}
