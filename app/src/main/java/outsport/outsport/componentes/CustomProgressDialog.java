package outsport.outsport.componentes;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Daniel on 27-05-2016.
 */
public class CustomProgressDialog extends ProgressDialog {
    Object response;

    public CustomProgressDialog(Context context, String mensaje) {
        super(context);
        this.setMessage(mensaje);
        this.setCancelable(false);
    }

    public void showProgressDialog() {
        if(!this.isShowing())
            this.show();
    }
    public void hideProgressDialog() {
        if (this.isShowing()) {
            this.hide();
        }
    }

    public void hideProgressDialog(Object response) {
        if (this.isShowing()) {
            this.hide();
            this.response = response;
        }
    }

    public Object getResponse() {
        return this.response;
    }
}
