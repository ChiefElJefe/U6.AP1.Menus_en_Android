package com.example.u6ap1menus_en_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<MangaVo> mangavo;
    RecyclerView recycler;
    final static String URL = "https://github.com/ChiefElJefe/CarpetaImage/raw/main/texto/texto.json";
    JSONObject texto1;
    JSONArray texto;
    JSONArray img;
    JSONArray img2;
    JSONArray volumen;
    JSONArray sinopsis;
    AdapterDatos adaptador;
    ArrayList<Pagina> pagina;
    Intent pasoPagina;

    DialogoLogo dialogo;
    boolean empezar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialogo = new DialogoLogo(MainActivity.this);

        empezar = false;

        pagina = new ArrayList<>();
        pasoPagina = new Intent(getApplicationContext(), MainActivity2.class);
        dialogo = new DialogoLogo(MainActivity.this);
        dialogo.mostrarDialogo("Cargando...");
        Handler manejador = new Handler();
        manejador.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialogo.cerrarDialogo();
            }
        },5000);



        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean exitoDescarga = descargarFichero(URL);
                for (int i = 0; i < 10; i++) {
                    exitoDescarga = descargarFichero("https://github.com/ChiefElJefe/CarpetaImage/raw/main/Image/csm"+(i+1)+".jpg");
                    exitoDescarga = descargarFichero("https://github.com/ChiefElJefe/CarpetaImage/raw/main/Image/csm"+(i+1)+"re.jpg");
                }
                empezar = true;
            }
        });

        t.start();




    }

    private void rellenarVista() {
        String datos ="";
        try {
            InputStreamReader in = new InputStreamReader(new FileInputStream(getApplicationContext().getFilesDir() + "/texto.json"));
            BufferedReader lectura = new BufferedReader(in);
            String line;
            while ((line = lectura.readLine()) != null){
                datos += line;
            }
            texto1 = new JSONObject(datos);
            texto = texto1.getJSONArray("titulo");
            img = texto1.getJSONArray("tarjeta_jpg");
            img2 = texto1.getJSONArray("ficha_jpg");
            volumen = texto1.getJSONArray("volumen");
            sinopsis = texto1.getJSONArray("sinopsis");

            for (int i = 0; i < texto.length(); i++) {
                mangavo.add(new MangaVo(texto.getString(i),getApplicationContext().getFilesDir() + "/" + img.getString(i)));
                pagina.add(new Pagina(volumen.getString(i), sinopsis.getString(i),getApplicationContext().getFilesDir() + "/" + img2.getString(i)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        recycler = findViewById(R.id.idNew);
        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recycler.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        mangavo = new ArrayList<>();

        rellenarVista();

        adaptador = new AdapterDatos(mangavo);
        recycler.setAdapter(adaptador);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pasoPagina.putExtra("pag", pagina.get(recycler.getChildAdapterPosition(view)));
                startActivity(pasoPagina);
            }
        });
    }

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

    protected boolean descargarFichero(String url) {
        boolean exito = true;

        try {
            String nombreArchivo = url.substring(url.lastIndexOf('/') + 1);
            File ruta = getApplicationContext().getFilesDir();
            File archivo = new File(ruta, nombreArchivo);

            URL u = new URL(url);
            InputStream is = u.openStream();
            DataInputStream dis = new DataInputStream(is);
            byte[] buffer = new byte[1024];
            int bytesLeidos;

            FileOutputStream fos = new FileOutputStream(archivo);
            while ((bytesLeidos = dis.read(buffer)) > 0) {
                fos.write(buffer, 0, bytesLeidos);
            }

        } catch (MalformedURLException mue) {
            Log.e("SYNC getUpdate", "malformed url error", mue);
            exito = false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            Log.e("SYNC getUpdate", "io error", ioe);
            exito = false;
        } catch (SecurityException se) {
            Log.e("SYNC getUpdate", "security error", se);
            exito = false;
        }
        return exito;
    }

}
