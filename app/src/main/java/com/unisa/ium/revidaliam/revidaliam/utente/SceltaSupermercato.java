package com.unisa.ium.revidaliam.revidaliam.utente;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.unisa.ium.revidaliam.revidaliam.R;
import com.unisa.ium.revidaliam.revidaliam.SuperMarketAdapter;
import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.db.SupermercatoBean;

public class SceltaSupermercato extends AppCompatActivity {

    private TextView ricerca;
    private GridView gridView;
    private RelativeLayout layoutLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utente_activity_scelta_supermercato);

        DBHelper dbHelper = new DBHelper(this);
        SupermercatoBean[] supermercati = dbHelper.retrieveAllSupermercatoContacts();

        ricerca = findViewById(R.id.ricerca);
        gridView = findViewById(R.id.gridview);
        SuperMarketAdapter superMarketAdapter = new SuperMarketAdapter(this, supermercati);
        gridView.setAdapter(superMarketAdapter);
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
        Intent i = new Intent(this, SceltaIndirizzoActivity.class);
        Log.d("TAG", "Ritorniamo alla scelta indirizzo dall'activity HomeUtente");

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
