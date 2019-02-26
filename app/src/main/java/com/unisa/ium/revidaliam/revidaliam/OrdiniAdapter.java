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
import com.unisa.ium.revidaliam.revidaliam.db.OrdineBean;
import com.unisa.ium.revidaliam.revidaliam.db.RigaOrdineBean;
import com.unisa.ium.revidaliam.revidaliam.utente.DettagliOrdine;

import java.text.DecimalFormat;

public class OrdiniAdapter extends BaseAdapter {

    private final Context mContext;
    private final OrdineBean[] ordini;

    public OrdiniAdapter(Context mContext, OrdineBean[] ordini) {
        this.mContext = mContext;
        this.ordini = ordini;
    }

    @Override
    public int getCount() {
        return ordini.length;
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
        final OrdineBean ordine = ordini[position];

        // 2
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.order_element, null);
        }

        // 3
        final LinearLayout order = convertView.findViewById(R.id.order);
        final TextView idOrdine = convertView.findViewById(R.id.idOrdine);
        final TextView prezzo = convertView.findViewById(R.id.prezzo);
        final ImageView image = convertView.findViewById(R.id.imageOrder);

        DBHelper db = new DBHelper(mContext);
        RigaOrdineBean[] rigaProdotti = db.retrieveAllRigaOrdineByOrdineContacts(ordine.getIdOrdine());
        float tot = 0;
        for (int i = 0; i < rigaProdotti.length; i++) {
            tot += db.retrieveProdottoContacts(rigaProdotti[i].getIdProdotto() + "").getPrezzo() * rigaProdotti[i].getQuantita();
        }

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show Toast
               // Toast.makeText(view.getContext(), ordine.getIdOrdine() + "", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(view.getContext(), DettagliOrdine.class);

                if (((Activity) mContext).getIntent().getStringExtra("idMarket") != null)
                    i.putExtra("idMarket", ((Activity) mContext).getIntent().getStringExtra("idMarket"));
                if (((Activity) mContext).getIntent().getStringExtra("username") != null)
                    i.putExtra("username", ((Activity) mContext).getIntent().getStringExtra("username"));

                i.putExtra("idorder", ordine.getIdOrdine());

                Log.d("TAG", "ID dell'ordine: " + ordine.getIdOrdine());
                mContext.startActivity(i);
                ((Activity) mContext).finish();
            }
        });

        // 4
        idOrdine.setText("Codice Ordine: " + ordine.getIdOrdine());
        prezzo.setText("Totale Spesa: " + new DecimalFormat("0.00").format(tot));

        return convertView;
    }
}
