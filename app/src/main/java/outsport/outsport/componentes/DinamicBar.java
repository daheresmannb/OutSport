package outsport.outsport.componentes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import outsport.outsport.R;


/**
 * Created by Daniel on 03-06-2016.
 */
public class DinamicBar extends CoordinatorLayout {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView img;
    private Toolbar toolbar;
    private NestedScrollView nestedScroll;
    private LinearLayout linearLayout;
    private AppBarLayout appbar;
    private AppBarLayout.LayoutParams collapParams;
    private CollapsingToolbarLayout.LayoutParams imgParams;
    private CollapsingToolbarLayout.LayoutParams toolbarParams;
    private LayoutParams nestedScrollParams;

    public DinamicBar(Context context, DisplayMetrics met) {
        super(context);
        this.setBackgroundColor(Color.RED);

        collapsingToolbarLayout = new CollapsingToolbarLayout(context);
        nestedScroll = new NestedScrollView(context);

        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        appbar = new AppBarLayout(context);
        toolbar = new Toolbar(context);
        img = new ImageView(context);

        collapParams = new AppBarLayout.LayoutParams(
                AppBarLayout.LayoutParams.MATCH_PARENT,
                AppBarLayout.LayoutParams.MATCH_PARENT
        );

        collapParams.setScrollFlags(
                AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                        |
                        AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
        );

        collapsingToolbarLayout.setLayoutParams(collapParams);
        collapsingToolbarLayout.setTitle("holaaaa");

        imgParams = new CollapsingToolbarLayout.LayoutParams(
                CollapsingToolbarLayout.LayoutParams.MATCH_PARENT,
                CollapsingToolbarLayout.LayoutParams.MATCH_PARENT
        );

        imgParams.setCollapseMode(
                CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PARALLAX
        );

        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        img.setFitsSystemWindows(true);

        img.setLayoutParams(imgParams);
        ///////////// toolbar /////////////////////////////////////////
        toolbarParams = new CollapsingToolbarLayout.LayoutParams(
                CollapsingToolbarLayout.LayoutParams.MATCH_PARENT,
                getActionBarSize(context)
        );
        toolbarParams.setCollapseMode(
                CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN
        );

        toolbar.setLayoutParams(toolbarParams);
        ///////////////////////////////////////////////////////////////
        nestedScrollParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );

        nestedScrollParams.setBehavior(new AppBarLayout.ScrollingViewBehavior());
        nestedScroll.setLayoutParams(nestedScrollParams);
        //////////////////// Adding views ////////////////////////////////
        this.addView(nestedScroll);

        nestedScroll.addView(
                linearLayout,
                NestedScrollView.LayoutParams.MATCH_PARENT,
                NestedScrollView.LayoutParams.WRAP_CONTENT
        );

        this.addView(
                appbar,
                LayoutParams.MATCH_PARENT,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 250, met)
        );
        appbar.addView(collapsingToolbarLayout);
        collapsingToolbarLayout.addView(img);
        collapsingToolbarLayout.addView(toolbar);
    }

    private int getActionBarSize(Context context) {
        final TypedArray actionBarSizeArray = context.getTheme().obtainStyledAttributes(
                new int[] {
                        R.attr.actionBarSize
                }
        );
        int actionbarSize = (int) actionBarSizeArray.getDimension(0, 0);
        actionBarSizeArray.recycle();
        return actionbarSize;
    }

    public CollapsingToolbarLayout getCollapsingToolbarLayout() {
        return collapsingToolbarLayout;
    }

    public ImageView getImg() {
        return img;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public NestedScrollView getNestedScroll() {
        return nestedScroll;
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }

    public AppBarLayout getAppbar() {
        return appbar;
    }

    public AppBarLayout.LayoutParams getCollapParams() {
        return collapParams;
    }

    public CollapsingToolbarLayout.LayoutParams getImgParams() {
        return imgParams;
    }

    public CollapsingToolbarLayout.LayoutParams getToolbarParams() {
        return toolbarParams;
    }

    public LayoutParams getNestedScrollParams() {
        return nestedScrollParams;
    }
}