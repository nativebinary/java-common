package common.android.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class NotificationManagerUtil {
    public static void notify(Context context, int notificationId, long when, int icon, String ticker, String contentTitle, String contentText, Intent intent) {
        Notification notification = createNotification(context, when, icon, ticker, contentTitle, contentText, intent);
        notify(context, notificationId, notification);
    }

    public static Notification createNotification(Context context, long when, int icon, String ticker, String contentTitle, String contentText, Intent intent) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        return new NotificationCompat.Builder(context)
                .setWhen(when)
                .setSmallIcon(icon)
                .setTicker(ticker)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
    }

    public static void notify(Context context, int notificationId, Notification notification) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notification);
    }
}
