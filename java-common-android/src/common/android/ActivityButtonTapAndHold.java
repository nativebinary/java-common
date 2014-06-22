package common.android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import common.android.extensions.FragmentActivityBase;
import common.android.utils.ButtonUtil;
import common.android.utils.ViewUtil;
import common.basic.logs.Logger;

public class ActivityButtonTapAndHold extends FragmentActivityBase {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_button_tap_and_hold);

        final Button button = ViewUtil.findButton(this, R.id.button);
        final TextView textView = ViewUtil.findTextView(this, R.id.textView);
        ButtonUtil.setTapAndHoldListener(button, 1000, new ButtonUtil.ITapAndHoldListener() {
            @Override
            public void onClick(View view) {
                Logger.e();
            }

            @Override
            public void onHolding(View view) {
                Logger.e();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}