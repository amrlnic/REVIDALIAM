package com.unisa.ium.revidaliam.revidaliam.supermercato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import com.unisa.ium.revidaliam.revidaliam.utente.DettagliUtente;
import com.unisa.ium.revidaliam.revidaliam.R;
import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.db.SupermercatoBean;


public class HomeSupermercato extends AppCompatActivity {

    CardView aggiungiProdotto;
    CardView listaProdotti;
    CardView profilo;
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
        setContentView(R.layout.supermercato_activity_home);

        aggiungiProdotto = findViewById(R.id.aggiungiprodotto);
        listaProdotti = findViewById(R.id.listaprodotti);
        profilo = findViewById(R.id.profilo);

        setting = findViewById(R.id.setting);

        username = getIntent().getStringExtra("username");
        profilo.setOnClickListener(goDettagliUtente);
        setting.setOnClickListener(goSetting);
        aggiungiProdotto.setOnClickListener(goToAddProdotto);
        listaProdotti.setOnClickListener(goToListaProdotto);
    }

    View.OnClickListener goToAddProdotto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i =  new Intent(v.getContext(), AggiungiProdotto.class);
            username = getIntent().getStringExtra("username");
            i.putExtra("username", username);
            startActivity(i);
        }
    };

    View.OnClickListener goToListaProdotto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i =  new Intent(v.getContext(), ListaProdottiActivity.class);
            username = getIntent().getStringExtra("username");
            i.putExtra("username", username);
            startActivity(i);
        }
    };

    View.OnClickListener goDettagliUtente = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            username = getIntent().getStringExtra("username");
            psw = getIntent().getStringExtra("password");
            Intent i = new Intent(v.getContext(), DettagliSupermercato.class);

            DBHelper db=new DBHelper(v.getContext());
            SupermercatoBean u=db.retrieveSupermercatoContacts(username);
            i.putExtra("nome", u.getNome());
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
}
