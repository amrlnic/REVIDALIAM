package com.unisa.ium.revidaliam.revidaliam.utente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import com.unisa.ium.revidaliam.revidaliam.MainActivity;
import com.unisa.ium.revidaliam.revidaliam.R;

public class ImpostazioniActivity extends AppCompatActivity {

    private Button logoutButton;
    private Switch switchGeolog;
    private Switch switchNotifiche;
    private Switch switchUpgrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utente_activity_impostazioni);

        logoutButton = findViewById(R.id.logout);
        logoutButton.setOnClickListener(disconnettiUtente);
    }

    View.OnClickListener disconnettiUtente = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);   //permette di non tornare più alla login e registrazione
            startActivity(i);
        }
    };

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, HomeUtente.class);
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
        super.onBackPressed();
    }
}
