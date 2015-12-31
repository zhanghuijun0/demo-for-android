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
	// �������ContentProvider(�������ݿ�)
	private ContentResolver mContentResolver;
	// ����װ��ѯ���������ļ�����
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
	 * ��������б�����
	 * 
	 * @return
	 */
	private ArrayList<Music> getMusicList() {
		ArrayList<Music> arrayList = new ArrayList<Music>();
		mContentResolver = this.getContentResolver();

		// �������� ������������ʱ�䡢����ID������·����������С
		String[] s1 = new String[] { MediaStore.Audio.Media.DISPLAY_NAME,
				MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.DURATION,
				MediaStore.Audio.Media._ID, MediaStore.Audio.Media.DATA,
				MediaStore.Audio.Media.SIZE };

		// ��ѯ����������Ϣ
		mCursor = mContentResolver.query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, s1, null, null,
				null);
		if (mCursor != null) {
			// �ƶ�����һ��
			mCursor.moveToFirst();
			// ��ø����ĸ�������
			for (int i = 0; i < mCursor.getCount(); i++) {
				// ����mp3�ļ�
				if (mCursor.getString(0).endsWith(".mp3")) {
					mMusic = new Music();
					mMusic.setMusicName(mCursor.getString(0));
					mMusic.setMusicSinger(mCursor.getString(1));
					mMusic.setMusicTime(mCursor.getInt(2));
					mMusic.set_id(mCursor.getInt(3));
					mMusic.setMusicPath(mCursor.getString(4));
					mMusic.setMusicSize(mCursor.getInt(5));
					Log.e("===", mMusic.get_id() + "");
					arrayList.add(mMusic);// װ�ص��б���
					arrayList.add(mMusic);// װ�ص��б���
					arrayList.add(mMusic);// װ�ص��б���
					arrayList.add(mMusic);// װ�ص��б���
				}
				mCursor.moveToNext();// �ƶ�����һ��
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
