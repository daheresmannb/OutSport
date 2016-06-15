package outsport.outsport.componentes;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class ImageView extends android.widget.ImageView {
    public ImageView(Context context, int draw) {
        super(context);
        this.setImageDrawable(getResources().getDrawable(draw));
    }

    public ImageView(Context context, LinearLayout layout, int draw, int width, int height, DisplayMetrics met) {
        super(context);
        this.setImageDrawable(getResources().getDrawable(draw));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, width, met), (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, height, met));
        this.setLayoutParams(params);
        layout.addView(this);
    }

    public ImageView(Context context, LinearLayout layout, int draw) {
        super(context);
        this.setImageDrawable(getResources().getDrawable(draw));
        layout.addView(this);
    }

    public ImageView(Context context) {
        super(context);
    }

    public LinearLayout returnwithlayout() {

        LinearLayout l = new LinearLayout(this.getContext());
        l.addView(this);
        return l;
    }
}

