package com.unisa.ium.revidaliam.revidaliam.utente;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.unisa.ium.revidaliam.revidaliam.R;
import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.db.UtenteBean;

public class LoginUtenteActivity extends AppCompatActivity {

    private EditText userEditText;
    private EditText pswEditText;
    private Button accediButton;
    private Button annullaButton;
    private Button registrazioneButton;
    private String username;
    private String psw;
    private String nome;
    private String cognome;
    private String email;
    private String indirizzo;
    private TextInputLayout tilUsername;
    private TextInputLayout tilPsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utente_activity_login);

        userEditText = findViewById(R.id.username);
        pswEditText = findViewById(R.id.password);
        accediButton = findViewById(R.id.login);
        annullaButton = findViewById(R.id.annulla);
        registrazioneButton = findViewById(R.id.goToRegistrazione);
        tilUsername = findViewById(R.id.tilUsername);
        tilPsw = findViewById(R.id.tilPsw);

        annullaButton.setOnClickListener(returnBack);
        registrazioneButton.setOnClickListener(goToRegistraUtente);
        accediButton.setOnClickListener(accediUtente);
    }

    View.OnClickListener goToRegistraUtente = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), RegistrazioneActivity.class);
            startActivity(i);
            finish();      //per evitare di avere 2 activity login dopo
        }
    };

    View.OnClickListener returnBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    View.OnClickListener accediUtente = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            username = userEditText.getText().toString();
            psw = pswEditText.getText().toString();

            DBHelper db = new DBHelper(v.getContext());
            UtenteBean u = db.retrieveUtenteContacts(username, psw);
            if (u != null) {
                Intent i = new Intent(v.getContext(), HomeUtente.class);
                i.putExtra("username", u.getUsername());
                i.putExtra("password", u.getPassword());
                Toast.makeText(LoginUtenteActivity.this, "accesso effettuato", Toast.LENGTH_SHORT).show();
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);   //permette di non tornare più alla login e registrazione
                startActivity(i);
            } else {
                Toast.makeText(LoginUtenteActivity.this, "dati errati", Toast.LENGTH_SHORT).show();
                tilUsername.setError("Invalid Username");
                tilPsw.setError("Invalid Password");
            }
        }
    };

    public boolean controllaAccesso() {
        if (userEditText.getText().toString().equals(username) && pswEditText.getText().toString().equals(psw)) { //controlla se i dati sono uguali a quelli della registrazione
            return true;
        } else {
            return false;
        }
    }

}
