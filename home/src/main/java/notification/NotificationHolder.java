package notification;
import android.service.notification.StatusBarNotification;

public class NotificationHolder {
    StatusBarNotification sbn;
    public NotificationHolder(StatusBarNotification incoming_sbn){
        sbn = incoming_sbn;
    }
}
