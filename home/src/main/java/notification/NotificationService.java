package notification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.support.v4.content.LocalBroadcastManager;
import android.app.Notification;
import modals.Action;
import com.robj.notificationhelperlibrary.utils.NotificationUtils;
import android.app.PendingIntent;
import notification.NotificationHolder;

public class NotificationService extends NotificationListenerService {

    Context context;

    @Override

    public void onCreate() {

        super.onCreate();
        context = getApplicationContext();

    }
    @Override

    public void onNotificationPosted(StatusBarNotification sbn) {


        String pack = sbn.getPackageName();
        String ticker = "";
        Bundle extras = sbn.getNotification().extras;
        String title = extras.getString("android.title");
        String text = extras.getCharSequence("android.text").toString();
        String reply = "";

        if(pack.equals("com.whatsapp")){
            NotificationService.this.cancelNotification(sbn.getKey());
            Action action = NotificationUtils.getQuickReplyAction(sbn.getNotification(), sbn.getPackageName());
            Intent msgrcv = new Intent("Msg");
            msgrcv.putExtra("package", pack);
            msgrcv.putExtra("ticker", ticker);
            msgrcv.putExtra("title", title);
            msgrcv.putExtra("text", text);
//            LocalBroadcastManager.getInstance(context).sendBroadcast(msgrcv);

            // make a api call here

            // on error
            reply = "Hey, I am busy now. Ping me back after some time or call Pushpendu on +917047434141 if this is urgent";
            reply(action,context,reply);
            // on success
            reply = "Hey ! Happy New Year";

        }


    }

    // this function is to reply automatically
    public void reply(Action action,Context context,String reply_string){
        if (action != null) {
            try {
                action.sendReply(context,reply_string);
            } catch (PendingIntent.CanceledException e) {
            }
        } else {
        }
    }

    @Override

    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i("Msg","Notification Removed");

    }
}