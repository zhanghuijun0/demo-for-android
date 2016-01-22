package com.newbiefly.money;

import android.app.Notification;
import android.app.PendingIntent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 对通知栏进行监听
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("NewApi")
public class NotificationService extends NotificationListenerService {
	private String TAG = "NotificationService";
	@Override
	public void onNotificationPosted(StatusBarNotification sbn) {
		Notification notification = sbn.getNotification();
		Log.d(TAG, "===notification:"+notification);
		if (null != notification) {
			Bundle extras = notification.extras;
			if (null != extras) {
				List<String> textList = new ArrayList<String>();
				String title = extras.getString("android.title");
				if (!TextUtils.isEmpty(title))
					textList.add(title);

				String detailText = extras.getString("android.text");
				Log.w(TAG, "事件detailText:" + detailText);
				if (!TextUtils.isEmpty(detailText))
					textList.add(detailText);

				if (textList.size() > 0) {
					for (String text : textList) {
						if (!TextUtils.isEmpty(text) && text.contains("[微信红包]")) {
							LuckyApplication.unlockScreen(getApplication());
							final PendingIntent pendingIntent = notification.contentIntent;
							try {
								LuckyApplication.autoGetMoney = true;
								pendingIntent.send();
							} catch (PendingIntent.CanceledException e) {
								Log.w(TAG, "事件e:" + e.getMessage());
							}
							break;
						}
					}
				}
			}
		}
	}

	@Override
	public void onNotificationRemoved(StatusBarNotification sbn) {
		Log.d(TAG, "===sbn:"+sbn);
	}
}
