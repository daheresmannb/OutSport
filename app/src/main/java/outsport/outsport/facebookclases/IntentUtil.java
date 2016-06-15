package outsport.outsport.facebookclases;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by daniel on 10-04-16.
 */
public class IntentUtil {

    private Activity activity;

    // constructor
    public IntentUtil(Activity activity) {
        this.activity = activity;
    }

    public void showAccessToken() {
        Intent i = new Intent(activity, AccessTokenActivity.class);
        activity.startActivity(i);
    }
}