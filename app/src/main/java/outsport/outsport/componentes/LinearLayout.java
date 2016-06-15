package outsport.outsport.componentes;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewGroup;

public class LinearLayout extends android.widget.LinearLayout {

    public LinearLayout(Context context) {
        super(context);
    }

    public LinearLayout(Context context, int gravedad, int color, int[] separacion, DisplayMetrics met) {
        super(context);
        this.setPadding(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, separacion[0], met),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, separacion[1], met),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, separacion[2], met),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, separacion[3], met)
        );
        this.setGravity(gravedad);
        this.setBackgroundColor(color);
    }

    public LinearLayout(Context context, int orientacion, int gravedad, int color, int[] separacion, DisplayMetrics met) {
        super(context);
        this.setPadding(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, separacion[0], met),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, separacion[1], met),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, separacion[2], met),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, separacion[3], met)
        );
        this.setOrientation(orientacion);
        this.setGravity(gravedad);
        this.setBackgroundColor(color);
    }

    public LinearLayout(Context context, int[] dimenciones, int orientacion, int gravedad, int color, int[] separacion, DisplayMetrics met) {
        super(context);
        this.setPadding(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, separacion[0], met),
                separacion[1],
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, separacion[2], met),
                separacion[3]
        );
        this.setOrientation(orientacion);
        this.setGravity(gravedad);
        this.setBackgroundColor(color);
        this.setLayoutParams(
                new ViewGroup.LayoutParams(
                        dimenciones[0],
                        dimenciones[1]
                )
        );
    }

    public LinearLayout(Context context, int ori, int grav, int col) {
        super(context);
        this.setOrientation(ori);
        this.setGravity(grav);
        this.setBackgroundColor(col);
    }

    public LinearLayout(Context context, int orientacion, int gravedad, int color, int mipmap) { // contenedor
        super(context);

        this.setOrientation(orientacion);
        this.setGravity(gravedad);
        this.setBackgroundColor(color);
        this.setBackgroundDrawable(
                getResources().getDrawable(mipmap)
        );
        this.setLayoutParams(
                new ViewGroup.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT
        ));
    }
}
