package com.example.veritabani;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText ad,soyad,email;
    Button kaydet,liste;
    TextView sonuc;

    public void init(){
        ad = (EditText)findViewById(R.id.ad);
        soyad = (EditText)findViewById(R.id.soyad);
        email = (EditText)findViewById(R.id.email);
        kaydet = (Button)findViewById(R.id.kaydet);
        liste = (Button)findViewById(R.id.liste);
        sonuc = (TextView)findViewById(R.id.sonuc);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        kaydet.setOnClickListener((View.OnClickListener) this);
        liste.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View view){
        File dosya = new File(this.getFilesDir(),"veritabani.txt");

        if( view.getId() == kaydet.getId()) {
            try {
                FileOutputStream fos = new FileOutputStream(dosya, true);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                String A = ad.getText().toString();
                String S = soyad.getText().toString();
                String E = email.getText().toString();
                String total = A + ";" + S + ";" + E;
                osw.write(total + "\n");
                osw.close();
                fos.close();
                Toast.makeText(this, "Kayıt başarılı", Toast.LENGTH_SHORT).show();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        else{
            try {
                FileInputStream fis = new FileInputStream(dosya);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);

                String output = "",satir="";
                satir = br.readLine();
                while (satir!=null){

                    String[] arrOfStr = satir.split(";");

                    for(String sonuc : arrOfStr){
                        output += sonuc + " ";
                    }

                    output += "\n";
                    satir= br.readLine();

                }
                br.close();
                isr.close();
                fis.close();

                sonuc.setText(output);
                Toast.makeText(this,dosya.toString(),Toast.LENGTH_LONG).show();
            }

            catch (FileNotFoundException e){
                e.printStackTrace();

            }
            catch (IOException e){
                e.printStackTrace();
            }


        }
    }
}