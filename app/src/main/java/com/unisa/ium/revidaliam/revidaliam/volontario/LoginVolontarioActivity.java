package com.unisa.ium.revidaliam.revidaliam.volontario;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.unisa.ium.revidaliam.revidaliam.R;
import com.unisa.ium.revidaliam.revidaliam.db.DBHelper;
import com.unisa.ium.revidaliam.revidaliam.db.VolontarioBean;


public class LoginVolontarioActivity extends AppCompatActivity {

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
        setContentView(R.layout.volontario_activity_login);
        userEditText = findViewById(R.id.username);
        pswEditText = findViewById(R.id.password);
        accediButton = findViewById(R.id.login);
        annullaButton = findViewById(R.id.annulla);
        registrazioneButton = findViewById(R.id.goToRegistrazione);
        tilUsername = findViewById(R.id.tilUsername);
        tilPsw = findViewById(R.id.tilPsw);

        annullaButton.setOnClickListener(returnBack);
        registrazioneButton.setOnClickListener(goToRegistraVolontario);
        accediButton.setOnClickListener(accediVolontario);
    }

    View.OnClickListener goToRegistraVolontario = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), RegistrazioneVolontarioActivity.class);
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

    View.OnClickListener accediVolontario = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            username = userEditText.getText().toString();
            psw = pswEditText.getText().toString();
            VolontarioBean volontario = controllaAccesso(v);
            if (volontario != null) {
                Intent i = new Intent(v.getContext(), HomeVolontario.class);
                i.putExtra("username", volontario.getUsername());
                i.putExtra("password", volontario.getPassword());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);   //permette di non tornare più alla login e registrazione
                startActivity(i);
            } else {
                tilUsername.setError("Invalid Username");
                tilPsw.setError("Invalid Password");
            }
        }
    };

    public VolontarioBean controllaAccesso(View v) {
        DBHelper db = new DBHelper(v.getContext());
        VolontarioBean volontarioBean = db.retrieveVolontarioContacts(username, psw);
        return volontarioBean;
    }
}
