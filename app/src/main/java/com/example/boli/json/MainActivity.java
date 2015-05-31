package com.example.boli.json;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ListActivity {

 // SE DECLARA VARIABLE
    private Context context;

    // DIRECCION DONDE OBTENDRA LOS DATOS DEl ARCHIVO.JSON
    private static String url = "http://iin8.szhernandez.dx.am/ganado.json";

    // Nodos keys Del JSON
    static final String KEY_N_ARETE = "n_arete";
    static final String KEY_F_NACIMIENTO = "f_nacimiento";
    static final String KEY_NOMBRE = "nombre";
    static final String KEY_SEXO = "sexo";
    static final String KEY_F_GESTACION = "f_gestacion";
    static final String KEY_F_PARTO = "f_parto";

    ArrayList<HashMap<String, String>> jsonlist = new ArrayList<HashMap<String, String>>();

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ProgressTask(MainActivity.this).execute();
    }

    // METOS QUE MUESTRA LA BARRA DE PROGRESO
    private class ProgressTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog dialog;

        private ListActivity activity;

        // private List<Message> messages;
        public ProgressTask(ListActivity activity) {
            this.activity = activity;
            context = activity;
            dialog = new ProgressDialog(context);
        }

        /** progress dialog to show user that the backup is processing. */

        /**
         * application context.
         */
        private Context context;

        //MENSAJE QUE SE MOSTRARA EL BARRA DE PROGRESO
        protected void onPreExecute() {
            this.dialog.setMessage("Cargando...");
            this.dialog.show();
        }

        //MANDA LOS VALORES A ITEM
        protected void onPostExecute(final Boolean success) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            ListAdapter adapter = new SimpleAdapter(context, jsonlist,
                    R.layout.list_item, new String[]{KEY_N_ARETE, KEY_F_NACIMIENTO, KEY_NOMBRE, KEY_SEXO, KEY_F_GESTACION, KEY_F_PARTO}, new int[]{
                    R.id.v_n_arete, R.id.v_f_naci, R.id.v_nombre, R.id.v_sexo, R.id.v_f_ges, R.id.v_f_parto});

            setListAdapter(adapter);

            // selecting single ListView item
            lv = getListView();
            lv.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    // Obtiene la selecciona de todos valores del Listview
                    String tx_n_arete = ((TextView) view.findViewById(R.id.v_n_arete)).getText().toString();
                    String tx_f_nacio = ((TextView) view.findViewById(R.id.v_f_naci)).getText().toString();
                    String txt_nombre = ((TextView) view.findViewById(R.id.v_nombre)).getText().toString();
                    String txt_sexo = ((TextView) view.findViewById(R.id.v_sexo)).getText().toString();
                    String txt_f_ges = ((TextView) view.findViewById(R.id.v_f_ges)).getText().toString();
                    String txt_f_par = ((TextView) view.findViewById(R.id.v_f_parto)).getText().toString();

                    // MANDA LOS VALORES A LA VISTA INDIVIDUAL
                    Intent in = new Intent(getApplicationContext(), vista_individual.class);
                    in.putExtra(KEY_N_ARETE,"ARETE: " + tx_n_arete);
                    in.putExtra(KEY_F_NACIMIENTO," F.NAC: " + tx_f_nacio);
                    in.putExtra(KEY_NOMBRE, " NOMBRE: " + txt_nombre);
                    in.putExtra(KEY_SEXO, " SEXO: " + txt_sexo);
                    in.putExtra(KEY_F_GESTACION, " F.GES: " + txt_f_ges);
                    in.putExtra(KEY_F_PARTO,  "F.PAR: " + txt_f_par);

                    startActivity(in);

                }
            });
        }


        protected Boolean doInBackground(final String... args) {

            JsonParse jParser = new JsonParse();

            // obtiene los valores del json mediante la url
            JSONArray json = jParser.getJSONFromUrl(url);

            for (int i = 0; i < json.length(); i++) {

                try {
                    JSONObject c = json.getJSONObject(i);

                    String arete = c.getString(KEY_N_ARETE);
                    String nacio = c.getString(KEY_F_NACIMIENTO);
                    String nombre1 = c.getString(KEY_NOMBRE);
                    String sexo1 = c.getString(KEY_SEXO);
                    String gestacion = c.getString(KEY_F_GESTACION);
                    String pario = c.getString(KEY_F_PARTO);


                    HashMap<String, String> map = new HashMap<String, String>();

                    // Se añaden cada nodo hijo al HashMap con su respectiva clave
                    map.put(KEY_N_ARETE, arete);
                    map.put(KEY_F_NACIMIENTO, nacio);
                    map.put(KEY_NOMBRE, nombre1);
                    map.put(KEY_SEXO, sexo1);
                    map.put(KEY_F_GESTACION, gestacion);
                    map.put(KEY_F_PARTO, pario);
                    jsonlist.add(map);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            return null;
        }

    }
}
