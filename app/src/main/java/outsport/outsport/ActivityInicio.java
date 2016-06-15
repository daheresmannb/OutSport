package outsport.outsport;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import outsport.outsport.componentes.Button;
import outsport.outsport.componentes.ImageView;
import outsport.outsport.componentes.LinearLayout;


/**
 * Created by Daniel on 29-04-2016.
 */
public class ActivityInicio extends Activity implements View.OnClickListener {
    private LinearLayout contenedor;
    private LinearLayout layout_logo;
    private LinearLayout layout_buttons;
    private Button button_entrar;
    private Button button_registrar;
    private ProgressDialog pdia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // bloquea giro de pantalla
        DisplayMetrics met = getResources().getDisplayMetrics(); // obtiene dimenciones de la pantalla
        pdia = new ProgressDialog(this);

        /////////////////// CONTENEDOR /////////////////////////////////////////////////////////////////////////
        contenedor = new LinearLayout(
                this,
                LinearLayout.VERTICAL,
                Gravity.CENTER,
                Color.BLUE,
                R.mipmap.background_splash
        );
        ////////////////////fin contenedor //////////////////////////////////////////////////////////////////////////////////////
        //////////// layout logo /////////////////////////////////////////////////////////////////////////////7777
        layout_logo = new LinearLayout(
                this,
                new int[]{
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        met.heightPixels /2
                },
                LinearLayout.VERTICAL,
                Gravity.CENTER_VERTICAL,
                Color.TRANSPARENT,
                new int[]{
                    0,0,0,0
                },
                met
        );
        /////////////////// fin layout logo /////////////////////////////////////////////////////////
        /////////////////// layout botones ///////////////////////////////////////////////////////////
        layout_buttons = new LinearLayout(
                this,
                new int[] {
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        met.heightPixels /2
                },
                LinearLayout.VERTICAL,
                Gravity.BOTTOM,
                Color.TRANSPARENT,
                new int[] {
                        25,
                        0,
                        25,
                        met.widthPixels*20/100
                }, // padding
                met
        );
        /////////////////// fin layout botones ///////////////////////////////////////////////////////
        //////////////////  botones  /////////////////////////////////////////////////////////////////
        button_registrar = new Button(
                this,
                layout_buttons,
                new int[]{
                        R.mipmap.registro_press,
                        R.mipmap.registro_nopress
                },
                15,
                met
        );

        button_entrar = new Button(
                this,
                layout_buttons,
                new int[]{
                        R.mipmap.inicio_sesion_nopress,
                        R.mipmap.inicio_sesion_press
                },
                15,
                met
        );

        button_registrar.setId(1);
        button_registrar.setOnClickListener(this);

        button_entrar.setId(2);
        button_entrar.setOnClickListener(this);
        ////////////////// fin botones ////////////////////////////////////////

        ImageView logo = new ImageView(
                this,
                layout_logo,
                R.mipmap.logo
        );
        System.out.print(printKeyHash(this));
        contenedor.addView(layout_logo);
        contenedor.addView(layout_buttons); // agregar layouts al contenedor detodo
        setContentView(contenedor);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case 1:
                i = new Intent(
                        this,
                        RegistroFormActivity.class
                );
                startActivity(i);
                finish();


                break;
            case 2:
                i = new Intent(
                        this,
                        LoginActivity.class
                );
                startActivity(i);
                finish();
                break;
        }
    }



    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();
            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);
            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));
                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        }
        catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }
        return key;
    }
}