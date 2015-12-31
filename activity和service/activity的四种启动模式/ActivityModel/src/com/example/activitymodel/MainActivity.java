package com.example.activitymodel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Activity的四种启动模式详解
 * 
 * http://www.cnblogs.com/zhjsll/p/5086240.html
 * 
 * @author Administrator
 * 
 */
public class MainActivity extends Activity {
	private TextView mEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mEditText = (TextView) findViewById(R.id.textView1);
		mEditText.setText(this.toString());
	}

	public void lunchStandard(View view) {
		// TODO Auto-generated method stub
		startActivity(new Intent(getApplicationContext(),
				StandardActivity.class));
		mEditText.setText(this.toString());
	}

	public void lunchSingtop(View view) {
		// TODO Auto-generated method stub
		startActivity(new Intent(getApplicationContext(), SingtopActivity.class));
		mEditText.setText(this.toString());
	}

	public void lunchSingtask(View view) {
		// TODO Auto-generated method stub
		startActivity(new Intent(getApplicationContext(),
				SingtaskActivity.class));
		mEditText.setText(this.toString());
	}

	public void lunchSingleInstance(View view) {
		// TODO Auto-generated method stub
		startActivity(new Intent(getApplicationContext(),
				SingleInstanceActivity.class));
		mEditText.setText(this.toString());
	}

}
