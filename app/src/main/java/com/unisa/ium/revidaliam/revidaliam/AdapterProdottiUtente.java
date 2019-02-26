package com.unisa.ium.revidaliam.revidaliam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.db.ProdottoBean;
import com.unisa.ium.revidaliam.revidaliam.db.RigaOrdineBean;
import com.unisa.ium.revidaliam.revidaliam.supermercato.ListaProdottiActivity;
import com.unisa.ium.revidaliam.revidaliam.utente.DettagliProdotto;
import com.unisa.ium.revidaliam.revidaliam.utente.DettaglioProdottoSupermercato;

import java.text.DecimalFormat;

public class AdapterProdottiUtente extends BaseAdapter {

    private final Context mContext;
    private final RigaOrdineBean[] rigaProdotti;

    public AdapterProdottiUtente(Context mContext, RigaOrdineBean[] rigaProdotti) {
        this.mContext = mContext;
        this.rigaProdotti = rigaProdotti;
    }

    @Override
    public int getCount() {
        return rigaProdotti.length;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1
        final RigaOrdineBean rigaProdotto = rigaProdotti[position];

        // 2
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.cart_element, null);
        }

        // 3
        final LinearLayout product = convertView.findViewById(R.id.product);
        final TextView nomeProdotto = convertView.findViewById(R.id.nomeProdotto);
        final TextView prezzo = convertView.findViewById(R.id.prezzo);
        final TextView quantita = convertView.findViewById(R.id.quantita);
        final ImageView image = convertView.findViewById(R.id.imageProduct);


        // 4
        DBHelper db = new DBHelper(mContext.getApplicationContext());
        ProdottoBean pb = db.retrieveProdottoContacts(rigaProdotto.getIdProdotto() + "");
        nomeProdotto.setText(pb.getNome());
        quantita.setText("Quantità: " + rigaProdotto.getQuantita());
        prezzo.setText(new DecimalFormat("0.00").format(pb.getPrezzo()) + "€");

        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show Toast
                //Toast.makeText(view.getContext(), rigaProdotto.getIdRigaOrdine() + "", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(view.getContext(), DettaglioProdottoSupermercato.class);
                i.putExtra("idproduct", rigaProdotto.getIdProdotto());
                Log.d("TAG", "ID del rigaProdotto: " + rigaProdotto.getIdProdotto());

                i.putExtra("idorder", ((Activity)mContext).getIntent().getIntExtra("idorder", -1));

                if (((Activity)mContext).getIntent().getStringExtra("idMarket") != null)
                    i.putExtra("idMarket", ((Activity)mContext).getIntent().getStringExtra("idMarket"));
                if (((Activity)mContext).getIntent().getStringExtra("username") != null)
                    i.putExtra("username", ((Activity)mContext).getIntent().getStringExtra("username"));
                mContext.startActivity(i);
            }
        });

        return convertView;
    }
}

