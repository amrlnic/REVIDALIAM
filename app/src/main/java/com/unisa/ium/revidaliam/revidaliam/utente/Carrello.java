package com.unisa.ium.revidaliam.revidaliam.utente;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.unisa.ium.revidaliam.revidaliam.Cart;
import com.unisa.ium.revidaliam.revidaliam.R;
import com.unisa.ium.revidaliam.revidaliam.db.*;
import com.unisa.ium.revidaliam.revidaliam.RigaProdottoAdapter;

import java.text.DecimalFormat;

public class Carrello extends AppCompatActivity {

    private Button acquistaButton;
    private ListView listaProdotti;
    private TextView prezzo;
    private String username;
    private ProdottoBean[] prodotti;
    private RigaOrdineBean[] rigaProdotti;
    private RelativeLayout layoutLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utente_activity_carrello);

        acquistaButton = findViewById(R.id.acquistaButton);
        listaProdotti = findViewById(R.id.mylistview);
        prezzo = findViewById(R.id.prezzo);

        acquistaButton.setOnClickListener(acquista);


        DBHelper db = new DBHelper(this);
        username = getIntent().getStringExtra("username");

        rigaProdotti = Cart.getCart().toArray(new RigaOrdineBean[Cart.getCart().size()]);
        prodotti = new ProdottoBean[rigaProdotti.length];

        float tot = 0;
        for (int i = 0; i < rigaProdotti.length; i++) {
            prodotti[i] = db.retrieveProdottoContacts(rigaProdotti[i].getIdProdotto() + "");
            tot += prodotti[i].getPrezzo() * rigaProdotti[i].getQuantita();
        }

        prezzo.setText(new DecimalFormat("0.00").format(tot) + "€");

        listaProdotti = findViewById(R.id.mylistview);

        Log.d("DEBUG", "ListView create: listView=" + listaProdotti);

        RigaProdottoAdapter prodottiAdapter = new RigaProdottoAdapter(this, rigaProdotti);

        Log.d("DEBUG", "ArrayAdapter create: arrayAdapter=" + prodottiAdapter);

        listaProdotti.setAdapter(prodottiAdapter);

        Log.d("DEBUG", "Done!");

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

    View.OnClickListener acquista = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {

            DBHelper db = new DBHelper(v.getContext());

            OrdineBean ob = new OrdineBean();
            ob.setIdUtente(username);

            db.insertContact(ob);


            for (int i = 0; i < rigaProdotti.length; i++) {

                RigaOrdineBean rob = new RigaOrdineBean();
                rob.setIdOrdine(db.retrieveMaxOrdineContacts());
                rob.setIdRigaOrdine(i + 1);
                rob.setIdSupermercato(getIntent().getStringExtra("idMarket"));
                rob.setQuantita(rigaProdotti[i].getQuantita());
                rob.setIdProdotto(rigaProdotti[i].getIdProdotto());

                db.insertContact(rob);


            }

            //gibbo
            RichiestaBean rb=new RichiestaBean();
            rb.setIdOrdine(db.retrieveMaxOrdineContacts());
            rb.setIdUtente(null);
            rb.setStato(false);
            db.insertContact(rb);

            Intent i = new Intent(v.getContext(), HomeUtente.class);

            if (getIntent().getStringExtra("username") != null) {
                i.putExtra("username", getIntent().getStringExtra("username"));
                Log.d("TAG", "Ho inserito nell'intent l'username");
            }

            Cart.getCart().clear();

            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setCancelable(true);
            builder.setTitle("Ordine Acquistato");
            builder.setMessage("Congratulazioni!\nIl tuo ordine è stato confermato!");
            builder.setPositiveButton("Torna Alla Home",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(v.getContext(), HomeUtente.class);
                            Log.d("TAG", "Accediamo alla Home dal popup di acquisto effettuato");

                            if (getIntent().getStringExtra("username") != null) {
                                i.putExtra("username", getIntent().getStringExtra("username"));
                                Log.d("TAG", "Ho inserito nell'intent l'username");
                            }

                            startActivity(i);
                            finish();
                        }
                    });
            builder.setNegativeButton("Visualizza Lista Ordini",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(getBaseContext(), StoricoOrdini.class);
                            Log.d("TAG", "Accediamo allo storico ordini dal popup di acquisto effettuato");

                            if (getIntent().getStringExtra("username") != null) {
                                i.putExtra("username", getIntent().getStringExtra("username"));
                                Log.d("TAG", "Ho inserito nell'intent l'username");
                            }

                            startActivity(i);
                            finish();
                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();


        }
    };

    @Override
    public void onBackPressed() {

        Intent i;

        if (getIntent().getStringExtra("prev").equals("list")) {
            i = new Intent(this, UtenteListaProdottiActivity.class);
            Log.d("TAG", "Ritorniamo alla lista prodotti dall'activity Carrello");
        } else {
            i = new Intent(this, HomeUtente.class);
            Log.d("TAG", "Ritorniamo alla Home dall'activity Carrello");
        }

        if (getIntent().getStringExtra("idMarket") != null)
            i.putExtra("idMarket", getIntent().getStringExtra("idMarket"));
        if (getIntent().getStringExtra("username") != null)
            i.putExtra("username", getIntent().getStringExtra("username"));

        startActivity(i);
        super.onBackPressed();
    }
}
