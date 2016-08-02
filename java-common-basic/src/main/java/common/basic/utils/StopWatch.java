package common.basic.utils;

public class StopWatch {
    final String name;
    long timestamp;
    final boolean isLapTime;

    public StopWatch(String name) {
        this.name = name;
        this.timestamp = System.currentTimeMillis();
        this.isLapTime = false;
    }

    public StopWatch(String name, boolean isLapTime) {
        this.name = name;
        this.timestamp = System.currentTimeMillis();
        this.isLapTime = isLapTime;
    }

    public StopWatch(Object object) {
        this.name = object.getClass().getName();
        this.timestamp = System.currentTimeMillis();
        this.isLapTime = false;
    }

    @Override
    public String toString() {
        long currentTimeMillis = System.currentTimeMillis();
        long duration = currentTimeMillis - timestamp;
        if (isLapTime)
            timestamp = currentTimeMillis;
        return "StopWatch{" +
                "elapsedTime=" + duration +
                ", name='" + name + '\'' +
                '}';
    }
}
