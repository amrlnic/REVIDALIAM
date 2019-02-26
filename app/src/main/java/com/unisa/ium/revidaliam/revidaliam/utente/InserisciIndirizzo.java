package com.unisa.ium.revidaliam.revidaliam.utente;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.unisa.ium.revidaliam.revidaliam.MainActivity;
import com.unisa.ium.revidaliam.revidaliam.R;

public class InserisciIndirizzo extends AppCompatActivity {

    private EditText indirizzo;
    private EditText citta;
    private EditText provincia;
    private Button conferma;
    private RelativeLayout layoutLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utente_activity_inserisci_indirizzo);

        indirizzo = findViewById(R.id.indirizzo);
        citta = findViewById(R.id.citta);
        provincia = findViewById(R.id.provincia);
        conferma = findViewById(R.id.confermaButton);

        conferma.setOnClickListener(confermaIndirizzo);
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

    View.OnClickListener confermaIndirizzo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String address = indirizzo.getText().toString();
            String city = indirizzo.getText().toString();
            String prov = provincia.getText().toString();

            String completeNewAddress = address + city + ", " + prov;

            Intent i = new Intent(v.getContext(), SceltaSupermercato.class);
            i.putExtra("newAddress", completeNewAddress);

            Toast.makeText(InserisciIndirizzo.this, "Il recapito per questo ordine è stato modificato", Toast.LENGTH_SHORT).show();

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
        Intent i = new Intent(this, SceltaIndirizzoActivity.class);
        Log.d("TAG", "Ritorniamo alla scelta indirizzo dall'activity Carrello");

        if (getIntent().getStringExtra("idMarket") != null)
            i.putExtra("idMarket", getIntent().getStringExtra("idMarket"));
        if (getIntent().getStringExtra("username") != null)
            i.putExtra("username", getIntent().getStringExtra("username"));

        startActivity(i);
        super.onBackPressed();
    }
}
