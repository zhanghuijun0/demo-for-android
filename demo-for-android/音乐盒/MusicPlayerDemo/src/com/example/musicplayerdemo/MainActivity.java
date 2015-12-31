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
	// 用来获得ContentProvider(共享数据库)
	private ContentResolver mContentResolver;
	// 用来装查询到得音乐文件数据
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
	// 为歌曲时间和播放时间定义静态变量
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
		 * 监听拖动SeekBar事件
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
				// 判断用户是否触拖SeekBar并且不为空才执行
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
	 * 获得音乐列表数组
	 * 
	 * @return
	 */
	private ArrayList<Music> getMusicList() {
		arrayList = new ArrayList<Music>();
		mContentResolver = this.getContentResolver();

		// 歌曲名、 歌手名、歌曲时间、歌曲ID、歌曲路径、歌曲大小
		String[] s1 = new String[] { MediaStore.Audio.Media.DISPLAY_NAME,
				MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.DURATION,
				MediaStore.Audio.Media._ID, MediaStore.Audio.Media.DATA,
				MediaStore.Audio.Media.SIZE };

		// 查询所有音乐信息
		Cursor mCursor = mContentResolver.query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, s1, null, null,
				null);
		if (mCursor != null) {
			// 移动到第一个
			mCursor.moveToFirst();
			// 获得歌曲的各种属性
			for (int i = 0; i < mCursor.getCount(); i++) {
				// 过滤mp3文件
				if (mCursor.getString(0).endsWith(".mp3")) {
					Music mMusic = new Music();
					mMusic.setMusicName(mCursor.getString(0));
					mMusic.setMusicSinger(mCursor.getString(1));
					mMusic.setMusicTime(mCursor.getInt(2));
					mMusic.set_id(mCursor.getInt(3));
					mMusic.setMusicPath(mCursor.getString(4));
					mMusic.setMusicSize(mCursor.getInt(5));
					arrayList.add(mMusic);// 装载到列表中
				}
				mCursor.moveToNext();// 移动到下一个
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
		// 打开播放音乐服务
		Intent play_1 = new Intent(mContext, ControlPlayService.class);
		// 将控制参数传递给音乐播放服务
		play_1.putExtra("control", "listClick");
		play_1.putExtra("position", position);
		startService(play_1);
	}

	/**
	 * 播放控制按钮
	 */
	private OnClickListener playAction = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.play_btn:
				// 打开音乐播放服务
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
