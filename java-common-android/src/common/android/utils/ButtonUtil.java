package common.android.utils;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class ButtonUtil {
    public interface ITapAndHoldListener {
        void onClick(View view);
        void onHolding(View view);
    }

    public static void setTapAndHoldListener(final Button button, final int holdInterval, final ITapAndHoldListener tapAndHoldListener) {
        final Handler handler = new Handler();
        final boolean[] fired = {false};
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                fired[0] = true;
                tapAndHoldListener.onHolding(button);

                HandlerUtil.postDelayed(handler, holdInterval, this);
            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fired[0])
                    return;

                tapAndHoldListener.onClick(v);
            }
        });

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        fired[0] = false;
                        HandlerUtil.postDelayed(handler, holdInterval, runnable);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        handler.removeCallbacks(runnable);
                        break;
                    case MotionEvent.ACTION_UP:
                        handler.removeCallbacks(runnable);
                        break;
                }
                return false;
            }
        });
    }


}
