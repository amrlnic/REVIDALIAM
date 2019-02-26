package com.unisa.ium.revidaliam.revidaliam.volontario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.unisa.ium.revidaliam.revidaliam.R;
import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.db.OrdineBean;
import com.unisa.ium.revidaliam.revidaliam.db.RichiestaBean;
import com.unisa.ium.revidaliam.revidaliam.db.RigaOrdineBean;
import com.unisa.ium.revidaliam.revidaliam.db.SupermercatoBean;
import com.unisa.ium.revidaliam.revidaliam.db.UtenteBean;

import java.util.ArrayList;

public class DettaglioRichiestaAccettataVolontario extends AppCompatActivity {
    DBHelper db;
    RichiestaBean rb;
    OrdineBean ob;
    UtenteBean ub;
    SupermercatoBean sb;
    TextView nome;
    TextView username;
    TextView numeroProdotti;
    TextView indirizzo;
    TextView indirizzoSupermercato;
    TextView prezzoTotale;
    Button listaProdotti;
    private RelativeLayout layoutLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volontario_activity_dettaglio_richiesta_accettata);
        db=new DBHelper(getApplicationContext());
        int idRichiesta=getIntent().getIntExtra("idrichiesta",0);
        Log.d("idRichiesta",idRichiesta+"");
        rb=db.retrieveRichiestaContact(idRichiesta);
        ob=db.retrieveOrdineContact(rb.getIdOrdine());
        ub=db.retrieveUtenteContacts(ob.getIdUtente());
        RigaOrdineBean[] rob_list=db.retrieveAllRigaOrdineByOrdineContacts(rb.getIdOrdine());
        String idSupermercato =db.retrieveSupermercatoByOrdine(ob.getIdOrdine());
        sb=db.retrieveSupermercatoBeanByOrdine(idSupermercato);
        int nprodotti=db.retrieveCountRigheOrdineContacts(ob.getIdOrdine());

        listaProdotti=findViewById(R.id.listaprodotti);
        nome=findViewById(R.id.nome);
        username=findViewById(R.id.username);
        numeroProdotti=findViewById(R.id.numeroprodotti);
        listaProdotti.setOnClickListener(goToListaProdotti);
        Log.d("edittextnprodotti",numeroProdotti.getText().toString());
        indirizzo=findViewById(R.id.indirizzo);
        indirizzoSupermercato=findViewById(R.id.indirizzosupermercato);
        prezzoTotale=findViewById(R.id.prezzoTotale);
        prezzoTotale.setText(getIntent().getFloatExtra("prezzoTotale",(float) 9.99)+"");

        nome.setText(ub.getNome()+" "+ub.getCognome());
        username.setText(ub.getUsername());
        Log.d("numeroProdotti",nprodotti+"");
        numeroProdotti.setText(nprodotti+"");
        indirizzo.setText(ub.getIndirizzo());
        Log.d("Supermercato e indirizzo", sb.getUsername()+" "+sb.getIndirizzo());
        indirizzoSupermercato.setText(sb.getIndirizzo());
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


    View.OnClickListener goToListaProdotti = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(v.getContext(), ListaProdottiVolontario.class);
            intent.putExtra("idOrdine",ob.getIdOrdine());
            startActivity(intent);
        }
    };


}
