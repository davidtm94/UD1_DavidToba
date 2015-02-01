package com.example.david.ud1_davidtoba;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

import bbdd.ClaseBBDD;


public class NomePersoas extends Activity {
    HashMap<String,String> hm;
    Spinner spin;
    TextView tvdesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nome_persoas);
        cargarDatosBBDD();
        tvdesc=(TextView) findViewById(R.id.tvDesc);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvdesc.setText((String)hm.get(      ((TextView)view).getText().toString()        ));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void cargarDatosBBDD() {
        //Gardaremos os datos nun Hashmap dentro do ArrayList declarado arriba
        hm=new HashMap<>();
        ClaseBBDD cbd=new ClaseBBDD(this);
        SQLiteDatabase sqlLiteDB = cbd.getReadableDatabase();
        Cursor cursor = sqlLiteDB.query("PERSOAS", new String[]{"nome", "descricion"}, null, null, null, null, null);
        if (cursor.moveToFirst()) {                // Se non ten datos xa non entra
            while (!cursor.isAfterLast()) {     // Quédase no bucle ata que remata de percorrer o cursor. Fixarse que leva un ! (not) diante
                String nome = cursor.getString(0),descricion=cursor.getString(1);
                hm.put(nome,descricion);
                cursor.moveToNext();
            }
        }
        //Cargamos os datos no Spinner
        spin=(Spinner) findViewById(R.id.spin1);
        ArrayAdapter<String> adaptador =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, new ArrayList<String>(hm.keySet()));
        spin.setAdapter(adaptador);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nome_persoas, menu);
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

    public void aplicarPreferencias(View v) {

    }

    public void gardaArquivoTexto(View v){
        //Obtemos a ruta das preferencias
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String valorRuta = preferencias.getString("ed_ruta", "vacia");
        //Si non obten a preferencia, saimos do metodo
        if(valorRuta.equalsIgnoreCase("vacia"))
            return;
        //Comproba se existe o directorio, senon creao
        File rutaDir=new File(valorRuta);
        rutaDir.mkdirs();
        //Gardamos nunha variable o nome da persoa seleccionada no spinner
        String nomePersoaSeleccionada=((TextView)spin.getSelectedView()).getText().toString();
        //Agora crea o arquivo no directorio
        File rutaArquivo=new File(rutaDir.getAbsolutePath(), nomePersoaSeleccionada+".txt");

        //Creamos o arquivo
        try {
            FileOutputStream fluxoSaida = new FileOutputStream(rutaArquivo);
            BufferedWriter bufferSaida = new BufferedWriter(new OutputStreamWriter(fluxoSaida));
            bufferSaida.write("-Nome Persoa: " + nomePersoaSeleccionada + "\n-Descricion: "+hm.get(nomePersoaSeleccionada));
            bufferSaida.close();
        }catch (FileNotFoundException erro){
            System.out.println("Non se pudo crear o arquivo: "+erro.getMessage());
        }catch (IOException erro){
            System.out.println("Non se pudo crear o arquivo: "+erro.getMessage());
        }
    }
}
