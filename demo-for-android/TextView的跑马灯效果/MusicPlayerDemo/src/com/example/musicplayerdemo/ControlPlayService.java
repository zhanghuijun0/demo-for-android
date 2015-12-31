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
	// ý�岥����
	public static MediaPlayer myMediaPlayer;
	public static int playing_id = 0;
	// ������Ϣ
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
	 * ��ʼ����ǰ�������ֵ�״̬
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
			// ����ָ������
			playing_id = intent.getExtras().getInt("position");
			playMusic(playing_id);
		}
		return super.onStartCommand(intent, flags, startId);
	}

	/**
	 * ����path·���µ�����
	 * 
	 * @param path
	 */
	public void initMediaSource(String path) {
		updateUI();
		Uri uri = Uri.parse(path);// ��������uri��ַ
		Log.e(tag, "path:" + path + ",uri:" + uri);
		if (myMediaPlayer != null) {
			myMediaPlayer.stop();
			myMediaPlayer.reset();
			myMediaPlayer = null;
		}
		myMediaPlayer = MediaPlayer.create(this, uri);// ΪmyMediaPlayer��������
		// ���������Ƿ����
		myMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				// �����굱ǰ�������Զ�������һ��
				initMusicStatus(++playing_id);
				playMusic(++playing_id);
			}
		});
	}

	private void playMusic() {
		// TODO Auto-generated method stub
		if (myMediaPlayer != null && mArrayListAize != 0) {
			if (myMediaPlayer.isPlaying()) {
				myMediaPlayer.pause();// ��ͣ
				// �������Ű�ť����
				MainActivity.mPlayImageView
						.setBackgroundResource(R.drawable.play_button);
			} else {
				myMediaPlayer.start();
				// ��������
				MainActivity.mPlayImageView
						.setBackgroundResource(R.drawable.pause_button);
				// �����̸߳���SeekBar
				startSeekBarUpdate();
			}
		} else {
			Toast.makeText(getApplicationContext(), "�ף�û�����ֻ����ҵ�����...",
					Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * �������һ������һ����
	 * 
	 * @param position
	 */
	private void playMusic(int position) {
		String str = "hello world";
		if (mArrayListAize != 0) {
			if (position == -1) {
				Toast.makeText(getApplicationContext(), "�����ǵ�һ��Ŷ��",
						Toast.LENGTH_SHORT).show();
				playing_id++;
			} else if (position == mArrayListAize) {
				Toast.makeText(getApplicationContext(), "�Ѿ������һ������",
						Toast.LENGTH_SHORT).show();
				playing_id--;
			} else {
				initMusicStatus(playing_id);
				initMediaSource(mMusic.getMusicPath());
				playMusic();
			}
		} else {
			Toast.makeText(getApplicationContext(), "û���ҵ�������",
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
			// ��ȡSeekBar�߶����ǵ�ʱ��
			MainActivity.play_time = myMediaPlayer.getCurrentPosition();
			// ������䵱ǰ��ȡ�Ľ���
			MainActivity.mPlaySeekBar.setProgress(MainActivity.play_time);
			// SeekBar�����ֵ������ʱ��
			MainActivity.mPlaySeekBar.setMax(MainActivity.arrayList.get(
					playing_id).getMusicTime());
			// �߳��ӳ�1000��������
			handler.postDelayed(updatesb, 1000);
		}
	};

	/**
	 * �л�������ʱ�򣬸�������UI
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
		// ��ʾ��ȡ�ĸ���ʱ��
		MainActivity.mTotalTextView.setText(MusicAdapter.toTime(mMusic
				.getMusicTime()));
		MainActivity.mTitleTextView.setText(mMusic.getMusicName());
	}
}
