package outsport.outsport;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

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


public class RegistroFormActivity extends Activity implements View.OnClickListener, RequestQueue.RequestFinishedListener<Object> {

    private LinearLayout contenedor;
    private LinearLayout layout_logo;
    private LinearLayout layout_edit;
    private LinearLayout layout_buttons;
    private ProgressDialog pdia;
    private EditText editRut;
    private EditText editNombre;
    private EditText editApellido;
    private EditText editEmail;
    private EditText editPass;
    private Button buttonRegistro;
    private ImageView logo;
    private Peticiones pet;
    private RequestQueue mQueue;
    private Usuarios user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics met = getResources().getDisplayMetrics();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        pet = new Peticiones(this);
        mQueue = CustomVolleyRequestQueue.getInstance(
                this.getApplicationContext()
        ).getRequestQueue();
        mQueue.addRequestFinishedListener(this);
        /////////////////// CONTENEDOR /////////////////////////////////////////////////////////////////////////
        contenedor = new LinearLayout(
                this,
                LinearLayout.VERTICAL,
                Gravity.CENTER,
                Color.TRANSPARENT,
                R.mipmap.background_splash
        );
        ////////////////////fin contenedor //////////////////////////////////////////////////////////////////////////////////////
        layout_logo = new LinearLayout(
                this,
                LinearLayout.VERTICAL,
                Gravity.TOP,
                Color.argb(23, 24, 230, 12),
                new int[]{
                        20,
                        Porcentaje.result(met.heightPixels, 1),
                        20,
                        15
                }, // padding
                met
        );

        layout_edit = new LinearLayout(
                this,
                LinearLayout.VERTICAL,
                Gravity.TOP,
                Color.TRANSPARENT,
                new int[]{
                        20,
                        Porcentaje.result(met.heightPixels, 1),
                        25,
                        35
                }, // padding
                met
        );

        layout_buttons = new LinearLayout(
                this,
                LinearLayout.VERTICAL,
                Gravity.TOP,
                Color.TRANSPARENT,
                new int[]{
                        new Porcentaje().result(met.heightPixels, 1),
                        new Porcentaje().result(met.heightPixels, 1),
                        20,
                        new Porcentaje().result(met.heightPixels, 1)
                }, // padding
                met
        );

        logo = new ImageView(
                this,
                layout_logo,
                R.drawable.logo
        );

        editNombre = new EditText(
                this,
                layout_edit,
                new int[]{
                        R.mipmap.edittext_usuario_press,
                        R.mipmap.edittext_usuario_nopress
                },
                10,
                met
        );

        editApellido = new EditText(
                this,
                layout_edit,
                new int[]{
                        R.mipmap.edittext_usuario_press,
                        R.mipmap.edittext_usuario_nopress
                },
                10,
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

        editPass = new EditText(
                this,
                layout_edit,
                new int[]{
                        R.mipmap.edittext_usuario_press,
                        R.mipmap.edittext_usuario_nopress
                },
                10,
                met
        );

        editPass.setInputType(
                InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PASSWORD
        );

        buttonRegistro = new Button(
                this,
                layout_buttons,
                new int[]{
                        R.mipmap.registro_press,
                        R.mipmap.registro_nopress
                },
                15,
                met
        );

        buttonRegistro.setId(1);
        buttonRegistro.setOnClickListener(this);

        contenedor.addView(
                layout_logo,
                ViewGroup.LayoutParams.MATCH_PARENT,
                new Porcentaje().result(met.heightPixels, 22.5)
        );

        contenedor.addView(
                layout_edit,
                ViewGroup.LayoutParams.MATCH_PARENT,
                new Porcentaje().result(met.heightPixels, 50)
        );
        contenedor.addView(
                layout_buttons,
                ViewGroup.LayoutParams.MATCH_PARENT,
                new Porcentaje().result(met.heightPixels, 22.5)
        );
        setContentView(contenedor);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case 1:
                user = new Usuarios(
                        editNombre.getText().toString(),
                        editApellido.getText().toString(),
                        editEmail.getText().toString(),
                        editPass.getText().toString(),
                        0,
                        0
                );

                Gson gson = new Gson();

                JsonElement userJSON = gson.toJsonTree(user);
                System.out.println(userJSON);
                break;

            case 2:

                if (Internet.isOnline(this)) {
                    editApellido.getText();

////////////////////AQUUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII !!!!!! ///////////////////////////////
                    JSONObject ob = new JSONObject(
                            // hay q llenarlo con los edittext
                    );

                    user = new Usuarios(
                            editNombre.getText().toString(),
                            editApellido.getText().toString(),
                            editEmail.getText().toString(),
                            editPass.getText().toString(),
                            0,
                            0
                    );

                    //gson = new Gson();
                    //String userJSON = gson.toJson(user);


                    pet.ProcessDialog();
                    pet.Insertar(
                            ob,
                            Constantes.insertar_usuario
                    );
                    mQueue.add(pet.getJsonRequest());
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            "Compruebe su conexion a Internet",
                            Toast.LENGTH_LONG
                    ).show();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, ActivityInicio.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onRequestFinished(Request<Object> request) {
        if (pet.isFlag()) {
            try {
                String estado = pet.getJsob().getString("estado");
                String mensaje = pet.getJsob().getString("mensaje");
                switch (estado) {
                    case "1": // se inserto correctamente
                        Toast.makeText(
                                this,
                                "Registro Exitoso!",
                                Toast.LENGTH_SHORT
                        ).show();
                        Intent i = new Intent(
                                this,
                                LoginCorreoActivity.class
                        );
                        startActivity(i);
                        finish();
                        break;
                    case "2": // error
                        Toast.makeText(
                                this,
                                mensaje,
                                Toast.LENGTH_SHORT
                        ).show();
                        break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}