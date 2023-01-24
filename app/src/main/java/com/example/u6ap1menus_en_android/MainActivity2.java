package com.example.u6ap1menus_en_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView volumen, sinopsis;
    ImageView portada;
    Pagina pag;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        volumen = findViewById(R.id.idtxtTomo);
        sinopsis = findViewById(R.id.idtxtDescripcion);
        portada = findViewById(R.id.idimgPortada);

        intent = getIntent();
        pag = (Pagina) intent.getSerializableExtra("pag");

        volumen.setText(pag.getVolumen());
        sinopsis.setText(pag.getSinopsis());
        portada.setImageDrawable(Drawable.createFromPath(pag.getPortada()));

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() { super.onResume(); }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}