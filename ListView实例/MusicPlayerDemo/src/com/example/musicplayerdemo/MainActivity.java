package com.example.musicplayerdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener{
	// 用来获得ContentProvider(共享数据库)
	private ContentResolver mContentResolver;
	// 用来装查询到得音乐文件数据
	private Cursor mCursor;
	private Music mMusic;
	private ListView mListView;
	private MusicAdapter mMusicAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		mListView = (ListView) findViewById(R.id.listView1);
		mListView.setOnItemClickListener(this);
		mMusicAdapter = new MusicAdapter(this, getMusicList());
		mListView.setAdapter(mMusicAdapter);
	}

	/**
	 * 获得音乐列表数组
	 * 
	 * @return
	 */
	private ArrayList<Music> getMusicList() {
		ArrayList<Music> arrayList = new ArrayList<Music>();
		mContentResolver = this.getContentResolver();

		// 歌曲名、 歌手名、歌曲时间、歌曲ID、歌曲路径、歌曲大小
		String[] s1 = new String[] { MediaStore.Audio.Media.DISPLAY_NAME,
				MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.DURATION,
				MediaStore.Audio.Media._ID, MediaStore.Audio.Media.DATA,
				MediaStore.Audio.Media.SIZE };

		// 查询所有音乐信息
		mCursor = mContentResolver.query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, s1, null, null,
				null);
		if (mCursor != null) {
			// 移动到第一个
			mCursor.moveToFirst();
			// 获得歌曲的各种属性
			for (int i = 0; i < mCursor.getCount(); i++) {
				// 过滤mp3文件
				if (mCursor.getString(0).endsWith(".mp3")) {
					mMusic = new Music();
					mMusic.setMusicName(mCursor.getString(0));
					mMusic.setMusicSinger(mCursor.getString(1));
					mMusic.setMusicTime(mCursor.getInt(2));
					mMusic.set_id(mCursor.getInt(3));
					mMusic.setMusicPath(mCursor.getString(4));
					mMusic.setMusicSize(mCursor.getInt(5));
					Log.e("===", mMusic.get_id() + "");
					arrayList.add(mMusic);// 装载到列表中
					arrayList.add(mMusic);// 装载到列表中
					arrayList.add(mMusic);// 装载到列表中
					arrayList.add(mMusic);// 装载到列表中
				}
				mCursor.moveToNext();// 移动到下一个
			}
		}
		return arrayList;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		ArrayList<Music> musicArrayList = mMusicAdapter.getDataSource(); 
		Toast.makeText(getApplicationContext(), musicArrayList.get(position).getMusicName(), Toast.LENGTH_SHORT).show();
	}

}
