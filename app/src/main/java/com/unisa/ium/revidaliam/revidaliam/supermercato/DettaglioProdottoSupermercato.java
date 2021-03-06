package com.unisa.ium.revidaliam.revidaliam.supermercato;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.unisa.ium.revidaliam.revidaliam.R;
import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.db.ProdottoBean;

public class DettaglioProdottoSupermercato extends AppCompatActivity {

    private TextView nomeProdotto;
    private TextView descrizioneProdotto;
    private TextView prezzo;
    private ImageView immagineProdotto;
    private NumberPicker counter;
    private Button aggiungiCarrello;
    private RelativeLayout layoutLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supermercato_dettaglio_prodotto);

        nomeProdotto = findViewById(R.id.nomeProdotto);
        descrizioneProdotto = findViewById(R.id.descrizioneProdotto);
        prezzo = findViewById(R.id.prezzo);
        immagineProdotto = findViewById(R.id.imageProduct);
        counter = findViewById(R.id.counter);
        aggiungiCarrello = findViewById(R.id.aggiungiprodotto);

        DBHelper db = new DBHelper(this);

        int idProduct = getIntent().getIntExtra("idproduct", -1);

        ProdottoBean thisProduct = db.retrieveProdottoContacts(idProduct+"");

        if(thisProduct != null) {
            nomeProdotto.setText(thisProduct.getNome());
            descrizioneProdotto.setText(thisProduct.getDescrizione());
            prezzo.setText(thisProduct.getPrezzo() + "€");
        }
        layoutLogo = findViewById(R.id.layoutLogo);
        layoutLogo.setOnClickListener(homeLink);
    }

    View.OnClickListener homeLink = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            {
                Intent i = new Intent(v.getContext(), HomeSupermercato.class);
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
}
