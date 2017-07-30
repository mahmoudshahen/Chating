package shahen.mahmoud.mobilechat;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by harty on 7/30/2017.
 */

public class Utilits {

    public static void hideSoftKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
