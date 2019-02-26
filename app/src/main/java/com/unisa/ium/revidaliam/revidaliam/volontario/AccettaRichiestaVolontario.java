package com.unisa.ium.revidaliam.revidaliam.volontario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.unisa.ium.revidaliam.revidaliam.MainActivity;
import com.unisa.ium.revidaliam.revidaliam.R;
import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.db.OrdineBean;
import com.unisa.ium.revidaliam.revidaliam.db.RichiestaBean;
import com.unisa.ium.revidaliam.revidaliam.db.UtenteBean;
import com.unisa.ium.revidaliam.revidaliam.db.VolontarioBean;

public class AccettaRichiestaVolontario extends AppCompatActivity {
    TextView clienteRichiesta;
    Button home;
    String username;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volontario_activity_accetta_richiesta);

        int idRichiesta = getIntent().getIntExtra("idRichiesta", 0);
        db = new DBHelper(this.getApplicationContext());
        username = getIntent().getStringExtra("username").toString();
        Log.d("usernameAccettaRichiesta",username);
        RichiestaBean rb = db.retrieveRichiestaContact(idRichiesta);
        OrdineBean ob = db.retrieveOrdineContact(rb.getIdOrdine());
        UtenteBean ub = db.retrieveUtenteContacts(ob.getIdUtente());
        rb.setIdUtente(username);
        db.updateContact(rb);
        clienteRichiesta = findViewById(R.id.cliente);
        home = findViewById(R.id.home);
        clienteRichiesta.setText(ub.getNome() + " " + ub.getCognome() + " ti Aspetta!");
        home.setOnClickListener(goToHome);


    }
    View.OnClickListener goToHome = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            VolontarioBean vb =db.retrieveVolontarioContacts(username);
            Intent i = new Intent(v.getContext(), HomeVolontario.class);
            i.putExtra("username",vb.getUsername());
            i.putExtra("password",vb.getPassword());
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    };
}
