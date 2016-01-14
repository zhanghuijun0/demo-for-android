package com.example.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class OtherActivity extends Activity {
	private TextView tvTitle;
	private TextView tvContent;
	private Button btnSend;
	private String title;
	private String content;
	private Button btnClear;
	NotificationManager nm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other);
		tvTitle = (TextView) findViewById(R.id.title);
		tvContent = (TextView) findViewById(R.id.content);
		btnSend = (Button) findViewById(R.id.send);
		btnClear = (Button) findViewById(R.id.clear);
		// 1.得到NotificationManager服务
		nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		btnSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				send();
			}
		});

		btnClear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				nm.cancelAll();
			}
		});

	}

	public void send() {
		title = tvTitle.getText().toString();// 标题
		content = tvContent.getText().toString();// 内容

		// 2.实例化一个通知，指定图标、概要、时间
		Notification n = new Notification(R.drawable.ic_launcher, "通知",
				System.currentTimeMillis());
		// 3.指定通知的标题、内容和intent
		Intent intent = new Intent(this, MainActivity.class);
		PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
		n.setLatestEventInfo(this, title, content, pi);
		// 指定声音
		// n.defaults = Notification.DEFAULT_SOUND;
		// 4.发送通知
		nm.notify(1, n);
	}

}