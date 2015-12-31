package com.example.preferencedemo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private EditText mTitleEditText;
	private Button mSubmitButton;
	private Button mShowButton;

	private TextView mTimeTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Preferences.init(getApplicationContext());
		mSubmitButton = (Button) findViewById(R.id.submit);
		mShowButton = (Button) findViewById(R.id.show);
		mTitleEditText = (EditText) findViewById(R.id.title);
		mTimeTextView = (TextView) findViewById(R.id.time);

		mSubmitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Preferences.setTitle(mTitleEditText.getText().toString());
			}
		});
		mShowButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mTitleEditText.setText(Preferences.getTitle());
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		mTimeTextView.setText(Preferences.getExitTime());
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"yyyy-MM-dd  HH:mm:ss");

		String str = dateFormatter.format(Calendar.getInstance().getTime());
		Preferences.setExitTime(str);
	}
}
