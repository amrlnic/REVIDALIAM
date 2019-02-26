package com.unisa.ium.revidaliam.revidaliam.supermercato;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.unisa.ium.revidaliam.revidaliam.R;
import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.db.ProdottoBean;
import com.unisa.ium.revidaliam.revidaliam.db.ProdottoSupermercatoBean;
import com.unisa.ium.revidaliam.revidaliam.utente.HomeUtente;

import java.io.IOException;

public class AggiungiProdotto extends AppCompatActivity {

    ImageView foto;
    EditText nomeProdotto;
    EditText descrizione;
    EditText prezzo;
    Button aggiungiProdotto;
    RelativeLayout layoutLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supermercato_activity_aggiungi_prodotto);

        foto = findViewById(R.id.foto);
        nomeProdotto = findViewById(R.id.nomeProdotto);
        descrizione = findViewById(R.id.descrizione);
        prezzo = findViewById(R.id.prezzo);
        aggiungiProdotto = findViewById(R.id.aggiungi);
        aggiungiProdotto.setOnClickListener(addProdotto);
        foto.setOnClickListener(addfoto);

    }


    View.OnClickListener addfoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, 0);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK && null != data) {
            Uri contentURI = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                this.foto.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(AggiungiProdotto.this, "Failed!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    View.OnClickListener addProdotto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DBHelper db = new DBHelper(getApplicationContext());

            String username =  getIntent().getStringExtra("username");
            String nome = nomeProdotto.getText().toString();
            String descrizioneProdotto = descrizione.getText().toString();
            float prezzoProdotto = Float.parseFloat(prezzo.getText().toString());

            ProdottoSupermercatoBean supermercato =  new ProdottoSupermercatoBean();

            ProdottoBean prodotto =  new ProdottoBean();
            prodotto.setNome(nome);
            prodotto.setDescrizione(descrizioneProdotto);
            prodotto.setPrezzo(prezzoProdotto);
            db.insertContact(prodotto);

            int newID = db.retrieveMaxProdottoContacts();
            supermercato.setIdProdotto(newID);
            supermercato.setUsernameSupermercato(username);
            db.insertContact(supermercato);
            Toast.makeText(getApplicationContext(),"Prodotto Aggiunto!",Toast.LENGTH_LONG).show();
        }
    };
}
