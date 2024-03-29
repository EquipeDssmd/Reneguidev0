package com.example.reneguidev0;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Tela_Pdf  extends AppCompatActivity {
    private PDFView pdfView;
    Integer pageNumber = 0;
    private String url;

    ImageView bt_home, bt_help;
    CardView btSobre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_pdf);




        pdfView = (PDFView) findViewById(R.id.pdfview);
        url = getIntent().getStringExtra("pdfUrl");
        new Tela_Pdf.PdfDownload().execute(url);

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
        bt_help.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Tela_Help.class);
            startActivity(intent);


        });

    }

    private class PdfDownload extends AsyncTask<String, Void, InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection =(HttpURLConnection) url.openConnection();
                if(urlConnection.getResponseCode() == 200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return inputStream;
        }


        @Override
        protected void onPostExecute(InputStream inputStream) {
            if (inputStream == null){
                Toast.makeText(getApplicationContext(), "não foi possivel acessar o pdf!", Toast.LENGTH_LONG).show();

                //finish();
            }
            pdfView.fromStream(inputStream)
                    .defaultPage(pageNumber)
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .enableAnnotationRendering(true)
                    .load();
        }
    }
}
