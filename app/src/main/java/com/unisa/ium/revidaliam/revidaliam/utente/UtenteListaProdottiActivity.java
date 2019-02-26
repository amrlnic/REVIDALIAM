package com.unisa.ium.revidaliam.revidaliam.utente;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.unisa.ium.revidaliam.revidaliam.ProdottiAdapter;
import com.unisa.ium.revidaliam.revidaliam.R;
import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.db.ProdottoBean;

public class UtenteListaProdottiActivity extends AppCompatActivity {
    public ListView listView;
    public ImageView cart;
    private RelativeLayout layoutLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utente_activity_lista_prodotti);

        String idMarket = getIntent().getStringExtra("idMarket");

        DBHelper dbHelper = new DBHelper(this);
        ProdottoBean[] prodotti = dbHelper.retrieveAllProdottoFromMarketContacts(idMarket);

        listView = findViewById(R.id.mylistview);
        cart = findViewById(R.id.carrello);

        cart.setOnClickListener(goCarrello);

        Log.d("TAG", "ListView creata: listView=" + listView);

        ProdottiAdapter prodottiAdapter = new ProdottiAdapter(this, prodotti);

        Log.d("TAG", "ArrayAdapter creato: arrayAdapter=" + prodottiAdapter);

        listView.setAdapter(prodottiAdapter);

        Log.d("TAG", "fatto!");

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

    View.OnClickListener goCarrello = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), Carrello.class);

            if(getIntent().getStringExtra("idMarket") != null) {
                i.putExtra("idMarket", getIntent().getStringExtra("idMarket"));
                Log.d("TAG", "Ho inserito nell'intent il supermercato");
            }

            if(getIntent().getStringExtra("username") != null) {
                i.putExtra("username", getIntent().getStringExtra("username"));
                Log.d("TAG", "Ho inserito nell'intent l'username");
            }

            i.putExtra("prev", "list");
            Log.d("TAG", "Ho inserito nell'intent il prev");

            startActivity(i);
            finish();
        }
    };

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, SceltaIndirizzoActivity.class);
        Log.d("TAG", "Ritorniamo alla scelta indirizzo dall'activity UtenteListaProdotti");

        if(getIntent().getStringExtra("idMarket") != null) {
            i.putExtra("idMarket", getIntent().getStringExtra("idMarket"));
            Log.d("TAG", "Ho inserito nell'intent il supermercato");
        }
        if(getIntent().getStringExtra("username") != null) {
            i.putExtra("username", getIntent().getStringExtra("username"));
            Log.d("TAG", "Ho inserito nell'intent l'username");
        }

        startActivity(i);
        super.onBackPressed();
    }

}
