package com.example.getipaddress;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.app.Activity;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 获得手机的IP地址
 * 
 * 2016.01.22
 * 
 * @author Administrator
 *
 */
public class MainActivity extends Activity {
	private TextView ipTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ipTextView = (TextView) findViewById(R.id.ipTextView);
		ipTextView.setText("IP地址：" + getIpAddresForWifi() + "\n（3G）IP:" + getLocalIpAddress());
	}

	public void refreshIp(View v) {
		ipTextView.setText("IP地址：" + getIpAddresForWifi() + "\n（3G）IP:" + getLocalIpAddress());
		Toast.makeText(MainActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
	}

	/**
	 * 在wifi环境下获得IP地址
	 * 
	 * @return IP地址
	 */
	private String getIpAddresForWifi() {
		WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		int i = wifiInfo.getIpAddress();
		return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + (i >> 24 & 0xFF);
	}

	/**
	 * wifi 或 3G
	 * 
	 * @return IP地址
	 */
	public String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			Log.e("WifiPreference IpAddress", ex.toString());
		}
		return null;
	}
}
