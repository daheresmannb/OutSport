package outsport.outsport.eventos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import outsport.outsport.PerfilActivity;
import outsport.outsport.R;


public class BuscarEventosActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private Spinner spinner1, spinner2, spinner3;
    private Button btnSubmit;
    Button button;
    String[] valores_form = new String[4];
    String[] regiones = new String[]{"XV Arica y Parinacota", "I Tarapaca", "II Antofagasta", "III Atacama", "IV Coquimbo", "V Valparaiso", "VI O'Higgins", "VII Maule", "VIII Maule", "IX La Araucania", "XIV Los Rios", "X Los Lagos", "XI Aysen", "XII Magallanes Y Antartida"};
    String[] ciudades_IX = new String[]{"temuco", "freire"};
    String[] ciudades_II = new String[]{"antofagasta", "calama"};
    String[] ciudades_III = new String[]{"chañarala", "copiapo", "vallenar"};
    String[] ciudades_x = new String[]{"No hay ciudades registradas"};
    String[] ciudad = new String[]{};
    String[] categorias = new String[]{"Nautica", "Aerea", "Terrestre", "Nieve", "Fotografia", "Outdoor"};
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar_eventos);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        button = (Button) findViewById(R.id.button);
        addItemsOnSpinner1_3();
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);
        button.setOnClickListener(this);
    }

    public void addItemsOnSpinner1_3() {

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, regiones);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
        ArrayAdapter<String> dataAdapter_categorias = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categorias);
        dataAdapter_categorias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(dataAdapter_categorias);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner1:
                int seleccionado = spinner1.getSelectedItemPosition();
                if (seleccionado == 2) {
                    ciudad = ciudades_II;
                    valores_form[0] = regiones[seleccionado];
                }
                if (seleccionado == 3) {
                    ciudad = ciudades_III;
                    valores_form[0] = regiones[seleccionado];
                }
                if (seleccionado == 9) {
                    ciudad = ciudades_IX;
                    valores_form[0] = regiones[seleccionado];
                }
                if (seleccionado != 9 && seleccionado != 3 && seleccionado != 2) {
                    ciudad = ciudades_x;
                    valores_form[0] = "";
                }
                ArrayAdapter<String> dataAdapter_ciudad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ciudad);
                dataAdapter_ciudad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(dataAdapter_ciudad);
            case R.id.spinner2:
                int seleccionado2 = spinner2.getSelectedItemPosition();
                if (valores_form[0] != "") {
                    if (valores_form[0] == "II Antofagasta") {
                        valores_form[1] = ciudades_II[seleccionado2];
                    }
                    if (valores_form[0] == "III Atacama") {
                        valores_form[1] = ciudades_III[seleccionado2];
                    }
                    if (valores_form[0] == "IX La Araucania") {
                        valores_form[1] = ciudades_x[seleccionado2];
                    }
                } else {
                    valores_form[1] = "aun no selecciona region con ciudades";
                }

            case R.id.spinner3:
                int seleccionado3 = spinner3.getSelectedItemPosition();
                valores_form[2] = categorias[seleccionado3];
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        i = new Intent(
                this,
                PerfilActivity.class
        );
        startActivity(i);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                if (valores_form[0] == "") {
                    Toast.makeText(this, "Aun no selecciona region o eleligio una region sin ciudades", Toast.LENGTH_LONG).show();
                    break;
                }
                if (valores_form[1] == "aun no selecciona region con ciudades" || valores_form[1] == "") {
                    Toast.makeText(this, "aun no selecciona ciudad", Toast.LENGTH_LONG).show();
                    break;
                }
                if (valores_form[2] == "") {
                    Toast.makeText(this, "aun no selecciona categoria", Toast.LENGTH_LONG).show();
                    break;
                } else {
                    i = new Intent(
                            this,
                            Lista_eventos.class
                    );
                    startActivity(i);
                    finish();
                }
        }
    }
}
