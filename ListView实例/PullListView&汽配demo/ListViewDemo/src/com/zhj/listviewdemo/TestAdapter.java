package com.zhj.listviewdemo;

import java.util.ArrayList;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * 
 * @author Zhanghj
 *
 */
public class TestAdapter extends BaseAdapter {
	private ArrayList<DataSource> mArrayList;
	private LayoutInflater mInflater;

	public TestAdapter(Context context, ArrayList<DataSource> dataList) {
		// TODO Auto-generated constructor stub
		this.mArrayList = dataList;
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public void setDataSources(ArrayList<DataSource> arrayList) {
		mArrayList = arrayList;
		notifyDataSetChanged();
	}

	public ArrayList<DataSource> getDataSources() {
		return mArrayList;
	}

	public void removeDataSource() {
		mArrayList = new ArrayList<DataSource>();
		notifyDataSetChanged();
	}

	public void appendDataSource(ArrayList<DataSource> arrayList) {
		mArrayList.addAll(arrayList);
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_data, null);
			viewHolder.mTextTv = (TextView) convertView
					.findViewById(R.id.textView1);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		DataSource dataSource = mArrayList.get(position);
		viewHolder.mTextTv
				.setText(TextUtils.isEmpty(dataSource.getText()) ? " "
						: dataSource.getText());
		return convertView;
	}

	final private class ViewHolder {
		TextView mTextTv;
	}

}
