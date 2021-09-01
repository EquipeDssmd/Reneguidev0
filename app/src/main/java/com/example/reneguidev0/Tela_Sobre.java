package com.example.reneguidev0;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Tela_Sobre extends AppCompatActivity {
    ImageView bt_home, bt_help;
    CardView btSobre;
    TextView appsobre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_sobre);

        bt_home = findViewById(R.id.homebutton);
        bt_home.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Tela_Inicial.class);
            startActivity(intent);


        });

        btSobre = findViewById(R.id.bt_sobre);
        btSobre.setBackgroundColor(getResources().getColor(R.color.auxiliar_1));
        appsobre = findViewById(R.id.appname);
        appsobre.setTextColor(getResources().getColor(R.color.auxiliar_2));
        btSobre.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Tela_Sobre.class);
            startActivity(intent);


        });

        bt_help = findViewById(R.id.helpbutton);
        bt_help.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Tela_Help.class);
            startActivity(intent);


        });


    }

}
