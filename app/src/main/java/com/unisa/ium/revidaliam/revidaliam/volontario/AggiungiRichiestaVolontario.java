package com.unisa.ium.revidaliam.revidaliam.volontario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.RelativeLayout;
import com.unisa.ium.revidaliam.revidaliam.R;
import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.db.OrdineBean;
import com.unisa.ium.revidaliam.revidaliam.db.ProdottoBean;
import com.unisa.ium.revidaliam.revidaliam.db.RichiestaBean;
import com.unisa.ium.revidaliam.revidaliam.db.RigaOrdineBean;
import com.unisa.ium.revidaliam.revidaliam.db.UtenteBean;
import com.unisa.ium.revidaliam.revidaliam.utente.HomeUtente;

import java.util.ArrayList;

public class AggiungiRichiestaVolontario extends AppCompatActivity {


    private String username;
    private EditText cityEditText;
    private EditText raggioEditText;
    private Button indirizzoButton;
    private String city;
    private DBHelper db;
    private RelativeLayout layoutLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volontario_activity_ricerca_richieste);
        //Recuperiamo l'username dalla precedente activity
        username = getIntent().getStringExtra("username");
        db =new DBHelper(this.getApplicationContext());
        UtenteBean ub=db.retrieveUtenteContacts(username);
        cityEditText=findViewById(R.id.citta);
        raggioEditText=findViewById(R.id.raggio);
        indirizzoButton=findViewById(R.id.ricercaButton);
        indirizzoButton.setOnClickListener(goToListaRichieste);
        layoutLogo = findViewById(R.id.layoutLogo);
        layoutLogo.setOnClickListener(homeLink);
    }

    View.OnClickListener homeLink = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            {
                Intent i = new Intent(v.getContext(), HomeVolontario.class);
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


    View.OnClickListener goToListaRichieste = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            city=cityEditText.getText().toString();
            Intent intent = new Intent(v.getContext(), ListaRichiesteVolontario.class);
            intent.putExtra("username",username);
            intent.putExtra("city",city);
            startActivity(intent);
        }
    };







}
