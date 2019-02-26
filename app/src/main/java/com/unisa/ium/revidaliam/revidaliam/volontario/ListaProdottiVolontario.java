package com.unisa.ium.revidaliam.revidaliam.volontario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import android.widget.RelativeLayout;
import com.unisa.ium.revidaliam.revidaliam.ProdottiAdapter;
import com.unisa.ium.revidaliam.revidaliam.ProdottiVolontarioAdapter;
import com.unisa.ium.revidaliam.revidaliam.R;
import com.unisa.ium.revidaliam.revidaliam.RichiestaAdapter;
import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.db.ProdottoBean;
import com.unisa.ium.revidaliam.revidaliam.db.RigaOrdineBean;

import java.util.ArrayList;

public class ListaProdottiVolontario extends AppCompatActivity {
    ArrayList<ProdottoBean> pb_list;
    RigaOrdineBean[] rob_list;
    ListView listView;
    private RelativeLayout layoutLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volontario_activity_lista_prodotti);
        DBHelper db=new DBHelper(this.getApplicationContext());
        int idOrdine =getIntent().getIntExtra("idOrdine",0);
        pb_list=new ArrayList<>();
        listView = findViewById(R.id.mylistview);
        rob_list=db.retrieveAllRigaOrdineByOrdineContacts(idOrdine);
        Log.d(" ProdottiVolontario",idOrdine+"");
        int i=0;
        for(i=0;i<rob_list.length;i++){
            pb_list.add(db.retrieveProdottoContacts(rob_list[i].getIdProdotto()+""));
            Log.d("pb_nome","Elemento"+i+":"+pb_list.get(i).getNome());
        }
        Log.d("pb_listsize",i+"");
        ProdottiVolontarioAdapter prodottiVolontarioAdapter=new ProdottiVolontarioAdapter(this,pb_list);
        listView.setAdapter(prodottiVolontarioAdapter);
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
