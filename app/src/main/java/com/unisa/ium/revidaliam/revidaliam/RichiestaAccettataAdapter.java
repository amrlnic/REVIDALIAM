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
import com.unisa.ium.revidaliam.revidaliam.db.RichiestaBean;
import com.unisa.ium.revidaliam.revidaliam.utente.DettagliProdotto;
import com.unisa.ium.revidaliam.revidaliam.volontario.DettaglioRichiestaAccettataVolontario;
import com.unisa.ium.revidaliam.revidaliam.volontario.DettaglioRichiestaVolontario;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class RichiestaAccettataAdapter extends BaseAdapter {

    private final Context mContext;
    private final ArrayList<RichiestaBean> richiesta_list;
    private final ArrayList<Float> prezzi;
    private final ArrayList<String> nomi;
    private final ArrayList<String> supermercati;

    public RichiestaAccettataAdapter(Context mContext, ArrayList<RichiestaBean> richiesta_list,ArrayList<Float> prezzi,ArrayList<String> nomi,ArrayList<String> supermercati) {
        this.mContext = mContext;
        this.richiesta_list = richiesta_list;
        this.prezzi = prezzi;
        this.nomi = nomi;
        this.supermercati=supermercati;
    }

    @Override
    public int getCount() {
        return richiesta_list.size();
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
        final RichiestaBean rb= richiesta_list.get(position);
        final Float r_prezzo=prezzi.get(position);
        final String r_nome =nomi.get(position);
        final String r_supermercato=supermercati.get(position);
        // 2
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.list_element, null);
        }

        // 3
        final LinearLayout product = convertView.findViewById(R.id.product);
        final TextView nomeUtente = convertView.findViewById(R.id.nomeProdotto);
        final TextView prezzo = convertView.findViewById(R.id.prezzo);
        final ImageView image = convertView.findViewById(R.id.imageProduct);

        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show Toast
                //Toast.makeText(view.getContext(), rb.getIdRichiesta(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(view.getContext(), DettaglioRichiestaAccettataVolontario.class);

                if(((Activity) mContext).getIntent().getStringExtra("idrichiesta") != null)
                    i.putExtra("idrichiesta", ((Activity) mContext).getIntent().getStringExtra("idrichiesta"));
                else{
                    Log.d("idrichiesta","null");
                }
                if(((Activity) mContext).getIntent().getStringExtra("username") != null) {
                    Log.d("UsernameRichiestaAdapter",((Activity) mContext).getIntent().getStringExtra("username"));
                    i.putExtra("username", ((Activity) mContext).getIntent().getStringExtra("username"));
                }
                i.putExtra("idrichiesta", rb.getIdRichiesta());
                Log.d("TAG", "ID della richiesta: "+ rb.getIdRichiesta());
                mContext.startActivity(i);
                i.putExtra("prezzoTotale",r_prezzo);
            }
        });

        // 4

        prezzo.setText(new DecimalFormat("0.00").format(r_prezzo) + "€");
        nomeUtente.setText(r_nome);
        /*if(r_supermercato.equals("coopsalerno")){

        }*/
        image.setImageResource(R.drawable.image_utente);
        return convertView;
    }
}
