package com.example.smsblocking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;

/**
 * À´Ô´£º
 * 
 * http://www.cnblogs.com/tiantianbyconan/archive/2012/03/01/2375408.html
 * 
 * @author Administrator
 * 
 */
public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	private EditText et;
	private Button saveBt;
	private boolean misChecked = true;
	private Switch mSwitch;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et = (EditText) findViewById(R.id.sendToId);
		mSwitch = (Switch) findViewById(R.id.switch1);
		mSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					misChecked = true;
				} else {
					misChecked = false;
				}
			}
		});
		saveBt = (Button) findViewById(R.id.saveBtId);
		saveBt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction("com.tiantian.SEND_TO");
				intent.putExtra("_sendTo", et.getText().toString());
				intent.putExtra("_isChecked", misChecked);
				sendBroadcast(intent);
			}
		});

	}
}
