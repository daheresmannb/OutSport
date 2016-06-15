package outsport.outsport;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import outsport.outsport.componentes.EditText;
import outsport.outsport.componentes.LinearLayout;

/**
 * Created by Daniel on 29-04-2016.
 */
public class InicioSesionActivity extends Activity implements View.OnClickListener{
    private LinearLayout contenedor;
    private LinearLayout layout_edit;
    private EditText editPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /////////////////// CONTENEDOR /////////////////////////////////////////////////////////////////////////
        contenedor = new LinearLayout(
                this,
                LinearLayout.VERTICAL,
                Gravity.CENTER,
                Color.TRANSPARENT,
                R.mipmap.background_splash
        );
        ////////////////////fin contenedor //////////////////////////////////////////////////////////////////////////////////////
        layout_edit = new LinearLayout(
                this,
                LinearLayout.VERTICAL,
                Gravity.CENTER_HORIZONTAL,
                Color.TRANSPARENT
        );

        editPass = new EditText(
                this,
                layout_edit
        );

        layout_edit.addView(editPass);
        contenedor.addView(layout_edit);
        setContentView(contenedor);
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(this, ActivityInicio.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onClick(View v) {


    }
}
