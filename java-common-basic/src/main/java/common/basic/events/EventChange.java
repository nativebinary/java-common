package common.basic.events;

public class EventChange<T> {
    public boolean onChanging(T before, T after) {
        return true;
    }
    public void onChanged(T before, T after) {

    }
}
