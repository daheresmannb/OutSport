package outsport.outsport.componentes;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.DisplayMetrics;
import android.view.ViewGroup;


public class EditText extends android.widget.EditText {
    private ViewGroup.LayoutParams params;
    private StateListDrawable estados_boton;

    public EditText(Context context, LinearLayout layout, String text, int color, float textSize, int img) {
        super(context);
        this.setTextColor(color);
        //this.setTypeface(Typeface.createFromAsset(context.getAssets(), font));
        this.setTextSize(textSize);
        this.setHintTextColor(color);
        this.setHint(text);
        this.setPadding(20, 10, 10, 10);

        Drawable d = getResources().getDrawable(img);

        d.setBounds(0, 0, (int) textSize + 8, (int) textSize + 8);
        this.setCompoundDrawables(d, null, null, null);
        layout.addView(this);
    }

    public EditText(Context context, LinearLayout layout) {
        super(context);
    }

    /*
    public EditText(Context context, LinearLayout contenedor, String texto, int color, String s1, float textsize, int imagen) {
        super(context);
        this.setTextColor(color);
        this.setTextSize(textsize);
        this.setHintTextColor(color);
        this.setHint(texto);
        this.setPadding(130, 20, 20, 20);
        Drawable d = getResources().getDrawable(imagen);
        d.setBounds(0, 0, (int) textsize + 18, (int) textsize + 18);
        this.setCompoundDrawables(d, null, null, null);
        contenedor.addView(this);
    }
*/
    public EditText(Context context, LinearLayout layout, int[] estados_img, int alto, DisplayMetrics met) {
        super(context);

        params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                new Porcentaje().result(met.heightPixels, alto)
        );

        estados_boton = new StateListDrawable();
        estados_boton.addState(
                new int[]{
                        android.R.attr.state_empty
                },
                getResources().getDrawable(
                        estados_img[0]
                )
        );

        estados_boton.addState(
                new int[]{
                        android.R.attr.state_focused
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
        this.setTextColor(Color.WHITE);

        this.setPadding(
                new Porcentaje().result(met.widthPixels, alto+4),
                new Porcentaje().result(met.heightPixels, alto)*35/100,
                new Porcentaje().result(met.widthPixels, alto)*45/100,
                new Porcentaje().result(met.heightPixels, alto)*35/100
        );

        //this.setBackgroundDrawable(getResources().getDrawable(draw));
        this.setLayoutParams(params);
        layout.addView(this);
    }
}