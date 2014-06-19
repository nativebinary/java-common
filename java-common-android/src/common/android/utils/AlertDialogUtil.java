package common.android.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertDialogUtil {
    public static void confirm(Context context, int title, int message, DialogInterface.OnClickListener onClickListenerOk, DialogInterface.OnClickListener onClickListenerCancel) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, onClickListenerOk)
                .setNegativeButton(android.R.string.cancel, onClickListenerCancel)
                .show();
    }

    public static void confirm(Context context, int title, int message, DialogInterface.OnClickListener onClickListenerOk) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, onClickListenerOk)
                .show();
    }
}
