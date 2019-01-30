package com.example.iumproject;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.iumproject.Supermercato.LoginSupermercatoActivity;

public class MainActivity extends AppCompatActivity {

    Button utenteButton;
    Button supermercatoButton;
    Button volontarioButton;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        utenteButton = findViewById(R.id.utente);
        supermercatoButton = findViewById(R.id.supermercato);
        utenteButton.setOnClickListener(goUtenteLogin);
        supermercatoButton.setOnClickListener(goSupermercatoLogin);
    }

    View.OnClickListener goUtenteLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                  Intent i = new Intent(v.getContext(), LoginActivity.class);
                  startActivity(i);
        }
    };

    View.OnClickListener goSupermercatoLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), LoginSupermercatoActivity.class);
            startActivity(i);
        }
    };
}
