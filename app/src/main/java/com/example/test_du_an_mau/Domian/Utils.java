package com.example.test_du_an_mau.Domian;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.example.test_du_an_mau.R;

public class Utils {

    public static ProgressDialog showLoader(Context context){
            ProgressDialog dialog = new ProgressDialog(context);
            try {
                dialog.show();
            } catch (WindowManager.BadTokenException e) {

            }
            dialog.setCancelable(false);
            dialog.getWindow()
                    .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.setContentView(R.layout.progressdialog);
            return dialog;

    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);

        View view = activity.getCurrentFocus();

        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void hideLoader(ProgressDialog dialog){

        if(dialog!=null){
        dialog.dismiss();
        }
    }
}
