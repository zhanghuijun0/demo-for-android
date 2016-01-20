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
 * 1�������� 2������ 3������ʱ�� 4��ר��(ר��ͼƬ��ר�����ƣ�ר��ID[������ȡͼƬ]) 5��������С
 */

public class Music {

	private int _id;
	private String musicName;
	private String musicSinger;
	private int musicTime;
	private String musicPath;
	private int musicSize;

	// ȡ��id
	public int get_id() {
		return _id;
	}

	// ����id
	public void set_id(int _id) {
		this._id = _id;
	}

	// ȡ�ø�����
	public String getMusicName() {
		return musicName;
	}

	// ���ø�����
	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	// ȡ�ø�����
	public String getMusicSinger() {
		return musicSinger;
	}

	// ���ø�����
	public void setMusicSinger(String musicSinger) {
		this.musicSinger = musicSinger;
	}

	// ȡ�����ֲ�����ʱ��
	public int getMusicTime() {
		return musicTime;
	}

	// �������ֲ�����ʱ��
	public void setMusicTime(int musicTime) {
		this.musicTime = musicTime;
	}

	// ȡ������·��
	public String getMusicPath() {
		return musicPath;
	}

	// ��������·��
	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}

	// ȡ�����ִ�С
	public int getMusicSize() {
		return musicSize;
	}

	// �������ִ�С
	public void setMusicSize(int musicSize) {
		this.musicSize = musicSize;
	}

}