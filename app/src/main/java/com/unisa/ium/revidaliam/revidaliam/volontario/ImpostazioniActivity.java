package com.unisa.ium.revidaliam.revidaliam.volontario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        setContentView(R.layout.volontario_activity_impostazioni);

        logoutButton = findViewById(R.id.logout);
        logoutButton.setOnClickListener(disconnettiUtente);
    }

    View.OnClickListener disconnettiUtente = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), MainActivity.class);
            startActivity(i);
        }
    };
}
