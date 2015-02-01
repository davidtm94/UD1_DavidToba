package com.example.david.ud1_davidtoba;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import bbdd.ClaseBBDD;


public class UD1_DavidToba extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ud1__david_toba);
        copiarBaseDeDatos();
    }

    private boolean copiarBaseDeDatos() {
        //Comprobamos si a BBDD existe
        String bddestino = "/data/data/" + getPackageName() + "/databases/" + ClaseBBDD.NOME_BD;
        File file = new File(bddestino);
        if (file.exists())
            return false;

        //Si non existe creamos a carpeta databases...
        String pathbd = "/data/data/" + getApplicationContext().getPackageName()+"/databases/";
        File ruta = new File(pathbd);
        ruta.mkdirs();

        //...Finalmente copiamos a BBDD
        File filepathdb = new File(pathbd);
        filepathdb.mkdirs();

        InputStream inputstream;
        try {
            inputstream = getAssets().open(ClaseBBDD.NOME_BD+".db");
            OutputStream outputstream = new FileOutputStream(bddestino);

            int tamread;
            byte[] buffer = new byte[2048];

            while ((tamread = inputstream.read(buffer)) > 0) {
                outputstream.write(buffer, 0, tamread);
            }

            inputstream.close();
            outputstream.flush();
            outputstream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ud1__david_toba, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, Configuracion.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void darAlta(View v){
        Intent intent = new Intent(this, AltaPersoas.class);
        startActivity(intent);
    }

    public void cargaDatos(View v){
        Intent intent = new Intent(this, NomePersoas.class);
        startActivity(intent);
    }


}
