package com.example.fragmentdemo;


import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity implements
		OnCheckedChangeListener {
	private SearchFragment mSearchFragment;
	private ListFragment mListFragment;
	private NewsFragment mNewsFragment;
	private ProfileFragment mProfileFragment;
	private FragmentManager mFragmentManager;
	private ArrayList<OnTabChangedListener> mOnTabChangedListenerList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 初始化布局元素
		((RadioGroup) findViewById(R.id.tabbar))
				.setOnCheckedChangeListener(this);
		mFragmentManager = getSupportFragmentManager();
		// 第一次启动时选中第0个tab
		((RadioButton) findViewById(R.id.tab_search)).setChecked(true);

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		setTabSelection(checkedId);
	}
	
	
	/**
	 * 根据传入的resId参数来设置选中的tab页。
	 * 
	 * @param resId
	 *            每个tab页中对应的资源ID(layout id)
	 */
	private void setTabSelection(int resId) {
		// 开启一个Fragment事务
		FragmentTransaction transaction = mFragmentManager.beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(transaction);
		switch (resId) {
		case R.id.tab_search:
			// 当点击了消息tab时，改变控件的图片和文字颜色
			if (mSearchFragment == null) {
				// 如果SearchFragment为空，则创建一个并添加到界面上
				mSearchFragment = new SearchFragment();
				transaction.add(R.id.content, mSearchFragment);
			} else {
				// 如果SearchFragment不为空，则直接将它显示出来
				transaction.show(mSearchFragment);
			}

			break;
		case R.id.tab_list:
			// 当点击了联系人tab时，改变控件的图片和文字颜色
			if (mListFragment == null) {
				// 如果ListFragment为空，则创建一个并添加到界面上
				mListFragment = new ListFragment();
				transaction.add(R.id.content, mListFragment);
			} else {
				// 如果ListFragment不为空，则直接将它显示出来
				transaction.show(mListFragment);
			}
			break;
		case R.id.tab_news:
			// 当点击了动态tab时，改变控件的图片和文字颜色
			if (mNewsFragment == null) {
				// 如果NewsFragment为空，则创建一个并添加到界面上
				mNewsFragment = new NewsFragment();
				transaction.add(R.id.content, mNewsFragment);
			} else {
				// 如果NewsFragment不为空，则直接将它显示出来
				transaction.show(mNewsFragment);
			}
			break;
		case R.id.tab_profile:
		default:
			resId = R.id.tab_profile;
			// 当点击了设置tab时，改变控件的图片和文字颜色
			if (mProfileFragment == null) {
				// 如果ProfileFragment为空，则创建一个并添加到界面上
				mProfileFragment = new ProfileFragment();
				transaction.add(R.id.content, mProfileFragment);
			} else {
				// 如果ProfileFragment不为空，则直接将它显示出来
				transaction.show(mProfileFragment);
			}
			break;
		}
		transaction.commit();
		if (mOnTabChangedListenerList != null) {
			for (OnTabChangedListener listener : mOnTabChangedListenerList) {
				listener.onChanged(this, resId);
			}
		}
	}
	
	/**
	 * 将所有的Fragment都置为隐藏状态。
	 * 
	 * @param transaction
	 *            用于对Fragment执行操作的事务
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (mSearchFragment != null) {
			transaction.hide(mSearchFragment);
		}
		if (mListFragment != null) {
			transaction.hide(mListFragment);
		}
		if (mNewsFragment != null) {
			transaction.hide(mNewsFragment);
		}
		if (mProfileFragment != null) {
			transaction.hide(mProfileFragment);
		}
	}
	public static interface OnTabChangedListener {
		void onChanged(MainActivity mainActivity, int selectedTabId);
	}

}
