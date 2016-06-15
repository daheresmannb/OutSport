package outsport.outsport.componentes.lista;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Daniel on 29-04-2016.
 */
public class AdapterArray extends ArrayAdapter<ItemList> implements View.OnClickListener {

    private Context context;
    private DisplayMetrics met;
    private ItemList item;
    private LinearLayout contenedor;
    private ImageView icono;
    private TextView textView;
    private int tamaño;


    public AdapterArray(Context context, int tamaño) {
        super(context, android.R.layout.list_content);
        this.context=context;
        this.tamaño = tamaño;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        met = context.getResources().getDisplayMetrics();
        contenedor = new LinearLayout(context);
        contenedor.setGravity(Gravity.CENTER_VERTICAL);
        item = this.getItem(position);
        icono = new ImageView(context);
        textView = new TextView(context);
        textView.setTextSize(met.heightPixels*1/100);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setPadding(met.heightPixels*5/100,0,0,0);

        icono.setImageDrawable(
                this.context.getResources().getDrawable(item.icon)
        );

        textView.setText(item.name);

        contenedor.addView(
                icono,
                (met.widthPixels * 25) / 100,
                (met.heightPixels * tamaño) / 100
        );
        contenedor.addView(
                textView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        return contenedor;
    }

    @Override
    public void onClick(View v) {

    }
}
