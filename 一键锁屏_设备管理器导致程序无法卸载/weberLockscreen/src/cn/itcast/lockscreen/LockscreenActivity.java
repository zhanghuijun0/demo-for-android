package cn.itcast.lockscreen;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

public class LockscreenActivity extends Activity {

	DevicePolicyManager policyManager;
	ComponentName componentName;
	private final int MY_REQUEST_CODE = 9999;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 获取设备管理服务
		policyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
		componentName = new ComponentName(this, LockReceiver.class);

		if (policyManager.isAdminActive(componentName)) {
			policyManager.lockNow();
			finish();
		}else{
			activeManage();
		}
		setContentView(R.layout.main);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// 获取权限成功，立即锁屏并finish自己，否则继续获取权限
		if (requestCode == MY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
			policyManager.lockNow();
			finish();
		} else {
			activeManage();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * 获取权限
	 */
	private void activeManage() {
		// TODO Auto-generated method stub
		// 使用隐式意图调用系统方法来激活指定的设备管理器
		Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);// 权限列表
		intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "一键锁屏"); // 描述
		startActivityForResult(intent, MY_REQUEST_CODE);
	}

}