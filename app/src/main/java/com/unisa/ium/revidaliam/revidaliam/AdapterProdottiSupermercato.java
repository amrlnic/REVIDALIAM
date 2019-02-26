package com.unisa.ium.revidaliam.revidaliam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.db.ProdottoBean;
import com.unisa.ium.revidaliam.revidaliam.supermercato.DettagliSupermercato;
import com.unisa.ium.revidaliam.revidaliam.supermercato.DettaglioProdottoSupermercato;
import com.unisa.ium.revidaliam.revidaliam.supermercato.ListaProdottiActivity;
import com.unisa.ium.revidaliam.revidaliam.utente.DettagliProdotto;

public class AdapterProdottiSupermercato extends BaseAdapter {
    private final Context mContext;
    private final ProdottoBean[] prodotti;
    private String username;

    public AdapterProdottiSupermercato(Context context, ProdottoBean[] prodotti, String username) {
        this.mContext = context;
        this.prodotti = prodotti;
        this.username = username;
    }

    // 2
    @Override
    public int getCount() {
        return prodotti.length;
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return null;
    }

    // 5
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1
        final ProdottoBean prodotto = prodotti[position];

        // 2
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.list_element_supermercato, null);
        }

        // 3
        final LinearLayout product = convertView.findViewById(R.id.product);
        final TextView nomeProdotto = convertView.findViewById(R.id.nomeProdotto);
        final TextView prezzo = convertView.findViewById(R.id.prezzo);
        final ImageView image = convertView.findViewById(R.id.imageProduct);
        final ImageButton imm = convertView.findViewById(R.id.elimina);

        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show Toast
                Toast.makeText(view.getContext(), prodotto.getNome(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(view.getContext(), DettaglioProdottoSupermercato.class);
                i.putExtra("idproduct", prodotto.getIdProdotto());
                Log.d("TAG", "ID del prodotto: "+ prodotto.getIdProdotto());
                mContext.startActivity(i);
            }
        });

        imm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ListaProdottiActivity.class);
                int pr = prodotto.getIdProdotto();
                DBHelper dbHelper = new DBHelper(((Activity)mContext).getApplicationContext());
                dbHelper.deleteSupermercato(pr);
                i.putExtra("username", username);



                ((Activity)mContext).startActivity(i);
                ((Activity) mContext).finish();
            }
        });

        // 4
        nomeProdotto.setText(prodotto.getNome());

        return convertView;
    }
}
