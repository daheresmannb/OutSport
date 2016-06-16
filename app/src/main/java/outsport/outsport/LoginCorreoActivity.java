package outsport.outsport;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

import outsport.outsport.BD.Constantes;
import outsport.outsport.BD.CustomVolleyRequestQueue;
import outsport.outsport.BD.Peticiones;
import outsport.outsport.componentes.Button;
import outsport.outsport.componentes.EditText;
import outsport.outsport.componentes.ImageView;
import outsport.outsport.componentes.LinearLayout;
import outsport.outsport.componentes.Porcentaje;
import outsport.outsport.entidades.Usuarios;
import outsport.outsport.util.Internet;
import outsport.outsport.util.TrasladorDeObjetos;

public class LoginCorreoActivity extends Activity implements View.OnClickListener,/** paso 1 **/ RequestQueue.RequestFinishedListener<Object> {

    private LinearLayout contenedor;
    private LinearLayout layout_edit;
    private LinearLayout layout_logo;
    private LinearLayout layout_buttons;
    private EditText editEmail;
    private EditText editPass;
    private ImageView logo;
    private ProgressDialog pdia;
    private Button olvidepass;
    private Button buttonLogin;
    Intent i;
    /************* paso 2 *************************/
    private RequestQueue mQueue;
    private Peticiones pet;
    /************* paso 2 *************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // bloquea giro de pantalla
        DisplayMetrics met = getResources().getDisplayMetrics(); // obtiene dimenciones de la pantalla

        /************* paso 3 *************************/
        pet = new Peticiones(this);
        mQueue = CustomVolleyRequestQueue.getInstance(
                this.getApplicationContext()
        ).getRequestQueue();
        mQueue.addRequestFinishedListener(this);
        /************* paso 3 *************************/

        /////////////////// CONTENEDOR /////////////////////////////////////////////////////////////////////////
        contenedor = new LinearLayout(
                this,
                LinearLayout.VERTICAL,
                Gravity.CENTER,
                Color.BLACK,
                R.mipmap.fondo
        );
        /////////////////// layout botones ///////////////////////////////////////////////////////////
        layout_buttons = new LinearLayout(
                this,
                LinearLayout.VERTICAL,
                Gravity.TOP,
                Color.TRANSPARENT,
                new int[]{
                        20,
                        Porcentaje.result(met.heightPixels, 1),
                        20,
                        15
                }, // padding
                met
        );
        /////////////////// fin layout botones ///////////////////////////////////////////////////////
        /////////////////// layout logo ////////////////////////////////////////////////////////////////
        layout_logo = new LinearLayout(
                this,
                LinearLayout.VERTICAL,
                Gravity.BOTTOM,
                Color.TRANSPARENT,
                new int[]{
                        25,
                        10,
                        25,
                        Porcentaje.result(met.heightPixels, 2),
                }, // padding
                met
        );
        /////////////////// fin layout logo ////////////////////////////////////////////////////////////////
        /////////////////// layout edit text ////////////////////////////////////////////////////////////////
        layout_edit = new LinearLayout(
                this,
                LinearLayout.VERTICAL,
                Gravity.BOTTOM,
                Color.TRANSPARENT,
                new int[]{25, 0, 25, 10}, // padding
                met
        );

        editEmail = new EditText(
                this,
                layout_edit,
                new int[]{
                        R.mipmap.edittext_usuario_press,
                        R.mipmap.edittext_usuario_nopress
                },
                10,
                met
        );
        editEmail.setText("dheresmann2012@alu.uct.cl");
        editPass = new EditText(
                this,
                layout_edit,
                new int[]{
                        R.mipmap.edittext_pass_press,
                        R.mipmap.edittext_pass_nopress
                },
                10,
                met
        );

        editPass.setInputType(
                InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PASSWORD
        );
        editPass.setText("123456");

        olvidepass = new Button(
                this,
                layout_edit,
                new int[]{
                        R.mipmap.olvidaste_contrasena_2, // cambiar x correo
                        R.mipmap.olvidaste_contrasena_2
                },
                new int[]{70, 2},
                met
        );

        buttonLogin = new Button(
                this,
                layout_buttons,
                new int[]{
                        R.mipmap.inicio_sesion_nopress,
                        R.mipmap.inicio_sesion_press
                },
                15,
                met
        );

        logo = new ImageView(
                this,
                layout_logo,
                R.mipmap.logo
        );

        buttonLogin.setId(1);
        olvidepass.setId(2);
        editEmail.setId(3);
        editPass.setId(4);

        buttonLogin.setOnClickListener(this);
        olvidepass.setOnClickListener(this);

        contenedor.addView(
                layout_logo,
                ViewGroup.LayoutParams.MATCH_PARENT,
                Porcentaje.result(met.heightPixels, 35)
        );
        contenedor.addView(
                layout_edit,
                ViewGroup.LayoutParams.MATCH_PARENT,
                Porcentaje.result(met.heightPixels, 30)
        );
        contenedor.addView(
                layout_buttons,
                ViewGroup.LayoutParams.MATCH_PARENT,
                Porcentaje.result(met.heightPixels, 30)
        );
        setContentView(contenedor);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mQueue != null)
            mQueue.cancelAll(
                    this.getClass().getSimpleName()
            );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case 1:
                /************* paso 4 *************************/
                /***********************
                 solicitar informacion
                 ***********************/
                if (Internet.isOnline(this)) {
                    pet.ProcessDialog();
                    pet.Obtener((
                            Constantes.obtener_usuario_x_correo +
                                    editEmail.getText()
                    ).trim());
                    mQueue.add(pet.getJsonRequest());
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            "Compruebe su conexion a Internet",
                            Toast.LENGTH_LONG
                    ).show();
                }
                /************* paso 4 *************************/
                break;
        }
    }

    @Override
    public void onBackPressed() {
        i = new Intent(
                this,
                LoginActivity.class
        );
        startActivity(i);
        finish();
    }

    @Override
    public void onRequestFinished(Request<Object> request) {
        /************* paso 5 *************************/
        /******************
         Obtener respuesta
         ******************/
        if (pet.isFlag() == true) {
            try {
                // Obtener atributo "estado"
                String estado = pet.getJsob().getString("estado");
                switch (estado) {
                    case "1": // EXITO
                        JSONObject info = pet.getJsob().getJSONObject("usuarios");
                        if (info.getString("password").equalsIgnoreCase(String.valueOf(editPass.getText()))) {
                            Usuarios user = new Usuarios(
                                    info.getString("nombre"),
                                    info.getString("apellido"),
                                    info.getString("correo"),
                                    info.getString("password"),
                                    info.getInt("id_nivel"),
                                    info.getInt("id_foto")
                            );

                            TrasladorDeObjetos.setObjeto(user);
                            i = new Intent(
                                    this,
                                    PerfilActivity.class
                            );
                            startActivity(i);
                            finish();
                            pet.setFlag(false);
                        } else {
                            Toast.makeText(
                                    this,
                                    "Clave incorrectaaaaaa!!!!!!!",
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                        break;

                    case "2": // FALLIDO
                        String mensaje2 = pet.getJsob().getString("mensaje");
                        Log.d(mensaje2, "El usuario no existe");
                        break;
                }
            } catch (JSONException e) {

            }
        } else
            Toast.makeText(
                    this,
                    "Correo Incorrecto",
                    Toast.LENGTH_LONG
            ).show();
        /************* paso 5 *************************/
    }
}
