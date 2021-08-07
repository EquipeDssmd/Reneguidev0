package com.example.reneguidev0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ViewPager2 imageContainer;
    slide_intro adapter;
    int[] list;
    TextView[] dots;
    LinearLayout layout;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_intro);

        imageContainer = findViewById(R.id.image_container);
        layout = findViewById(R.id.dots_container);
        button = findViewById(R.id.bt_iniciar);


        dots = new TextView[3];

        list = new int[3];
        list[0] = getResources().getColor(R.color.primary);
        list[1] = getResources().getColor(R.color.cat_1);
        list[2] = getResources().getColor(R.color.cat_2);


        adapter = new slide_intro(list);
        imageContainer.setAdapter(adapter);

        setIndicators();

        imageContainer.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                selectedDots(position);
                super.onPageSelected(position);
            }
        });

        button.setOnClickListener(v -> {

            Intent intent = new Intent(getApplicationContext(), Tela_Inicial.class);
            startActivity(intent);

        });

    }

    private void selectedDots(int position) {
        for (int i = 0; i < dots.length; i++) {
            if (i == position) {
                dots[i].setTextColor(getColor(R.color.primary));
            } else {
                dots[i].setTextColor(getResources().getColor(R.color.auxiliar_2));
            }
        }
    }

    private void setIndicators() {
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#9679;"));
            dots[i].setTextSize(26);
            layout.addView(dots[i]);
        }

    }




}