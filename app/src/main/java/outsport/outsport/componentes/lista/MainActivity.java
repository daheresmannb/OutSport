package outsport.outsport.componentes.lista;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import outsport.outsport.R;

/**
 * Created by Daniel on 09-05-2016.
 */
public class MainActivity extends Activity {
    LinearLayout contenedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics met = getResources().getDisplayMetrics();
        contenedor = new LinearLayout(this);
        contenedor.setBackgroundColor(Color.BLUE);
        ListaDosItems lista = new ListaDosItems(this, 10);

        for (int i = 0; i < 100; i++)
        lista.InsertToList(
            new ItemList(
                    R.mipmap.ic_launcher,
                    "holaaaaaaaaa!!!!"
            )
        );

        contenedor.addView(
                lista.GetLista(),
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        addContentView(
                contenedor,
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );
    }
}
