package com.unisa.ium.revidaliam.revidaliam.supermercato;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.unisa.ium.revidaliam.revidaliam.R;
import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.db.SupermercatoBean;
import com.unisa.ium.revidaliam.revidaliam.utente.LoginUtenteActivity;

public class RegistrazioneSupermercatoActivity extends AppCompatActivity {

    EditText nomeText;
    EditText cognomeText;
    EditText emailText;
    EditText indirizzoText;
    EditText userEditText;
    EditText pswEditText;
    Button registratiButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supermercato_activity_registrazione);

        nomeText = findViewById(R.id.nome);
        emailText = findViewById(R.id.email);
        indirizzoText = findViewById(R.id.indirizzo);
        userEditText = findViewById(R.id.username);
        pswEditText = findViewById(R.id.password);
        registratiButton = findViewById(R.id.registrati);

        registratiButton.setOnClickListener(registraUtente);

    }

    View.OnClickListener registraUtente = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controllaRegistrazione(); //Controllo dei campi

            String nome = nomeText.getText().toString();
            String email = emailText.getText().toString();
            String indirizzo = indirizzoText.getText().toString();
            String username = userEditText.getText().toString();
            String psw = pswEditText.getText().toString();
            SupermercatoBean s=new SupermercatoBean(username,nome,indirizzo,email,psw);
            DBHelper db=new DBHelper(getApplicationContext());
            db.insertContact(s);
            //Setta i paramentri per la prossima activity
            Intent i = new Intent(v.getContext(), LoginSupermercatoActivity.class);
            Toast.makeText(getApplicationContext(),"Registrazione Effettuata",Toast.LENGTH_LONG);
            startActivity(i);
            finish();
        }
    };

    public boolean controllaRegistrazione(){
        //Da implementare


        return true;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, LoginSupermercatoActivity.class);
        Log.d("TAG", "Ritorniamo al login Supermercato dall'activity RegistrazioneSupermercato");
        startActivity(i);
        super.onBackPressed();
    }
}
