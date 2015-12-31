package com.example.musicplayerdemo;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 1、歌曲名 2、歌手 3、歌曲时间 4、专辑(专辑图片，专辑名称，专辑ID[用来获取图片]) 5、歌曲大小
 */

public class Music {

	private int _id;
	private String musicName;
	private String musicSinger;
	private int musicTime;
	private String musicPath;
	private int musicSize;

	// 取得id
	public int get_id() {
		return _id;
	}

	// 设置id
	public void set_id(int _id) {
		this._id = _id;
	}

	// 取得歌曲名
	public String getMusicName() {
		return musicName;
	}

	// 设置歌曲名
	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	// 取得歌手名
	public String getMusicSinger() {
		return musicSinger;
	}

	// 设置歌手名
	public void setMusicSinger(String musicSinger) {
		this.musicSinger = musicSinger;
	}

	// 取得音乐播放总时间
	public int getMusicTime() {
		return musicTime;
	}

	// 设置音乐播放总时间
	public void setMusicTime(int musicTime) {
		this.musicTime = musicTime;
	}

	// 取得音乐路径
	public String getMusicPath() {
		return musicPath;
	}

	// 设置音乐路径
	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}

	// 取得音乐大小
	public int getMusicSize() {
		return musicSize;
	}

	// 设置音乐大小
	public void setMusicSize(int musicSize) {
		this.musicSize = musicSize;
	}

}