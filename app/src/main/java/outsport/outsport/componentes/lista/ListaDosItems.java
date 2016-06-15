package outsport.outsport.componentes.lista;

import android.content.Context;
import android.widget.ListView;

/**
 * Created by Daniel on 09-05-2016.
 */
public class ListaDosItems {

    public ListView listView;
    public AdapterArray adapter;

    public ListaDosItems(Context context, int tamaño) {
        listView = new ListView(context);
        adapter = new AdapterArray(context, tamaño);
    }

    public void InsertToList(ItemList items) {

        this.adapter.add(items);
    }

    public ListView GetLista() {
        this.listView.setAdapter(this.adapter);
        return this.listView;
    }
}
