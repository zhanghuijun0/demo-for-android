package com.app.code;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ImageView vc_image;
	Button vc_shuaixi, vc_ok;
	String getCode = null;
	EditText vc_code;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		vc_image = (ImageView) findViewById(R.id.vc_image);
		vc_image.setImageBitmap(Code.getInstance().getBitmap());
		vc_code = (EditText) findViewById(R.id.vc_code);

		getCode = Code.getInstance().getCode(); // 获取显示的验证码
		Log.e("info", getCode + "----");
		vc_shuaixi = (Button) findViewById(R.id.vc_shuaixi);
		vc_shuaixi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				vc_image.setImageBitmap(Code.getInstance().getBitmap());
				getCode = Code.getInstance().getCode();
			}
		});

		vc_ok = (Button) findViewById(R.id.vc_ok);
		vc_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String v_code = vc_code.getText().toString().trim();
				if (v_code == null || v_code.equals("")) {
					Toast.makeText(MainActivity.this, "没有填写验证码", 2).show();
				} else if (!v_code.equals(getCode)) {
					Toast.makeText(MainActivity.this, "验证码填写不正确", 2).show();
				} else {
					Toast.makeText(MainActivity.this, "操作成功", 2).show();
				}
			}
		});

	}

}
