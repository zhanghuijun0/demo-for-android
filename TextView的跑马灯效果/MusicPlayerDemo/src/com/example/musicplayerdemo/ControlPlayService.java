package com.example.musicplayerdemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class ControlPlayService extends Service {
	private String tag = "ControlPlayService";
	// 媒体播放类
	public static MediaPlayer myMediaPlayer;
	public static int playing_id = 0;
	// 歌曲信息
	Handler handler = new Handler();
	public boolean playFlag = true;

	private int mArrayListAize = 0;
	private Music mMusic;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mArrayListAize = MusicAdapter.mMusicList.size();
		if (MusicAdapter.mMusicList != null && mArrayListAize != 0) {
			initMusicStatus(0);
			initMediaSource(mMusic.getMusicPath());
		}
		Log.e(tag, "-----[onCreate]mArrayListAize:" + mArrayListAize);
	}

	/**
	 * 初始化当前播放音乐的状态
	 * 
	 * @param id
	 * @return
	 */
	private Music initMusicStatus(int id) {
		if (mMusic == null) {
			mMusic = new Music();
		}
		mMusic.set_id(id);
		mMusic.setMusicName(MusicAdapter.mMusicList.get(id).getMusicName());
		mMusic.setMusicTime(MusicAdapter.mMusicList.get(id).getMusicTime());
		mMusic.setMusicPath(MusicAdapter.mMusicList.get(id).getMusicPath());
		mMusic.setMusicSinger(MusicAdapter.mMusicList.get(id).getMusicSinger());
		mMusic.setMusicSize(MusicAdapter.mMusicList.get(id).getMusicSize());
		return mMusic;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		String playFlag = intent.getExtras().getString("control");
		Log.e(tag, "=========onStartCommand,playFlag:" + playFlag);
		if (playFlag.endsWith("play")) {
			playMusic();
		} else if (playFlag.equals("preview")) {
			playMusic(--playing_id);
		} else if (playFlag.equals("next")) {
			playMusic(++playing_id);
		} else if (playFlag.equals("listClick")) {
			// 播放指定音乐
			playing_id = intent.getExtras().getInt("position");
			playMusic(playing_id);
		}
		return super.onStartCommand(intent, flags, startId);
	}

	/**
	 * 播放path路径下的音乐
	 * 
	 * @param path
	 */
	public void initMediaSource(String path) {
		updateUI();
		Uri uri = Uri.parse(path);// 解析歌曲uri地址
		Log.e(tag, "path:" + path + ",uri:" + uri);
		if (myMediaPlayer != null) {
			myMediaPlayer.stop();
			myMediaPlayer.reset();
			myMediaPlayer = null;
		}
		myMediaPlayer = MediaPlayer.create(this, uri);// 为myMediaPlayer创建对象
		// 监听播放是否完成
		myMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				// 播放完当前歌曲，自动播放下一首
				initMusicStatus(++playing_id);
				playMusic(++playing_id);
			}
		});
	}

	private void playMusic() {
		// TODO Auto-generated method stub
		if (myMediaPlayer != null && mArrayListAize != 0) {
			if (myMediaPlayer.isPlaying()) {
				myMediaPlayer.pause();// 暂停
				// 更换播放按钮背景
				MainActivity.mPlayImageView
						.setBackgroundResource(R.drawable.play_button);
			} else {
				myMediaPlayer.start();
				// 更换背景
				MainActivity.mPlayImageView
						.setBackgroundResource(R.drawable.pause_button);
				// 启动线程更新SeekBar
				startSeekBarUpdate();
			}
		} else {
			Toast.makeText(getApplicationContext(), "亲，没有在手机里找到歌曲...",
					Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 点击“上一曲、下一曲”
	 * 
	 * @param position
	 */
	private void playMusic(int position) {
		String str = "hello world";
		if (mArrayListAize != 0) {
			if (position == -1) {
				Toast.makeText(getApplicationContext(), "现在是第一首哦！",
						Toast.LENGTH_SHORT).show();
				playing_id++;
			} else if (position == mArrayListAize) {
				Toast.makeText(getApplicationContext(), "已经是最后一首啦！",
						Toast.LENGTH_SHORT).show();
				playing_id--;
			} else {
				initMusicStatus(playing_id);
				initMediaSource(mMusic.getMusicPath());
				playMusic();
			}
		} else {
			Toast.makeText(getApplicationContext(), "没有找到歌曲！",
					Toast.LENGTH_SHORT).show();
		}

	}

	private void startSeekBarUpdate() {
		// TODO Auto-generated method stub
		handler.post(start);
	}

	private Runnable start = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			handler.post(updatesb);
		}

	};
	Runnable updatesb = new Runnable() {
		@Override
		public void run() {
			// 获取SeekBar走动到那的时间
			MainActivity.play_time = myMediaPlayer.getCurrentPosition();
			// 设置填充当前获取的进度
			MainActivity.mPlaySeekBar.setProgress(MainActivity.play_time);
			// SeekBar的最大值填充歌曲时间
			MainActivity.mPlaySeekBar.setMax(MainActivity.arrayList.get(
					playing_id).getMusicTime());
			// 线程延迟1000毫秒启动
			handler.postDelayed(updatesb, 1000);
		}
	};

	/**
	 * 切换歌曲的时候，更换界面UI
	 */
	private void updateUI() {
		MainActivity.mPreImageView.setEnabled(true);
		MainActivity.mNextImageView.setEnabled(true);
		if (playing_id == 0) {
			MainActivity.mPreImageView.setEnabled(false);
		} 
		if (playing_id == mArrayListAize - 1) {
			MainActivity.mNextImageView.setEnabled(false);
		}
		// 显示获取的歌曲时间
		MainActivity.mTotalTextView.setText(MusicAdapter.toTime(mMusic
				.getMusicTime()));
		MainActivity.mTitleTextView.setText(mMusic.getMusicName());
	}
}
