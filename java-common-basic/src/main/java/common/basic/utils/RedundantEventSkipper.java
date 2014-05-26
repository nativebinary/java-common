package common.basic.utils;

import common.basic.logs.Logger;

public class RedundantEventSkipper {
    private String name;
    private int duration;
    public long lastNewButtonClickTime;

    public RedundantEventSkipper(String name, int duration) {
        this.name = name;
        this.duration = duration;
        this.lastNewButtonClickTime = 0;
    }


    public boolean skipEvent() {
        long currentTimeMillis = System.currentTimeMillis();
        final long diff = currentTimeMillis - lastNewButtonClickTime;
        if (diff < duration)
        {
            Logger.w(name, diff);
            return true;
        }

        lastNewButtonClickTime = currentTimeMillis;
        return false;
    }
}
