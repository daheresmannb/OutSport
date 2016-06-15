package outsport.outsport.eventos;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import outsport.outsport.PerfilActivity;
import outsport.outsport.R;

public class Lista_eventos extends AppCompatActivity {
    ListView listView;
    String[] valores = new String[]{"evento1", "evento2", "evento3", "evento4"};
    Button boton;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_eventos);

        listView = (ListView)findViewById(R.id.listView);
        boton = (Button) findViewById(R.id.button);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_expandable_list_item_1,
                valores
        );
        listView.setAdapter(adapter);
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
}
