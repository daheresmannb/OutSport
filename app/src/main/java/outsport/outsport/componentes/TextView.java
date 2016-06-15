package outsport.outsport.componentes;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

public class TextView extends android.widget.TextView {

    public TextView(Context context, LinearLayout layout, String text, int color, String font, float textSize, int gra) {
        super(context);
        this.setText(text);
        this.setTextColor(color);
        this.setTextSize(textSize);
        this.setGravity(gra);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), font));
        layout.addView(this);
    }

    public TextView(Context context, LinearLayout layout, String text, int color, String font, float textSize) {
        super(context);
        this.setText(text);
        this.setTextColor(color);
        this.setTextSize(textSize);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), font));
        layout.addView(this);
    }

    public TextView(Context context, String text, int color, String font, float textSize, int gra) {
        super(context);
        this.setText(text);
        this.setTextColor(color);
        this.setTextSize(textSize);
        this.setGravity(gra);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), font));

    }

    public TextView(Context context, LinearLayout layout, String texto, int color, int gra) {
        super(context);
        this.setText(texto);
        this.setTextColor(color);
        this.setGravity(gra);
        layout.addView(this);
    }

    public TextView(Context context, String texto) {
        super(context);
        this.setText(texto);
    }

    public TextView(Context context, LinearLayout layout, String texto, int gra) {
        super(context);
        this.setText(texto);
        this.setTextColor(Color.BLUE);
        this.setGravity(gra);
        layout.addView(this);
    }

    public TextView(Context context) {
        super(context);
    }
}
