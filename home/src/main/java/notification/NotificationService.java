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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.robj.notificationhelperlibrary.utils.NotificationUtils;
import android.app.PendingIntent;

import org.json.JSONException;
import org.json.JSONObject;

import notification.NotificationHolder;

public class NotificationService extends NotificationListenerService {

    Context context;
    String reply = "";
    Action action;
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
//        String reply = "";

        if(pack.equals("com.whatsapp")){
            NotificationService.this.cancelNotification(sbn.getKey());
            action = NotificationUtils.getQuickReplyAction(sbn.getNotification(), sbn.getPackageName());
            Intent msgrcv = new Intent("Msg");
            msgrcv.putExtra("package", pack);
            msgrcv.putExtra("ticker", ticker);
            msgrcv.putExtra("title", title);
            msgrcv.putExtra("text", text);
//            LocalBroadcastManager.getInstance(context).sendBroadcast(msgrcv);
                                reply = "Hey, I am busy now. Ping me back after some time or call Pushpendu on +917047434141 if this is urgent";
                    reply(action,context,reply);

            // make a api call here

//            JSONObject json = new JSONObject();
//            try{
//                json.put("name","morpheus");
//                json.put("job","leader");
//            }catch (JSONException e){
//                e.printStackTrace();
//            }
//            String url = " https://reqres.in/api/users";
//            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, json,
//                    new Response.Listener <JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            System.out.println("responce------->>>>>>"+response.toString());
//                            try {
//                                JSONObject serverResp = new JSONObject(response.toString());
//                                reply = "Hey ! Happy New Year";
//                            }catch (JSONException e){
//                                e.printStackTrace();
//                            }
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    reply = "Hey, I am busy now. Ping me back after some time or call Pushpendu on +917047434141 if this is urgent";
//                    reply(action,context,reply);
//                }
//            });
            // on error
//            reply = "Hey, I am busy now. Ping me back after some time or call Pushpendu on +917047434141 if this is urgent";
//            reply(action,context,reply);
            // on success
//            reply = "Hey ! Happy New Year";

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