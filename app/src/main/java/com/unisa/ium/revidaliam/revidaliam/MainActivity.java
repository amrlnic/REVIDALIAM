package com.unisa.ium.revidaliam.revidaliam;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.supermercato.LoginSupermercatoActivity;
import com.unisa.ium.revidaliam.revidaliam.utente.LoginUtenteActivity;
import com.unisa.ium.revidaliam.revidaliam.volontario.LoginVolontarioActivity;

public class MainActivity extends AppCompatActivity {

    Button utenteButton;
    Button supermercatoButton;
    Button volontarioButton;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper db = new DBHelper(getApplicationContext());
        utenteButton = findViewById(R.id.utente);
        supermercatoButton = findViewById(R.id.supermercato);
        volontarioButton = findViewById(R.id.volontario);
        utenteButton.setOnClickListener(goUtenteLogin);
        supermercatoButton.setOnClickListener(goSupermercatoLogin);
        volontarioButton.setOnClickListener(goVolontarioLogin);
    }

    View.OnClickListener goUtenteLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), LoginUtenteActivity.class);
            Log.d("TAG", "Accediamo al login Utente");
            startActivity(i);
        }
    };

    View.OnClickListener goSupermercatoLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), LoginSupermercatoActivity.class);
            Log.d("TAG", "Accediamo al login Supermercato");
            startActivity(i);
        }
    };

    View.OnClickListener goVolontarioLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), LoginVolontarioActivity.class);
            Log.d("TAG", "Accediamo al login Volontario");
            startActivity(i);
        }
    };
}
