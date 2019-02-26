package com.unisa.ium.revidaliam.revidaliam.utente;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.unisa.ium.revidaliam.revidaliam.OrdiniAdapter;
import com.unisa.ium.revidaliam.revidaliam.ProdottiAdapter;
import com.unisa.ium.revidaliam.revidaliam.R;
import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.db.OrdineBean;
import com.unisa.ium.revidaliam.revidaliam.db.ProdottoBean;

public class StoricoOrdini extends AppCompatActivity {

    public ListView listView;
    private RelativeLayout layoutLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utente_activity_lista_ordini);

        String username = getIntent().getStringExtra("username");

        DBHelper dbHelper = new DBHelper(this);
        OrdineBean[] ordini = dbHelper.retrieveAllOrdineByUserContacts(username);

        listView = findViewById(R.id.mylistview);

        Log.d("TAG", "ListView creata: listView=" + listView);

        OrdiniAdapter ordiniAdapter = new OrdiniAdapter(this, ordini);

        Log.d("TAG", "ArrayAdapter creato: arrayAdapter=" + ordiniAdapter);

        listView.setAdapter(ordiniAdapter);

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

/*    public void onClick(View v) {
        Intent i = new Intent(v.getContext(), HomeUtente.class);
        Log.d("TAG", "Accediamo alla Home dallo storico ordini");

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
    }*/

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, HomeUtente.class);
        Log.d("TAG", "Ritorniamo alla Home dall'activity StoricoOrdini");

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
