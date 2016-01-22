package com.newbiefly.money;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends FragmentActivity implements OnClickListener {
	private static final Intent accessibilityIntent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
	private static final Intent notificationIntent = new Intent(
			"android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
	private long mExitTime;
	private ToggleButton accessibilityToggleButton, notificationToggleButton;
	private RelativeLayout notificationRelativeLayout, noticeRelativeLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		accessibilityToggleButton = (ToggleButton) findViewById(R.id.tb_settings_item_accessibility);
		accessibilityToggleButton.setOnClickListener(this);
		notificationToggleButton = (ToggleButton) findViewById(R.id.tb_settings_item_notification);
		notificationToggleButton.setOnClickListener(this);
		notificationRelativeLayout = (RelativeLayout) findViewById(R.id.rl_notification);
		noticeRelativeLayout = (RelativeLayout) findViewById(R.id.rl_notice);
		noticeRelativeLayout.setOnClickListener(this);
		if (Build.VERSION.SDK_INT >= 18) {
			notificationRelativeLayout.setVisibility(View.VISIBLE);
		} else {
			notificationRelativeLayout.setVisibility(View.GONE);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		changeLabelStatus();
	}

	private void changeLabelStatus() {
		boolean isAccessibilityEnabled = LuckyApplication.isAccessibleEnabled(getApplication());
		accessibilityToggleButton.setChecked(isAccessibilityEnabled);
		Log.e("MainActivity", "isAccessibilityEnabled:" + isAccessibilityEnabled);
		if (isAccessibilityEnabled) {
			accessibilityToggleButton.setBackgroundResource(R.drawable.toggle_btn_push_on);
		} else {
			accessibilityToggleButton.setBackgroundResource(R.drawable.toggle_btn_push_off);
		}
		if (Build.VERSION.SDK_INT >= 18) {
			boolean isNotificationEnabled = LuckyApplication.isNotificationEnabled(getApplication());
			notificationToggleButton.setChecked(isNotificationEnabled);
			if (isNotificationEnabled) {
				notificationToggleButton.setBackgroundResource(R.drawable.toggle_btn_push_on);
			} else {
				notificationToggleButton.setBackgroundResource(R.drawable.toggle_btn_push_off);
			}
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tb_settings_item_accessibility:
			startActivity(accessibilityIntent);
			break;
		case R.id.tb_settings_item_notification:
			startActivity(notificationIntent);
			break;
		default:
			break;
		}
	}

}
