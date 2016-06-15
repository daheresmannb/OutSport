package outsport.outsport.componentes;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewGroup;

/**
 * Created by Daniel on 29-04-2016.
 */
public class RelativeLayoutM extends android.widget.RelativeLayout {
    private LayoutParams params;
    private ViewGroup.LayoutParams params2;

    public RelativeLayoutM(Context context) {
        super(context);
    }

    public RelativeLayoutM(Context context, int[] dimenciones, int gravedad, int color, int[] reglas, int[] separacion, DisplayMetrics met) {
        super(context);

        params = new LayoutParams(
            dimenciones[0],
            dimenciones[1]
        );

        for(int i = 0; i < reglas.length; i++)
            params.addRule(reglas[i]);

        this.setPadding(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, separacion[0], met),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, separacion[1], met),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, separacion[2], met),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, separacion[3], met)
        );

        this.setGravity(gravedad);
        this.setBackgroundColor(color);
    }

    public RelativeLayoutM(Context context, int[] dimenciones, int gravedad, int color, int[] separacion, DisplayMetrics met) {
        super(context);

        params2 = new ViewGroup.LayoutParams(
                dimenciones[0],
                dimenciones[1]
        );



        this.setPadding(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, separacion[0], met),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, separacion[1], met),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, separacion[2], met),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, separacion[3], met)
        );

        this.setGravity(gravedad);
        this.setBackgroundColor(color);
    }

    public RelativeLayoutM(Context context, int ancho, int alto) {
        super(context);

        params = new LayoutParams(
                ancho,
                alto
        );
    }
}
