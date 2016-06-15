package outsport.outsport.componentes;

import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by daniel on 29-04-2016.
 */
public class Button extends android.widget.Button {
    private static StateListDrawable estados_boton;

    public Button(Context context, int draw) {
        super(context);
        this.setBackgroundDrawable(getResources().getDrawable(draw));
    }

    public Button(Context context, LinearLayout layout, int draw) {
        super(context);
        this.setBackgroundDrawable(getResources().getDrawable(draw));
        layout.addView(this);
    }

    public Button(Context context, RelativeLayoutM layout, int[] estados_img) {
        super(context);

        estados_boton = new StateListDrawable();
        estados_boton.addState(
                new int[]{
                        android.R.attr.state_pressed
                },
                getResources().getDrawable(
                        estados_img[0]
                )
        );
        estados_boton.addState(
                new int[]{},
                getResources().getDrawable(
                        estados_img[1]
                )
        );
        this.setBackgroundDrawable(estados_boton);
        layout.addView(this);
    }

    public Button(Context context, LinearLayout layout, int[] estados_img, int alto, DisplayMetrics met) {//////////
        super(context);

        estados_boton = new StateListDrawable();
        estados_boton.addState(
                new int[]{
                        android.R.attr.state_pressed
                },
                getResources().getDrawable(
                        estados_img[0]
                )
        );
        estados_boton.addState(
                new int[]{},
                getResources().getDrawable(
                        estados_img[1]
                )
        );

        this.setBackgroundDrawable(estados_boton);

        layout.addView(
                this,
                ViewGroup.LayoutParams.MATCH_PARENT,
                new Porcentaje().result(met.heightPixels,alto)
        );
    }

    public Button(Context context, LinearLayout layout, int[] estados_img, int[] dimen, DisplayMetrics met) {//////////
        super(context);

        estados_boton = new StateListDrawable();
        estados_boton.addState(
                new int[]{
                        android.R.attr.state_pressed
                },
                getResources().getDrawable(
                        estados_img[0]
                )
        );
        estados_boton.addState(
                new int[]{},
                getResources().getDrawable(
                        estados_img[1]
                )
        );

        this.setBackgroundDrawable(estados_boton);

        layout.addView(
                this,
                new Porcentaje().result(met.widthPixels,dimen[0]),
                new Porcentaje().result(met.heightPixels,dimen[1])
        );
    }

    public static StateListDrawable getEstados_boton() {
        return estados_boton;
    }

    public static void setEstados_boton(StateListDrawable Estados_boton) {
        estados_boton = Estados_boton;
    }
}