package com.demo.ui.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import no.noname.baseapp.R;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static butterknife.ButterKnife.findById;

/**
 * Created by Beka on 8/15/16.
 */
public class UiUtils {
    public static Dialog getProgressDialog(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_wait, null);
        TextView messageView = findById(view, R.id.message);
        messageView.setText(R.string.wait);
        AlertDialog alertDialog = new AlertDialog
                .Builder(context)
                .setCancelable(false)
                .setView(view)
                .create();
        alertDialog.getWindow().setLayout(WRAP_CONTENT, WRAP_CONTENT);
        return alertDialog;
    }
}
