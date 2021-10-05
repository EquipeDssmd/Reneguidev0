package com.example.reneguidev0;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.reneguidev0.R.color;
import com.example.reneguidev0.R.id;

public class MainActivity extends AppCompatActivity {

    ViewPager2 imageContainer;
    slide_intro adapter;
    int[] list;
    TextView[] dots;
    LinearLayout layout;
    Button button;
    Fragment_Controller frag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_intro);

        imageContainer = findViewById(id.image_container);
        layout = findViewById(id.dots_container);
        button = findViewById(id.bt_iniciar);
        FragmentManager fm = getSupportFragmentManager();
        frag= new Fragment_Controller(fm,getLifecycle());
        imageContainer.setAdapter(frag);


        dots = new TextView[3];

        list = new int[3];



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
                dots[i].setTextColor(getColor(color.primary));
            } else {
                dots[i].setTextColor(getResources().getColor(color.auxiliar_2));
            }
        }
    }

    private void setIndicators() {
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#9679;"));
            dots[i].setTextSize(25);
            layout.addView(dots[i]);
        }

    }




}