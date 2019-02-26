package com.unisa.ium.revidaliam.revidaliam.volontario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.unisa.ium.revidaliam.revidaliam.OrdiniAdapter;
import com.unisa.ium.revidaliam.revidaliam.R;
import com.unisa.ium.revidaliam.revidaliam.RichiestaAdapter;
import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.db.OrdineBean;
import com.unisa.ium.revidaliam.revidaliam.db.ProdottoBean;
import com.unisa.ium.revidaliam.revidaliam.db.RichiestaBean;
import com.unisa.ium.revidaliam.revidaliam.db.RigaOrdineBean;
import com.unisa.ium.revidaliam.revidaliam.db.UtenteBean;

import java.util.ArrayList;

public class ListaRichiesteVolontario extends AppCompatActivity {
    private DBHelper db;

    public ListView listView;
    private RelativeLayout layoutLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volontario_activity_lista_richieste);
        listView = findViewById(R.id.mylistview);
        String city = getIntent().getStringExtra("city");
        db = new DBHelper(this.getApplicationContext());
        ArrayList<RichiestaBean> rb_list = db.retrieveRichiestaByCityContacts(city);
        Log.d("rb_list",rb_list.get(0).getIdOrdine()+"");
        Log.d("rb_Size:",rb_list.size()+"");
        ArrayList<Float> prezzi_list =new ArrayList<Float>();
        ArrayList<String> supermercati_list =new ArrayList<String>();
        ArrayList<String> nomi_list=new ArrayList<String>();
        for (int i = 0; i < rb_list.size(); i++) {
            //prendo i dati per ogni richiesta
            int idOrdine = rb_list.get(i).getIdOrdine();
            String idSupermercato = db.retrieveSupermercatoByOrdine(idOrdine);
            RigaOrdineBean[] rigaList = db.retrieveAllRigaOrdineByOrdineContacts(idOrdine);
            float prezzo = 0; // il prezzo totale di ogni richiesta
            for (int count = 0; count < rigaList.length; count++) {
                ProdottoBean pb = db.retrieveProdottoContacts((rigaList[count].getIdProdotto() + ""));
                prezzo += ((ProdottoBean) pb).getPrezzo() * rigaList[count].getQuantita();
            }
            OrdineBean ob = db.retrieveOrdineContact(idOrdine);//nome e cognome di ogni richiesta
            UtenteBean ub = db.retrieveUtenteContacts(ob.getIdUtente());
            String nome = ub.getNome();
            String cognome = ub.getCognome();
            String nomecognome = nome + " " + cognome;
            prezzi_list.add(i,prezzo);
            nomi_list.add(i,nomecognome);
            supermercati_list.add(i,idSupermercato);

        }
        RichiestaAdapter richiesteAdapter = new RichiestaAdapter(this,rb_list,prezzi_list,nomi_list,supermercati_list);
        listView.setAdapter(richiesteAdapter);
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


}
