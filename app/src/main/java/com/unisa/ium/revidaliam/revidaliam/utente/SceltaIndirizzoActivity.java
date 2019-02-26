package com.unisa.ium.revidaliam.revidaliam.utente;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.unisa.ium.revidaliam.revidaliam.R;

public class SceltaIndirizzoActivity extends AppCompatActivity {

    Button indirizzoCorrente;
    Button inserisciIndirizzo;
    private RelativeLayout layoutLogo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utente_activity_scelta_indirizzo);
        inserisciIndirizzo = findViewById(R.id.inserisciIndirizzo);
        indirizzoCorrente = findViewById(R.id.indirizzoCorrente);

        inserisciIndirizzo.setOnClickListener(goInserisciIndirizzo);
        indirizzoCorrente.setOnClickListener(goSceltaSupermercato);

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
    View.OnClickListener goInserisciIndirizzo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), InserisciIndirizzo.class);
            Log.d("TAG", "Andiamo nell'activity InserisciIndirizzo");

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

    View.OnClickListener goSceltaSupermercato = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), SceltaSupermercato.class);
            Log.d("TAG", "Andiamo nell'activity Scelta Supermercato");

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

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, HomeUtente.class);
        Log.d("TAG", "Ritorniamo alla Home dall'activity SceltaIndirizzo");

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
