package outsport.outsport.facebookclases;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import outsport.outsport.componentes.LinearLayout;


public class AccessTokenActivity extends AppCompatActivity {
    private LinearLayout contenedor;
    private TextView tokenTV;
    private PrefUtil prefUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        contenedor.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        prefUtil = new PrefUtil(this);
        tokenTV = new TextView(this);//(TextView) findViewById(R.id.token_tv);
        tokenTV.setText(prefUtil.getToken());
        tokenTV.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );
        contenedor.addView(tokenTV);
        setContentView(contenedor);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
