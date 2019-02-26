package com.unisa.ium.revidaliam.revidaliam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.unisa.ium.revidaliam.revidaliam.db.SupermercatoBean;
import com.unisa.ium.revidaliam.revidaliam.utente.UtenteListaProdottiActivity;

public class SuperMarketAdapter extends BaseAdapter{
    private final Context mContext;
    private final SupermercatoBean[] supermercati;

    // 1
    public SuperMarketAdapter(Context context, SupermercatoBean[] supermercati) {
        this.mContext = context;
        this.supermercati = supermercati;
    }

    // 2
    @Override
    public int getCount() {
        return supermercati.length;
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
        final SupermercatoBean supermercato = supermercati[position];

        // 2
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.market_layout, null);
        }

        // 3
        final TextView testo = convertView.findViewById(R.id.testo);
        final RelativeLayout market = convertView.findViewById(R.id.market);

        market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(view.getContext(), UtenteListaProdottiActivity.class);

                if(((Activity) mContext).getIntent().getStringExtra("username") != null) {
                    i.putExtra("username", ((Activity) mContext).getIntent().getStringExtra("username"));
                    Log.d("TAG", "Ho inserito nell'intent l'username");
                }
                i.putExtra("idMarket", supermercato.getUsername());
                Log.d("TAG", "Ho inserito nell'intent il supermercato");
                mContext.startActivity(i);
            }
        });

        // 4
        testo.setText(supermercato.getNome());

        return convertView;
    }
}
