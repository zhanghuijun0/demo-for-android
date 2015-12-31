package com.example.sqlitedemo;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button mSubmitButton;
	private EditText mTitleEditText;
	private EditText mContentEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DatabaseManager.initialize(this);
		mTitleEditText = (EditText)findViewById(R.id.title);
		mContentEditText = (EditText)findViewById(R.id.content);
		mSubmitButton = (Button)findViewById(R.id.submit);
		mSubmitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				insert(mTitleEditText.getText().toString(), mContentEditText.getText().toString());
				String sql = "select * from user;";
				Cursor cursor = DatabaseManager.select(sql, null);
				Toast.makeText(getApplicationContext(), "数据库当前有"+cursor.getCount()+"条数据！", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	private void insert(String title,String content){
		ContentValues values = new ContentValues();
		values.clear();
		values.put("title",title);
		values.put("content",content);
		DatabaseManager.insert("user", values);
		Toast.makeText(getApplicationContext(), title+","+content, Toast.LENGTH_SHORT).show();
	}

}
