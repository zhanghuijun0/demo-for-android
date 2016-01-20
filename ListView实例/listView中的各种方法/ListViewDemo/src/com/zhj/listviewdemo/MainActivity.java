package com.zhj.listviewdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {
	private ListView mListView;
	private TestAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListView = (ListView) this.findViewById(R.id.listview);
		mAdapter = new TestAdapter(this, getData());
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "===-" + String.valueOf(position),
				Toast.LENGTH_SHORT).show();
	}

	/** =======================测试方法===============================*/
	private ArrayList<DataSource> getData() {
		ArrayList<DataSource> arrayList = new ArrayList<DataSource>();
		for (int i = 0; i < 10; i++) {
			DataSource dataSource = new DataSource();
			dataSource.setText("test" + i);
			arrayList.add(dataSource);
		}
		return arrayList;
	}

	private ArrayList<DataSource> getAddData() {
		ArrayList<DataSource> arrayList = new ArrayList<DataSource>();
		for (int i = 0; i < 10; i++) {
			DataSource dataSource = new DataSource();
			dataSource.setText("add" + i);
			arrayList.add(dataSource);
		}
		return arrayList;
	}

	/**
	 * add Data
	 * 
	 * @param v
	 */
	public void addData(View v) {
		mAdapter.appendDataSource(getAddData());
		Toast.makeText(this, "你目前有" + mAdapter.getCount() + "条数据！",
				Toast.LENGTH_SHORT).show();
	}

	/**
	 * remove Data
	 * 
	 * @param v
	 */
	public void removeData(View v) {
		mAdapter.removeDataSource();
		Toast.makeText(this, "你目前有" + mAdapter.getCount() + "条数据！",
				Toast.LENGTH_SHORT).show();
	}
}
