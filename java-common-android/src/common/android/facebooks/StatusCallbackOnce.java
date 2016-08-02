//package common.android.facebooks;
//
//import com.facebook.Session;
//import com.facebook.SessionState;
//import common.basic.logs.Logger;
//
//public abstract class StatusCallbackOnce implements Session.StatusCallback {
//
//    @Override
//    public void call(final Session session, SessionState state, Exception exception) {
//        Logger.i(state);
//
//        if (!session.isOpened()) {
//            return;
//        }
//
//        session.removeCallback(this);
//
//        if (exception != null)
//        {
//            onException(session, state, exception);
//            return;
//        }
//
//        onSuccess(session, state);
//    }
//
//    protected abstract void onSuccess(Session session, SessionState sessionState);
//    protected void onException(Session session, SessionState sessionState, Exception exception) {
//        Logger.i(exception);
//    }
//}
