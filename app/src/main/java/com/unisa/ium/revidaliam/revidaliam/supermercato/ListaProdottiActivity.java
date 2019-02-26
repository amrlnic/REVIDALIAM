package com.unisa.ium.revidaliam.revidaliam.supermercato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.unisa.ium.revidaliam.revidaliam.AdapterProdottiSupermercato;
import com.unisa.ium.revidaliam.revidaliam.ProdottiAdapter;
import com.unisa.ium.revidaliam.revidaliam.R;
import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.db.ProdottoBean;

public class ListaProdottiActivity extends AppCompatActivity {

    public ListView listView;
    ImageButton elimina;
    private RelativeLayout layoutLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supermercato_lista_prodotti);

        String username = getIntent().getStringExtra("username");

        DBHelper dbHelper = new DBHelper(this);
        ProdottoBean[] prodotti = dbHelper.retrieveAllProdottoFromMarketContacts(username);

        listView = findViewById(R.id.mylistview2);
        AdapterProdottiSupermercato prodottiAdapter = new AdapterProdottiSupermercato(this, prodotti, username);
        listView.setAdapter(prodottiAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String  str  = listView.getItemAtPosition(position).toString();
                // Show Toast
                Toast.makeText(getApplicationContext(),
                        "Click su posizione n."+position+": " +str, Toast.LENGTH_LONG)
                        .show();
            }
        });

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
