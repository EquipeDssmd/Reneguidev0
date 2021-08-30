package com.example.reneguidev0;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Tela_Pdf  extends AppCompatActivity {
    private static final String TAG = Tela_Pdf.class.getSimpleName();
    private PDFView pdfView;
    Integer pageNumber = 0;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_pdf);


        pdfView = (PDFView) findViewById(R.id.pdfview);
        url = getIntent().getStringExtra("pdfUrl");
        //url = "https://www.reportlab.com/docs/reportlab-reference.pdf";
        new Tela_Pdf.PdfDownload().execute(url);
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
            pdfView.fromStream(inputStream)
                    .defaultPage(pageNumber)
                    .enableSwipe(true)
                    .swipeHorizontal(true)
                    .enableAnnotationRendering(true)
                    .load();
        }
    }
}
