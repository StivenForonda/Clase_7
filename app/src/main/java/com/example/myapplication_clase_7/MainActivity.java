package com.example.myapplication_clase_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etc, etn, etu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etc=(EditText)findViewById(R.id.etc);
        etn=(EditText)findViewById(R.id.etn);
        etu=(EditText)findViewById(R.id.etu);
    }

    public void registrar(View view){

        AdminBD admin = new AdminBD(this, "baseDatos", null, 1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        String cedula = etc.getText().toString();
        String nombre = etn.getText().toString();
        String universidad = etu.getText().toString();
        if (!cedula .isEmpty() && !nombre.isEmpty() && !universidad.isEmpty()){

            ContentValues registro = new ContentValues();
            registro.put("cedula", cedula);
            registro.put("nombre", nombre);
            registro.put("universidad", universidad);
            baseDatos.insert("usurio",null,registro);
            baseDatos.close();
            etc.setText("");
            etn.setText("");
            etu.setText("");
            Toast.makeText(this,"Registro Almacenado Exitosamente",Toast.LENGTH_LONG).show();
        }

        else {

            Toast.makeText(this,"Ingrese correctamente los campos",Toast.LENGTH_LONG).show();
        }

    }

    public void consultar(View view){

        AdminBD admin = new AdminBD(this,"baseDatos",null,1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        String cedula1 = etc.getText().toString();
        if (!cedula1.isEmpty()){

            Cursor fila = baseDatos.rawQuery("select nombre, universidad from usuario where cedula="+cedula1,null);
            if (fila.moveToFirst()){

                etn.setText(fila.getString(0));
                etu.setText(fila.getString(1));
                baseDatos.close();

            }
            else{

                Toast.makeText(this,"No se encontro el usuario", Toast.LENGTH_LONG).show();

            }

        }


    }
}