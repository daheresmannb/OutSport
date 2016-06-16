package outsport.outsport;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.Marker;

import outsport.outsport.componentes.DinamicBar;
import outsport.outsport.componentes.LinearLayout;
import outsport.outsport.util.TrasladorDeObjetos;


public class PerfilEventosActivity extends AppCompatActivity implements View.OnClickListener {
    private ActionBar actionBar;
    private DinamicBar db;
    private Intent i;
    private DisplayMetrics met;
    private LinearLayout layout_buttons;
    private Marker ev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTheme(android.R.style.Theme_Holo_NoActionBar_TranslucentDecor);
        obtener_usuario();
        met = getResources().getDisplayMetrics();

        db = new DinamicBar(this,met);
        db.getImg().setBackgroundDrawable(
                getResources().getDrawable(
                        R.mipmap.ic_launcher
                )
        );

        layout_buttons = new LinearLayout(
                this,
                LinearLayout.VERTICAL,
                Gravity.BOTTOM,
                Color.TRANSPARENT,
                new int[]{25, 0, 25, met.widthPixels * 20 / 100}, // padding
                met
        );

        dynamicToolbarColor();
        toolbarTextAppernce();

        db.getLinearLayout().addView(
                layout_buttons,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        db.getCollapsingToolbarLayout().setTitle(
                ev.getTitle()
        );
        setContentView(db);
        setSupportActionBar(db.getToolbar());
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void obtener_usuario() {
        if (TrasladorDeObjetos.getObjeto() != null)
            ev = (Marker) TrasladorDeObjetos.getObjeto();
    }

    private void dynamicToolbarColor() {
        Bitmap bitmap = BitmapFactory.decodeResource(
                getResources(),
                R.mipmap.ic_launcher
        );

        Palette.from(bitmap).generate(
                new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        db.getCollapsingToolbarLayout().setContentScrimColor(
                                palette.getMutedColor(
                                        Color.DKGRAY
                                )
                        );
                        db.getCollapsingToolbarLayout().setStatusBarScrimColor(
                                palette.getMutedColor(
                                        Color.BLUE
                                )
                        );
                    }
                }
        );
    }

    private void toolbarTextAppernce() {
        db.getCollapsingToolbarLayout().setCollapsedTitleTextAppearance(
                R.style.collapsedappbar
        );
        db.getCollapsingToolbarLayout().setExpandedTitleTextAppearance(
                R.style.expandedappbar
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, 0, 0, "Option1").setShortcut('3', 'c');
        menu.add(0, 1, 0, "Option2").setShortcut('3', 'c');
        menu.add(0, 2, 0, "Option3").setShortcut('4', 's');

        SubMenu sMenu = menu.addSubMenu(0, 3, 0, "SubMenu"); //If you want to add submenu
        sMenu.add(0, 4, 0, "SubOption1").setShortcut('5', 'z');
        sMenu.add(0, 5, 0, "SubOption2").setShortcut('5', 'z');

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                // code for option1
                return true;
            case 1:
                // code for option2
                return true;
            case 2:
                // code for option3
                return true;
            case 4:
                // code for subOption1
                return true;
            case 5:
                // code for subOption2
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }
}
