package com.example.reneguidev0;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Tela_Help extends AppCompatActivity {
    ImageView bt_home, bt_help;
    CardView btSobre;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_help);


        bt_home = findViewById(R.id.homebutton);
        bt_home.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Tela_Inicial.class);
            startActivity(intent);


        });

        btSobre = findViewById(R.id.bt_sobre);
        btSobre.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Tela_Sobre.class);
            startActivity(intent);


        });

        bt_help = findViewById(R.id.helpbutton);
        bt_help.setPressed(true);
        bt_help.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Tela_Help.class);
            startActivity(intent);


        });

    }

    }
