package outsport.outsport.facebookclases;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

import outsport.outsport.componentes.LinearLayout;
import outsport.outsport.componentes.Porcentaje;


/**
 * Created by Daniel on 02-05-2016.
 */
public class MibotonFacebook extends CustomLoginButton {
    private StateListDrawable estados_boton,estados_boton2;

    public MibotonFacebook(Context context, LinearLayout layout, int dimen, DisplayMetrics met, int[] img) {
        super(context);

        this.setBackgroundColor(Color.TRANSPARENT);
        this.setCompoundDrawables(null,null,null,null);
        this.setTextSize(0);
        estados_boton = new StateListDrawable();
        estados_boton.addState(
                new int[]{
                        android.R.attr.state_pressed
                },
                getResources().getDrawable(
                        img[0]
                )
        );
        estados_boton.addState(
                new int[]{},
                getResources().getDrawable(
                        img[1]
                )
        );
        this.setBackgroundDrawable(estados_boton);
        layout.addView(
                this,
                ViewGroup.LayoutParams.MATCH_PARENT,
                new Porcentaje().result(met.heightPixels,dimen)
        );
    }
}
