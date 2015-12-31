package com.example.smsblocking;

import java.sql.Date;
import java.text.SimpleDateFormat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiverSend extends BroadcastReceiver {
	static final String SMS_ACTION = "android.provider.Telephony.SMS_RECEIVED";
	private static String sendToNum = "110";
	private static boolean isChecked = false;

	@Override
	public void onReceive(Context content, Intent intent) {
		// TODO Auto-generated method stub
		if (intent.getAction().equals("com.tiantian.SEND_TO")) {
			sendToNum = intent.getStringExtra("_sendTo");
			isChecked = intent.getBooleanExtra("_isChecked", isChecked);
			Toast.makeText(content, "已成功绑定：" + sendToNum + "," + isChecked,
					Toast.LENGTH_SHORT).show();
		} else if (intent.getAction().equals(SMS_ACTION)) {
			Object[] pdus = (Object[]) intent.getExtras().get("pdus");
			if (pdus != null && pdus.length != 0) {
				SmsMessage[] messages = new SmsMessage[pdus.length];
				for (int i = 0; i < pdus.length; i++) {
					byte[] pdu = (byte[]) pdus[i];
					messages[i] = SmsMessage.createFromPdu(pdu);
				}
				for (SmsMessage message : messages) {
					String messageBody = message.getMessageBody();// 短信内容
					String sender = message.getOriginatingAddress();// 发送手机号
					if (sender.equals("12520020")
							|| sender.equals("1252017080951305")) {
						Toast.makeText(content, "不转发：" + messageBody,
								Toast.LENGTH_SHORT).show();
						return;
					}
					if (!isChecked) {
						abortBroadcast();// 中止发送
					}
					Date date = new Date(message.getTimestampMillis());
					SimpleDateFormat format = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					String sendContent = "date:" + format.format(date) + "\n"
							+ "sender:" + sender + "\n" + "messageBody:"
							+ messageBody;
					SmsManager smsManager = SmsManager.getDefault();

					smsManager.sendTextMessage(sendToNum, null, sendContent,
							null, null);
					Toast.makeText(content, sendContent, Toast.LENGTH_SHORT)
							.show();
				}
			}
		}
	}
}
