package com.example.musicplayerdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener,
		OnItemLongClickListener {
	// �������ContentProvider(�������ݿ�)
	private ContentResolver mContentResolver;
	// ����װ��ѯ���������ļ�����
	private MusicAdapter mMusicAdapter;
	public static ArrayList<Music> arrayList;

	public static TextView mTitleTextView;
	public static TextView mTotalTextView;
	public static TextView mPlayedTextView;
	public static SeekBar mPlaySeekBar;
	public static ImageButton mPreImageView;
	public static ImageButton mNextImageView;
	public static ImageButton mPlayImageView;

	private Context mContext;
	// Ϊ����ʱ��Ͳ���ʱ�䶨�徲̬����
	public static int song_time = 0;
	public static int play_time = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		getView();
		mContext = getApplicationContext();
		ListView mListView = (ListView) findViewById(R.id.listView1);
		mListView.setOnItemClickListener(this);
		mListView.setOnItemLongClickListener(this);
		mMusicAdapter = new MusicAdapter(this, getMusicList());
		mListView.setAdapter(mMusicAdapter);
		/**
		 * �����϶�SeekBar�¼�
		 */
		mPlaySeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				// �ж��û��Ƿ���SeekBar���Ҳ�Ϊ�ղ�ִ��
				if (fromUser && ControlPlayService.myMediaPlayer != null) {
					ControlPlayService.myMediaPlayer.seekTo(progress);
				}
				mPlayedTextView.setText(MusicAdapter.toTime(progress));
			}
		});

	}

	private void getView() {
		// TODO Auto-generated method stub
		mTitleTextView = (TextView) findViewById(R.id.title_tv);
		mTotalTextView = (TextView) findViewById(R.id.time_total);
		mPlayedTextView = (TextView) findViewById(R.id.time_play);
		mPlaySeekBar = (SeekBar) findViewById(R.id.player_seekbar);
		mPreImageView = (ImageButton) findViewById(R.id.preview_btn);
		mNextImageView = (ImageButton) findViewById(R.id.next_btn);
		mPlayImageView = (ImageButton) findViewById(R.id.play_btn);
		mPreImageView.setOnClickListener(playAction);
		mNextImageView.setOnClickListener(playAction);
		mPlayImageView.setOnClickListener(playAction);
	}

	/**
	 * ��������б�����
	 * 
	 * @return
	 */
	private ArrayList<Music> getMusicList() {
		arrayList = new ArrayList<Music>();
		mContentResolver = this.getContentResolver();

		// �������� ������������ʱ�䡢����ID������·����������С
		String[] s1 = new String[] { MediaStore.Audio.Media.DISPLAY_NAME,
				MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.DURATION,
				MediaStore.Audio.Media._ID, MediaStore.Audio.Media.DATA,
				MediaStore.Audio.Media.SIZE };

		// ��ѯ����������Ϣ
		Cursor mCursor = mContentResolver.query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, s1, null, null,
				null);
		if (mCursor != null) {
			// �ƶ�����һ��
			mCursor.moveToFirst();
			// ��ø����ĸ�������
			for (int i = 0; i < mCursor.getCount(); i++) {
				// ����mp3�ļ�
				if (mCursor.getString(0).endsWith(".mp3")) {
					Music mMusic = new Music();
					mMusic.setMusicName(mCursor.getString(0));
					mMusic.setMusicSinger(mCursor.getString(1));
					mMusic.setMusicTime(mCursor.getInt(2));
					mMusic.set_id(mCursor.getInt(3));
					mMusic.setMusicPath(mCursor.getString(4));
					mMusic.setMusicSize(mCursor.getInt(5));
					arrayList.add(mMusic);// װ�ص��б���
				}
				mCursor.moveToNext();// �ƶ�����һ��
			}
			mCursor.close();
		}
		return arrayList;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		ArrayList<Music> musicArrayList = mMusicAdapter.getDataSource();
		// �򿪲������ַ���
		Intent play_1 = new Intent(mContext, ControlPlayService.class);
		// �����Ʋ������ݸ����ֲ��ŷ���
		play_1.putExtra("control", "listClick");
		play_1.putExtra("position", position);
		startService(play_1);
	}

	/**
	 * ���ſ��ư�ť
	 */
	private OnClickListener playAction = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.play_btn:
				// �����ֲ��ŷ���
				Intent play_center = new Intent(mContext,
						ControlPlayService.class);
				play_center.putExtra("control", "play");
				startService(play_center);
				break;
			case R.id.preview_btn:
				Intent play_left = new Intent(mContext,
						ControlPlayService.class);
				play_left.putExtra("control", "preview");
				startService(play_left);
				break;
			case R.id.next_btn:
				Intent play_right = new Intent(mContext,
						ControlPlayService.class);
				play_right.putExtra("control", "next");
				startService(play_right);
				break;
			default:
				break;
			}
		}
	};

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		// TODO Auto-generated method stub
		ArrayList<Music> musicArrayList = mMusicAdapter.getDataSource();
		Toast.makeText(mContext, musicArrayList.get(position).getMusicPath(),
				Toast.LENGTH_SHORT).show();
		return false;
	}

}
