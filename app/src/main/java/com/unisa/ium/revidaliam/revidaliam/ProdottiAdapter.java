package com.unisa.ium.revidaliam.revidaliam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.unisa.ium.revidaliam.revidaliam.db.ProdottoBean;
import com.unisa.ium.revidaliam.revidaliam.utente.DettagliProdotto;

import java.text.DecimalFormat;

public class ProdottiAdapter extends BaseAdapter {

    private final Context mContext;
    private final ProdottoBean[] prodotti;

    public ProdottiAdapter(Context mContext, ProdottoBean[] prodotti) {
        this.mContext = mContext;
        this.prodotti = prodotti;
    }

    @Override
    public int getCount() {
        return prodotti.length;
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
        final ProdottoBean prodotto = prodotti[position];

        // 2
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.list_element, null);
        }

        // 3
        final LinearLayout product = convertView.findViewById(R.id.product);
        final TextView nomeProdotto = convertView.findViewById(R.id.nomeProdotto);
        final TextView prezzo = convertView.findViewById(R.id.prezzo);
        final ImageView image = convertView.findViewById(R.id.imageProduct);

        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show Toast
                //Toast.makeText(view.getContext(), prodotto.getNome(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(view.getContext(), DettagliProdotto.class);

                if(((Activity) mContext).getIntent().getStringExtra("idMarket") != null)
                    i.putExtra("idMarket", ((Activity) mContext).getIntent().getStringExtra("idMarket"));
                if(((Activity) mContext).getIntent().getStringExtra("username") != null)
                    i.putExtra("username", ((Activity) mContext).getIntent().getStringExtra("username"));

                i.putExtra("prev", "list");
                i.putExtra("idproduct", prodotto.getIdProdotto());
                Log.d("TAG", "ID del prodotto: "+ prodotto.getIdProdotto());
                mContext.startActivity(i);
                ((Activity) mContext).finish();
            }
        });

        // 4
        nomeProdotto.setText(prodotto.getNome());
        prezzo.setText(new DecimalFormat("0.00").format(prodotto.getPrezzo()) + "€");

        return convertView;
    }
}
