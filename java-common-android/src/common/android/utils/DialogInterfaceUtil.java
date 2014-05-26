package common.android.utils;

import android.content.DialogInterface;

public class DialogInterfaceUtil {
    public static final DialogInterface.OnClickListener onClickListenerNull = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) { }
    };
    public static final DialogInterface.OnCancelListener onCancelListenerNull = new DialogInterface.OnCancelListener() {
        @Override
        public void onCancel(DialogInterface dialogInterface) { }
    };
}
