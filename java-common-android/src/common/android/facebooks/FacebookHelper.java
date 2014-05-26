//package common.android.facebooks;
//
//import android.app.Activity;
//import com.facebook.Session;
//import com.facebook.SessionState;
//import common.basic.logs.Logger;
//import common.basic.utils.CollectionUtil;
//
//import java.util.List;
//
//public class FacebookHelper {
//
//
//    public static final String read_stream = "read_stream";
//    public static final String publish_stream = "publish_stream";
//
//    public static boolean isLoggedIn() {
//        Session session = Session.getActiveSession();
//        return session != null && session.isOpened();
//    }
//
//    public static void openReadPermission(final Activity activity, final boolean allowLoginUI, final List<String> listPermission, final ICallbackSession callbackSession) {
//        Logger.i();
//
//        Session.openActiveSession(activity, allowLoginUI, new StatusCallbackOnce() {
//            @Override
//            protected void onSuccess(Session session, SessionState sessionState) {
//
//
//                if (CollectionUtil.isSubsetOf(listPermission, session.getPermissions())) {
//                    callbackSession.callback(session);
//                    return;
//                }
//
//                session.addCallback(new StatusCallbackOnce() {
//                    @Override
//                    protected void onSuccess(Session session, SessionState sessionState) {
//                        callbackSession.callback(session);
//                    }
//                });
//
//                session.requestNewReadPermissions(new Session.NewPermissionsRequest(activity, listPermission));
//            }
//        });
//    }
//
//
//    public static void openPublishPermission(final Activity activity, final boolean allowLoginUI, final List<String> listPermission, final ICallbackSession callbackSession) {
//        Logger.i();
//
//        Session.openActiveSession(activity, allowLoginUI, new StatusCallbackOnce() {
//            @Override
//            protected void onSuccess(Session session, SessionState sessionState) {
//                if (CollectionUtil.isSubsetOf(listPermission, session.getPermissions())) {
//                    callbackSession.callback(session);
//                    return;
//                }
//
//                session.addCallback(new StatusCallbackOnce() {
//                    @Override
//                    protected void onSuccess(Session session, SessionState sessionState) {
//                        callbackSession.callback(session);
//                    }
//                });
//
//                session.requestNewPublishPermissions(new Session.NewPermissionsRequest(activity, listPermission));
//            }
//        });
//    }
//
//
//}
