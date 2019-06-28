package com.example.app_pingui_g2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.app_pingui_g2.R;

public class MenuActivity extends AppCompatActivity {
    private Button temperatura;
    private Button recorrido;
    private Button salir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final String token = getIntent().getStringExtra("token");

        temperatura = findViewById(R.id.temperature);
        temperatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,TemperatureActivity.class);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });

        recorrido = findViewById(R.id.recorrido);

        recorrido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this,RecorridoActivity.class);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });

        salir = findViewById(R.id.salir);

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });



    }
}
