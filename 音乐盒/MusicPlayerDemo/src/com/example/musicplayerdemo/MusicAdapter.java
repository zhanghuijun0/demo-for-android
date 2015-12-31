package com.example.musicplayerdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MusicAdapter extends BaseAdapter {
	public static ArrayList<Music> mMusicList;
	private Activity mContext;

	public MusicAdapter(Activity context, ArrayList<Music> musicList) {
		mMusicList = musicList;
		mContext = context;
	}

	public ArrayList<Music> getDataSource() {
		return mMusicList;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mMusicList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mMusicList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mContext.getLayoutInflater().inflate(
					R.layout.list_item, null);
			viewHolder.mMusicNameTv = (TextView) convertView
					.findViewById(R.id.music_name);
			viewHolder.mMusicSizeTv = (TextView) convertView
					.findViewById(R.id.music_size);
			viewHolder.mMusicTimeTv = (TextView) convertView
					.findViewById(R.id.music_time);
			viewHolder.mMusicIdTv = (TextView) convertView
					.findViewById(R.id.music_id);
			viewHolder.mSingerTv = (TextView) convertView
					.findViewById(R.id.singer);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		Music music = mMusicList.get(position);
		viewHolder.mMusicNameTv.setText(music.getMusicName());
		viewHolder.mMusicTimeTv.setText(toTime(music.getMusicTime()));
		viewHolder.mMusicSizeTv.setText(toMB(music.getMusicSize()));
		viewHolder.mMusicIdTv.setText(String.valueOf(position + 1));
		viewHolder.mSingerTv.setText(music.getMusicSinger());
		return convertView;
	}

	final private class ViewHolder {
		TextView mMusicIdTv;
		TextView mMusicNameTv;
		TextView mSingerTv;
		TextView mMusicTimeTv;
		TextView mMusicSizeTv;
	}

	/**
	 * 时间转化处理
	 */
	public static String toTime(int time) {
		time /= 1000;
		int minute = time / 60;
		int second = time % 60;
		minute %= 60;
		// 格式化时间
		return String.format(" %02d:%02d ", minute, second);
	}

	/**
	 * 文件大小转换，将B转换为MB
	 */
	public String toMB(int size) {
		float a = (float) size / (float) (1024 * 1024);
		String b = Float.toString(a);
		int c = b.indexOf(".");
		String fileSize = "";
		fileSize += b.substring(0, c + 2);
		return fileSize;
	}

}
