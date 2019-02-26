package com.unisa.ium.revidaliam.revidaliam.utente;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.unisa.ium.revidaliam.revidaliam.Cart;
import com.unisa.ium.revidaliam.revidaliam.R;
import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.db.ProdottoBean;
import com.unisa.ium.revidaliam.revidaliam.db.RigaOrdineBean;

import java.text.DecimalFormat;

public class DettagliProdotto extends AppCompatActivity {

    private TextView nomeProdotto;
    private TextView descrizioneProdotto;
    private TextView prezzo;
    private ImageView immagineProdotto;
    private NumberPicker counter;
    private Button aggiungiCarrello;
    private ProdottoBean thisProduct;
    private NumberPicker np;
    private RelativeLayout layoutLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utente_activity_dettagli_prodotto);

        nomeProdotto = findViewById(R.id.nomeProdotto);
        descrizioneProdotto = findViewById(R.id.descrizioneProdotto);
        prezzo = findViewById(R.id.prezzo);
        immagineProdotto = findViewById(R.id.imageProduct);
        counter = findViewById(R.id.counter);
        aggiungiCarrello = findViewById(R.id.aggiungiprodotto);
        np = findViewById(R.id.counter);


        np.setMinValue(1);
        np.setMaxValue(10);
        np.setWrapSelectorWheel(false);
        np.setValue(1);

        DBHelper db = new DBHelper(this);

        int idProduct = getIntent().getIntExtra("idproduct", -1);

        thisProduct = db.retrieveProdottoContacts(idProduct + "");

        if (thisProduct != null) {
            nomeProdotto.setText(thisProduct.getNome());
            descrizioneProdotto.setText(thisProduct.getDescrizione());
            prezzo.setText(new DecimalFormat("0.00").format(thisProduct.getPrezzo()) + "€");
        }

        aggiungiCarrello.setOnClickListener(addAtCart);

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

    View.OnClickListener addAtCart = new View.OnClickListener() {

        public void onClick(View v) {

            RigaOrdineBean rob = new RigaOrdineBean();
            rob.setIdProdotto(thisProduct.getIdProdotto());
            rob.setIdSupermercato(getIntent().getStringExtra("idMarket"));
            rob.setQuantita(counter.getValue());

            Intent i = new Intent(v.getContext(), UtenteListaProdottiActivity.class);
            if (getIntent().getStringExtra("idMarket") != null) {
                i.putExtra("idMarket", getIntent().getStringExtra("idMarket"));
                Log.d("TAG", "Ho inserito nell'intent il supermercato");
            }
            if (getIntent().getStringExtra("username") != null) {
                i.putExtra("username", getIntent().getStringExtra("username"));
                Log.d("TAG", "Ho inserito nell'intent l'username");
            }

            Cart.addProduct(rob);
            Toast.makeText(v.getContext(), "Il prodotto è stato aggiunto con successo al Carrello", Toast.LENGTH_LONG).show();
            Log.d("TAG", "aggiunto al carrello il prodotto id: " + thisProduct.getIdProdotto());
            startActivity(i);
            finish();
        }
    };

    @Override
    public void onBackPressed() {
        Intent i;

        if (getIntent().getStringExtra("prev").equals("list")) {
            i = new Intent(this, UtenteListaProdottiActivity.class);
            Log.d("TAG", "Ritorniamo alla lista prodotti dall'activity DettaglioProdotto");
        } else {
            i = new Intent(this, Carrello.class);
            i.putExtra("prev", getIntent().getStringExtra("prev2"));
            Log.d("TAG", "Ritorniamo al Carrello dall'activity DettaglioProdotto");
        }

        if (getIntent().getStringExtra("idMarket") != null) {
            i.putExtra("idMarket", getIntent().getStringExtra("idMarket"));
            Log.d("TAG", "Ho inserito nell'intent il supermercato");
        }
        if (getIntent().getStringExtra("username") != null) {
            i.putExtra("username", getIntent().getStringExtra("username"));
            Log.d("TAG", "Ho inserito nell'intent l'username");
        }

        startActivity(i);
        super.onBackPressed();
    }

}
