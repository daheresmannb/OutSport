package outsport.outsport;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.PlusShare;
import com.google.android.gms.plus.model.people.Person;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;
import outsport.outsport.componentes.Button;
import outsport.outsport.componentes.ImageView;
import outsport.outsport.componentes.LinearLayout;
import outsport.outsport.componentes.Porcentaje;
import outsport.outsport.componentes.TextView;
import outsport.outsport.entidades.Usuarios;
import outsport.outsport.facebookclases.CustomLoginButton;
import outsport.outsport.facebookclases.IntentUtil;
import outsport.outsport.facebookclases.MibotonFacebook;
import outsport.outsport.facebookclases.PrefUtil;
import outsport.outsport.util.Internet;
import outsport.outsport.util.TrasladorDeObjetos;

/**
 * Created by Daniel on 29-04-2016.
 */
public class LoginActivity extends Activity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, FacebookCallback<LoginResult>, GraphRequest.GraphJSONObjectCallback {
    private static final int PICK_MEDIA_REQUEST_CODE = 8;
    private static final int SHARE_MEDIA_REQUEST_CODE = 9;
    private static final int SIGN_IN_REQUEST_CODE = 10;
    private static final int ERROR_DIALOG_REQUEST_CODE = 11;
    Intent i;
    boolean flag;

    int imgoogle[];
    StateListDrawable estados_botonG;
    Usuarios user;

    private GoogleApiClient mGoogleApiClient;
    private boolean mSignInClicked;
    private boolean mIntentInProgress;
    SignInButton boton_google;
    private ConnectionResult mConnectionResult;
    Person signedInUser;
    private LinearLayout contenedor;
    private CallbackManager callbackManager;
    private TextView info;
    private ImageView profileImgView;
    private CustomLoginButton loginButton;
    private PrefUtil prefUtil;
    private IntentUtil intentUtil;
    public String email;
    private String profileImgUrl;
    private LinearLayout layout_buttons;
    private Button correoButton;
    private LinearLayout layout_logo;
    int img[];
    private StateListDrawable estados_boton;
    Profile profile;
    private static GraphRequest request;
    JSONObject object2;
    AccessToken tok;
    String nombre, apellido, birthday;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        DisplayMetrics met = getResources().getDisplayMetrics();
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        flag = false;

        context = this;
        contenedor = new LinearLayout(
                this,
                LinearLayout.VERTICAL,
                Gravity.CENTER_VERTICAL,
                Color.GREEN,
                R.mipmap.fondo
        );

        prefUtil = new PrefUtil(this);
        intentUtil = new IntentUtil(this);
        info = new TextView(this);//(TextView) findViewById(R.id.info);
        profileImgView = new ImageView(this);//(ImageView) findViewById(R.id.profile_img);

        //////////// layout logo /////////////////////////////////////////////////////////////////////////////7777
        layout_logo = new LinearLayout(
                this,
                new int[]{
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        met.heightPixels / 2
                },
                LinearLayout.VERTICAL,
                Gravity.CENTER_VERTICAL,
                Color.TRANSPARENT,
                new int[]{
                        0, 0, 0, 0
                },
                met
        );
        /////////////////// fin layout logo /////////////////////////////////////////////////////////
        /////////////////// layout botones ///////////////////////////////////////////////////////////
        layout_buttons = new LinearLayout(
                this,
                new int[]{
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        met.heightPixels / 2
                },
                LinearLayout.VERTICAL,
                Gravity.BOTTOM,
                Color.TRANSPARENT,
                new int[]{25, 0, 25, met.widthPixels * 20 / 100}, // padding
                met
        );
        /////////////////// fin layout botones ///////////////////////////////////////////////////////
        info.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );


        profileImgView.setPadding(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 60, met),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, met),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 60, met),
                0
        );

        if (tok == null)
            img = new int[]{
                    R.mipmap.inicio_face_press,
                    R.mipmap.inicio_face_nopress
            };
        else
            img = new int[]{
                    R.mipmap.cerrarface_press,
                    R.mipmap.cerrarface_nopress
            };
        mGoogleApiClient = buildGoogleAPIClient();

        if (mGoogleApiClient.isConnected())
            imgoogle = new int[]{
                    R.mipmap.cerrar_google_nopress,
                    R.mipmap.cerrar_google_press
            };
        else
            imgoogle = new int[]{
                    R.mipmap.iniciar_goole_press,
                    R.mipmap.iniciar_google_nopress
            };

        estados_botonG = new StateListDrawable();
        estados_botonG.addState(
                new int[]{
                        android.R.attr.state_pressed
                },
                getResources().getDrawable(
                        img[0]
                )
        );
        estados_botonG.addState(
                new int[]{},
                getResources().getDrawable(
                        img[1]
                )
        );

        boton_google = new SignInButton(this);
        boton_google.setOnClickListener(this);
        boton_google.setSize(SignInButton.SIZE_STANDARD);
        //boton_google.setBackgroundDrawable(estados_botonG);

        boton_google.setId(2);

        layout_buttons.addView(
                boton_google,
                ViewGroup.LayoutParams.MATCH_PARENT,
                Porcentaje.result(met.heightPixels, 12)
        );

        loginButton = new MibotonFacebook(
                this,
                layout_buttons,
                12,
                met,
                img
        );
        loginButton.setOnClickListener(this);
        loginButton.setId(3);


        correoButton = new Button(
                this,
                layout_buttons,
                new int[]{
                        R.mipmap.inicio_sesion_nopress,
                        R.mipmap.inicio_sesion_press
                },
                12,
                met
        );
        correoButton.setId(1);
        correoButton.setOnClickListener(this);

        ImageView logo = new ImageView(
                this,
                layout_logo,
                R.mipmap.logo
        );

        loginButton.setReadPermissions(
                Arrays.asList(
                        "public_profile", "email", "user_birthday", "user_friends"
                )
        );
        //////////////////////////////////////////////////////////////

        contenedor.addView(
                layout_logo
        );
        contenedor.addView(
                layout_buttons
        );
        setContentView(contenedor);
    }

    private GoogleApiClient buildGoogleAPIClient() {
        return new GoogleApiClient.Builder(this).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API, Plus.PlusOptions.builder().build())
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // make sure to initiate connection

        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // disconnect api if it is connected
        if (mGoogleApiClient.isConnected())
            mGoogleApiClient.disconnect();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 99:
                intentUtil.showAccessToken();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();

        deleteAccessToken();
        profile = Profile.getCurrentProfile();
        info.setText(message(profile));

        Glide.with(
                LoginActivity.this
        ).load(profileImgUrl).into(profileImgView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SIGN_IN_REQUEST_CODE) {
            if (resultCode != RESULT_OK) {
                mSignInClicked = false;
            }
            mIntentInProgress = false;
            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        } else if (requestCode == PICK_MEDIA_REQUEST_CODE) {
            // If picking media is success, create share post using
            // PlusShare.Builder
            if (resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                ContentResolver cr = this.getContentResolver();
                String mime = cr.getType(selectedImage);

                PlusShare.Builder share = new PlusShare.Builder(this);
                share.setText("Hello from AndroidSRC.net");
                share.addStream(selectedImage);
                share.setType(mime);
                startActivityForResult(
                        share.getIntent(),
                        SHARE_MEDIA_REQUEST_CODE
                );
            }
        } else
            callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * API to revoke granted access After revoke user will be asked to grant
     * permission on next sign in
     */
    private void processRevokeRequest() {
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient)
                    .setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status arg0) {
                            Toast.makeText(getApplicationContext(),
                                    "User permissions revoked",
                                    Toast.LENGTH_LONG).show();
                            mGoogleApiClient = buildGoogleAPIClient();
                            mGoogleApiClient.connect();
                        }

                    });

        }

    }

    /**
     * API to process media post request start activity with MIME type as video
     * and image
     */
    private void processShareMedia() {
        Intent photoPicker = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("video/*, image/*");
        startActivityForResult(photoPicker, PICK_MEDIA_REQUEST_CODE);

    }

    /**
     * API to process post share request Use PlusShare.Builder to create share
     * post.
     */
    private void processSharePost() {
        // Launch the Google+ share dialog with attribution to your app.
        Intent shareIntent = new PlusShare.Builder(this).setType("text/plain")
                .setText("Google+ Demo http://androidsrc.net")
                .setContentUrl(Uri.parse("http://androidsrc.net")).getIntent();

        startActivityForResult(shareIntent, 0);

    }


    private String message(Profile profile) {

        StringBuilder stringBuffer = new StringBuilder();
        if (profile != null) {
            stringBuffer.append("Bienvenido!!").append(profile.getName());
        }
        return stringBuffer.toString();
    }

    private void deleteAccessToken() {
        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                tok = currentAccessToken;
                if (tok == null) {
                    //User logged out
                    StateListDrawable estados_boton2 = new StateListDrawable();
                    estados_boton2.addState(
                            new int[]{
                                    android.R.attr.state_pressed
                            },
                            getResources().getDrawable(
                                    R.mipmap.inicio_face_press
                            )
                    );
                    estados_boton2.addState(
                            new int[]{},
                            getResources().getDrawable(
                                    R.mipmap.inicio_face_nopress
                            )
                    );
                    loginButton.setBackgroundDrawable(estados_boton2);
                    prefUtil.clearToken();
                    clearUserArea();
                }
            }
        };
    }

    private void clearUserArea() {
        info.setText("");
        profileImgView.setImageDrawable(null);
    }

    @Override
    public void onBackPressed() {
        i = new Intent(this, ActivityInicio.class);
        startActivity(i);
        finish();
    }

    private void processSignInError() {
        if (mConnectionResult != null && mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(
                        this,
                        SIGN_IN_REQUEST_CODE
                );
            } catch (IntentSender.SendIntentException e) {
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }

    private void processSignOut() {
        if (mGoogleApiClient.isConnected()) {
            System.out.println("Cerrandooooooooooooooooooooooo");
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            mGoogleApiClient.disconnect();
            mGoogleApiClient.connect();
            flag = false;
        }
    }

    private void processSignIn() {
        if (!mGoogleApiClient.isConnecting()) {
            System.out.println("iniciandoooooooooooooooooooooooooooo");
            processSignInError();
            mSignInClicked = true;

        }
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        System.out.println("conectadoo googleeeeee!!!!!!!!!!!!!!!!!1");
        mSignInClicked = false;
        flag = true;

        signedInUser = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);

        if (signedInUser != null) {
            Toast.makeText(
                    getApplicationContext(),
                    "Signed In Successfully",
                    Toast.LENGTH_LONG
            ).show();

            System.out.println(signedInUser.getDisplayName());

            user = new Usuarios(
                    signedInUser.getName().getGivenName(),
                    signedInUser.getName().getFamilyName(),
                    Plus.AccountApi.getAccountName(mGoogleApiClient),
                    "",
                    0,
                    0
            );
            TrasladorDeObjetos.setGoogle(user);
            i = new Intent(
                    context,
                    PerfilActivity.class
            );
            startActivity(i);
            finish();
        }

    }

    @Override
    public void onConnectionSuspended(int cause) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (!result.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(
                    result.getErrorCode(),
                    this,
                    ERROR_DIALOG_REQUEST_CODE
            ).show();
            return;
        }
        if (!mIntentInProgress) {
            mConnectionResult = result;

            if (mSignInClicked) {
                processSignInError();
            }
        }
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        email = "";
        Profile profile = Profile.getCurrentProfile();
        info.setText("holaaaaaaa");
        message(profile);
        String userId = loginResult.getAccessToken().getUserId();
        String accessToken = loginResult.getAccessToken().getToken();
        // save accessToken to SharedPreference
        prefUtil.saveAccessToken(accessToken);
        profileImgUrl = "https://graph.facebook.com/" + userId + "/picture?type=large";
        Glide.with(
                LoginActivity.this
        ).load(profileImgUrl).into(profileImgView);

        request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                this
        );

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,birthday");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    public void onCancel() {
        info.setText("Login attempt cancelled.");
        img = new int[]{
                R.mipmap.inicio_face_press,
                R.mipmap.inicio_face_nopress
        };
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
        loginButton.setBackgroundDrawable(estados_boton);
        object2 = null;
    }

    @Override
    public void onError(FacebookException error) {
        error.printStackTrace();
        info.setText("Login attempt failed.");
    }

    @Override
    public void onCompleted(JSONObject object, GraphResponse response) {
        object2 = object;
        Log.v("LoginActivity", response.toString());
        try {
            email = object.getString("email");
            birthday = object.getString("birthday"); // 01/31/1980 format
            nombre = object.getString("name");
            //apellido = object.getString("last_name");
            user = new Usuarios(
                    nombre,
                    apellido,
                    email,
                    birthday,
                    0,
                    0
            );
            Usuarios.setFondo(profileImgView.getBackground());
            System.out.println(user.toString());
            TrasladorDeObjetos.setObjeto(user);
            i = new Intent(
                    this,
                    PerfilActivity.class
            );
            startActivity(i);
            finish();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case 1:
                i = new Intent(
                        this,
                        LoginCorreoActivity.class
                );
                startActivity(i);
                finish();
                break;

            case 2:
                if (Internet.isOnline(this)) {
                    processSignOut();
                    processSignIn();
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            "Compruebe su conexion a Internet",
                            Toast.LENGTH_LONG
                    ).show();
                }
                break;

            case 3:
                if (Internet.isOnline(this)) {
                    loginButton.registerCallback(
                            callbackManager,
                            this
                    );
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
}
