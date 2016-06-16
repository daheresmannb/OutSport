package outsport.outsport;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

import outsport.outsport.componentes.ImageView;
import outsport.outsport.componentes.LinearLayout;
import outsport.outsport.componentes.TextView;


public class SplashActivity extends Activity {
    private LinearLayout contenedor;
    private LinearLayout fl_copyright;
    private TextView copyright;
    private LinearLayout fl_barra;
    private ProgressBar barra;
    private LinearLayout fl_ima;
    private ImageView im_Outsport;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTheme(android.R.style.Theme_Holo_Light_NoActionBar);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        DisplayMetrics met = getResources().getDisplayMetrics();

        im_Outsport = new ImageView(
                this,
                R.mipmap.logo
        );

        copyright = new TextView(this);
        copyright.setText("Derechos Reservados");
        copyright.setGravity(Gravity.CENTER);
        copyright.setLayoutParams(
                new ViewGroup.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
        );
        barra = new ProgressBar(this);
        contenedor = new LinearLayout(
                this,
                LinearLayout.VERTICAL,
                Gravity.CENTER_VERTICAL,
                Color.TRANSPARENT
        );

        contenedor.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.FILL_PARENT
                )
        );
        contenedor.setPadding(
                0,
                (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_SP,
                        50,
                        met
                ),
                0,
                0
        );

        fl_ima = new LinearLayout(
                this,
                LinearLayout.VERTICAL,
                Gravity.CENTER_VERTICAL,
                Color.TRANSPARENT
        );

        fl_ima.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );

        fl_copyright = new LinearLayout(
                this,
                LinearLayout.VERTICAL,
                Gravity.BOTTOM,
                Color.TRANSPARENT
        );
        fl_copyright.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.FILL_PARENT
                )
        );
        fl_copyright.addView(copyright);

        fl_barra = new LinearLayout(
                this,
                LinearLayout.VERTICAL,
                Gravity.CENTER_HORIZONTAL,
                Color.TRANSPARENT
        );

        fl_barra.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );
        fl_barra.setPadding(
                0,
                (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_SP,
                        50,
                        met
                ),
                0,
                0
        );

        fl_barra.addView(barra);
        contenedor.setBackgroundDrawable(
                getResources().getDrawable(
                        R.mipmap.background_splash    //"fondosplash.png"     // imagen de fondo del splas
                )
        );
        fl_ima.addView(im_Outsport);
        contenedor.addView(fl_ima);
        contenedor.addView(fl_barra);
        contenedor.addView(fl_copyright);

        this.setContentView(contenedor); //

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent().setClass(
                        SplashActivity.this,
                        ActivityInicio.class
                );
                startActivity(i);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(
                task,
                3000
        );
    }
}