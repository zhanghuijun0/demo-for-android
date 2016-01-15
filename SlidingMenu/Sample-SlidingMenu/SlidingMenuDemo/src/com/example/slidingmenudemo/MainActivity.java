package com.example.slidingmenudemo;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnCloseListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnClosedListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnOpenListener;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends Activity {
	private SlidingMenu menu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		// configure the SlidingMenu
		menu = new SlidingMenu(this);

		menu.setMode(SlidingMenu.LEFT);// 设置左滑菜单

		// menu.setMode(SlidingMenu.LEFT_RIGHT);// 属性，然后设置右侧菜单的布局文件
		// menu.setSecondaryMenu(R.layout.activity_main);
		// menu.setSecondaryShadowDrawable(R.drawable.shadow);// 右侧菜单的阴影图片

		/**
		 * 设置滑动的区域
		 */
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// 全屏都可以
//		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);// 旁边可以
//		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);// activity界面都不可以

		

		
		
		/**
		 * 阴影
		 */
		menu.setShadowWidth(200);// 设置阴影宽度
		menu.setShadowWidthRes(R.dimen.shadow_width);// 设置阴影图片的宽度
		menu.setShadowDrawable(R.drawable.shadow);// 设置阴影图片

		/**
		 * 淡入淡出
		 */
		menu.setFadeEnabled(true);// 是否淡入淡出
		menu.setFadeDegree(0.35f);// 设置淡入淡出的比例

		menu.setBehindScrollScale(0.2f);// 设置滑动时 的拖拽效果
		menu.setBehindWidth(400);// 设置SlidingMenu菜单的宽度
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);// SlidingMenu划出时主页面显示的剩余宽度

		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);// 使SlidingMenu附加在Activity上
		menu.setMenu(R.layout.slide_menu);// 设置menu的布局文件

		CanvasTransformer mTransformer = new CanvasTransformer() {
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
				float scale = (float) (percentOpen * 0.25 + 0.75);
				canvas.scale(scale, scale, canvas.getWidth() / 2,
						canvas.getHeight() / 2);
			}
		};
		menu.setBehindCanvasTransformer(mTransformer);// 左滑或右滑自定义动画

	}

	
	// 事件监听
	private void actionListener() {
		// TODO Auto-generated method stub
		// 监听slidingmenu打开
		menu.setOnOpenListener(new OnOpenListener() {

			@Override
			public void onOpen() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "onOpen",
						Toast.LENGTH_SHORT).show();
			}
		});
		// 监听slidingmenu关闭时事件
		menu.setOnCloseListener(new OnCloseListener() {

			@Override
			public void onClose() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "onClose",
						Toast.LENGTH_SHORT).show();
			}
		});
		// 监听slidingmenu关闭后事件
		menu.setOnClosedListener(new OnClosedListener() {

			@Override
			public void onClosed() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "onClosed",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}
