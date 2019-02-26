package com.unisa.ium.revidaliam.revidaliam.utente;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.unisa.ium.revidaliam.revidaliam.R;
import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.db.UtenteBean;

/**
 * Classe che gestisce l'activity Home per l'utente
 */

public class HomeUtente extends AppCompatActivity {

    private CardView faiSpesa;                          //View che permette l'accesso al task per fare la spesa
    private CardView carrello;                          //View che permette l'accesso al carrello
    private CardView dettagliUtente;                    //View che permette l'accesso ai dettagli dell'utente
    private CardView ordiniUtente;                      //View che permette l'accesso allo storico degli ordini
    private ImageView setting;                          //View che permette di accedere alle impostazioni

    //Dati dell'utente
    private String username;
    private String psw;
    private String nome;
    private String cognome;
    private String email;
    private String indirizzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utente_activity_home);      //settiamo il Layout corrispondente alla nostra Activity

        //Settiamo i valori delle varie View
        faiSpesa = findViewById(R.id.spesaUtente);
        carrello = findViewById(R.id.carrello);
        dettagliUtente = findViewById(R.id.dettagliUtente);
        ordiniUtente = findViewById(R.id.ordiniUtente);
        setting = findViewById(R.id.setting);

        //Recuperiamo l'username dalla precedente activity
        username = getIntent().getStringExtra("username");


        //Settiamo i vari Listener alle nostre View
        faiSpesa.setOnClickListener(goToSpesa);
        dettagliUtente.setOnClickListener(goDettagliUtente);
        carrello.setOnClickListener(goCarrello);
        ordiniUtente.setOnClickListener(goOrdiniUtente);
        setting.setOnClickListener(goSetting);
    }

    View.OnClickListener goToSpesa = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), SceltaIndirizzoActivity.class);

            if(getIntent().getStringExtra("idMarket") != null) {
                i.putExtra("idMarket", getIntent().getStringExtra("idMarket"));
                Log.d("TAG", "Ho inserito nell'intent il supermercato");
            }
            if(getIntent().getStringExtra("username") != null) {
                i.putExtra("username", getIntent().getStringExtra("username"));
                Log.d("TAG", "Ho inserito nell'intent l'username");
            }

            startActivity(i);
            finish();

        }
    };

    View.OnClickListener goCarrello = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), Carrello.class);

            if(getIntent().getStringExtra("idMarket") != null) {
                i.putExtra("idMarket", getIntent().getStringExtra("idMarket"));
                Log.d("TAG", "Ho inserito nell'intent il supermercato");
            }

            i.putExtra("prev", "home");
            i.putExtra("username", username);
            Log.d("TAG", "Ho inserito nell'intent l'username");

            startActivity(i);
            finish();
        }
    };

    View.OnClickListener goDettagliUtente = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            username = getIntent().getStringExtra("username");
            psw = getIntent().getStringExtra("password");
            Intent i = new Intent(v.getContext(), DettagliUtente.class);

            DBHelper db=new DBHelper(v.getContext());
            UtenteBean u=db.retrieveUtenteContacts(username);
            i.putExtra("nome", u.getNome());
            i.putExtra("cognome", u.getCognome());
            i.putExtra("email", u.getEmail());
            i.putExtra("indirizzo", u.getIndirizzo());
            i.putExtra("username", u.getUsername());
            i.putExtra("psw", u.getPassword());

            if(getIntent().getStringExtra("idMarket") != null) {
                i.putExtra("idMarket", getIntent().getStringExtra("idMarket"));
                Log.d("TAG", "Ho inserito nell'intent il supermercato");
            }

            Log.d("TAG", "Accediamo ai dettagli utente dalla Home");
            startActivity(i);
            finish();
        }
    };

    View.OnClickListener goOrdiniUtente = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), StoricoOrdini.class);
            Log.d("TAG", "Accediamo allo storico ordini dalla Home");

            if(getIntent().getStringExtra("idMarket") != null) {
                i.putExtra("idMarket", getIntent().getStringExtra("idMarket"));
                Log.d("TAG", "Ho inserito nell'intent il supermercato");
            }
            if(getIntent().getStringExtra("username") != null) {
                i.putExtra("username", getIntent().getStringExtra("username"));
                Log.d("TAG", "Ho inserito nell'intent l'username");
            }

            startActivity(i);
            finish();
        }
    };

    View.OnClickListener goSetting = new View.OnClickListener() {

        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), ImpostazioniActivity.class);
            Log.d("TAG", "Accediamo alle impostazione dalla Home");

            if(getIntent().getStringExtra("idMarket") != null) {
                i.putExtra("idMarket", getIntent().getStringExtra("idMarket"));
                Log.d("TAG", "Ho inserito nell'intent il supermercato");
            }
            if(getIntent().getStringExtra("username") != null) {
                i.putExtra("username", getIntent().getStringExtra("username"));
                Log.d("TAG", "Ho inserito nell'intent l'username");
            }

            startActivity(i);
            finish();
        }
    };
}
