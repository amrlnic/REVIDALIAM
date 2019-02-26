package com.unisa.ium.revidaliam.revidaliam.utente;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.unisa.ium.revidaliam.revidaliam.AdapterProdottiUtente;
import com.unisa.ium.revidaliam.revidaliam.R;
import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.db.ProdottoBean;
import com.unisa.ium.revidaliam.revidaliam.db.RigaOrdineBean;

import java.text.DecimalFormat;

public class DettagliOrdine extends AppCompatActivity {

    private ListView listaRighe;
    private TextView prezzo;
    private String username;
    private ProdottoBean[] prodotti;
    private RigaOrdineBean[] rigaProdotti;
    private RelativeLayout layoutLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utente_activity_ordine);

        listaRighe = findViewById(R.id.mylistview);
        prezzo = findViewById(R.id.prezzo);

        DBHelper db = new DBHelper(this);
        username = getIntent().getStringExtra("username");

        rigaProdotti = db.retrieveAllRigaOrdineByOrdineContacts(getIntent().getIntExtra("idorder", -1));
        prodotti = new ProdottoBean[rigaProdotti.length];

        float tot = 0;
        for (int i = 0; i < rigaProdotti.length; i++) {
            prodotti[i] = db.retrieveProdottoContacts(rigaProdotti[i].getIdProdotto() + "");
            tot += prodotti[i].getPrezzo() * rigaProdotti[i].getQuantita();
        }


        prezzo.setText(new DecimalFormat("0.00").format(tot) + "€");

        listaRighe = findViewById(R.id.mylistview);

        Log.d("DEBUG", "ListView creato: listView=" + listaRighe);

        AdapterProdottiUtente prodottiAdapter = new AdapterProdottiUtente(this, rigaProdotti);

        Log.d("DEBUG", "ArrayAdapter creato: arrayAdapter=" + prodottiAdapter);

        listaRighe.setAdapter(prodottiAdapter);

        Log.d("DEBUG", "Fatto!");

        layoutLogo = findViewById(R.id.layoutLogo);
        layoutLogo.setOnClickListener(homeLink);
    }

    View.OnClickListener homeLink = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            {
                Intent i = new Intent(v.getContext(), HomeUtente.class);
                Log.d("TAG", "Ritorniamo alla Home dall'activity Impostazioni");

                if (getIntent().getStringExtra("idMarket") != null) {
                    i.putExtra("idMarket", getIntent().getStringExtra("idMarket"));
                    Log.d("TAG", "Ho inserito nell'intent il supermercato");
                }
                if (getIntent().getStringExtra("username") != null) {
                    i.putExtra("username", getIntent().getStringExtra("username"));
                    Log.d("TAG", "Ho inserito nell'intent l'username");
                }

                startActivity(i);
                finish();
            }
        }
    };


    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, StoricoOrdini.class);
        Log.d("TAG", "Ritorniamo allo storico ordini dall'activity DettagliOrdine");

        if (getIntent().getStringExtra("idMarket") != null)
            i.putExtra("idMarket", getIntent().getStringExtra("idMarket"));
        if (getIntent().getStringExtra("username") != null)
            i.putExtra("username", getIntent().getStringExtra("username"));

        startActivity(i);
        super.onBackPressed();
    }
}
