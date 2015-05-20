package com.example.boli.json;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class vista_individual extends ActionBarActivity {

    static final String KEY_N_ARETE = "n_arete";
    static final String KEY_F_NACIMIENTO = "f_nacimiento";
    static final String KEY_NOMBRE = "nombre";
    static final String KEY_SEXO = "sexo";
    static final String KEY_F_GESTACION = "f_gestacion";
    static final String KEY_F_PARTO = "f_parto";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_individual);

        Intent in = getIntent();

        // Get XML values from previous intent
        String num_are = in.getStringExtra(KEY_N_ARETE);
        String f_nacimiento = in.getStringExtra(KEY_F_NACIMIENTO);
        String nombre = in.getStringExtra(KEY_NOMBRE);
        String sexo = in.getStringExtra(KEY_SEXO);
        String f_gestacion = in.getStringExtra(KEY_F_GESTACION);
        String f_parto = in.getStringExtra(KEY_F_PARTO);


        // Displaying all values on the screen
        TextView lbl_arete = (TextView) findViewById(R.id.n_arete);
        TextView lblnacio = (TextView) findViewById(R.id.nacio);
        TextView lblnombre = (TextView) findViewById(R.id.nombre);
        TextView lblsexo = (TextView) findViewById(R.id.sexo);
        TextView lblgestacion = (TextView) findViewById(R.id.gestacion);
        TextView lblparto = (TextView) findViewById(R.id.parto);


        lbl_arete.setText(num_are);
        lblnacio.setText(f_nacimiento);
        lblnombre.setText(nombre);
        lblsexo.setText(sexo);
        lblgestacion.setText(f_gestacion);
        lblparto.setText(f_parto);

    }


 }
