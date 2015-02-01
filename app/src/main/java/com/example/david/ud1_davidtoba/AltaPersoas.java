package com.example.david.ud1_davidtoba;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import bbdd.ClaseBBDD;


public class AltaPersoas extends Activity {
    EditText ed,ed2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_persoas);
        ed=(EditText) findViewById(R.id.editText);
        ed2=(EditText) findViewById(R.id.editText2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alta_persoas, menu);
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

    public void altaPersoa(View v){
        ClaseBBDD cbd=new ClaseBBDD(this);
        SQLiteDatabase sqlLiteDB = cbd.getWritableDatabase();
        ContentValues datosAintroducir = new ContentValues();
        datosAintroducir.put("nome", ed.getText().toString());
        datosAintroducir.put("descricion", ed2.getText().toString());
        long idFila1 = sqlLiteDB.insert("PERSOAS", null, datosAintroducir);
        datosAintroducir.clear();
        ed.setText("");
        ed2.setText("");
        //Comprobado. Garda correctamente os datos na BBDD
    }
}
