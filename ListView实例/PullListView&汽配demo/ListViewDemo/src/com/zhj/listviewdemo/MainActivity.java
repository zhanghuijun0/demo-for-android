package com.zhj.listviewdemo;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class MainActivity extends Activity implements OnItemClickListener,
		PullToRefreshBase.OnRefreshListener2<ListView> {
	private PullToRefreshListView mPullToRefreshListView;
	private AsyncHttpClient mHttpClient = new AsyncHttpClient();
	private NewsCompanyAdapter mNewsCompanyAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mPullToRefreshListView = (PullToRefreshListView) this
				.findViewById(R.id.prlv_news_comapny);
		mNewsCompanyAdapter = new NewsCompanyAdapter(this,
				new ArrayList<News>());
		mPullToRefreshListView.setAdapter(mNewsCompanyAdapter);
		getQipeiData();
		mPullToRefreshListView.setOnItemClickListener(this);
		mPullToRefreshListView.setOnRefreshListener(this);// 刷新监听
		mPullToRefreshListView.setMode(Mode.BOTH);// 设置刷新模式（上拉、下拉）
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "===-" + String.valueOf(position),
				Toast.LENGTH_SHORT).show();
	}

	private void getQipeiData() {
		RequestParams params = new RequestParams();
		params.put("key", " ");
		params.put("type", String.valueOf(2));
		params.put("area", "新乡");
		params.put("page_index",
				String.valueOf(mNewsCompanyAdapter.getPageNumber()));
		params.put("page_size",
				String.valueOf(mNewsCompanyAdapter.getPageSize()));
		mHttpClient.post(MainActivity.this, Constant.API_NEWS_LIST_GET, params,
				new AsyncHttpResponseHandler(MainActivity.this,
						new JsonHttpResponseHandler() {
							public void onSuccess(JSONObject res) {
								// TODO Auto-generated method stub
								JSONObject jsonInfo = null;
								ArrayList<News> newsList;
								try {
									if (res.getInt(Constant.JSON_KEY_CODE) == Constant.CODE_SUCCESS) {
										jsonInfo = res
												.getJSONObject(Constant.JSON_KEY_INFO);
										newsList = NewsJSONConvert
												.convertJsonArrayToItemList(jsonInfo
														.getJSONArray(Constant.JSON_KEY_LIST));

										if (mNewsCompanyAdapter.getPageNumber() == 1) {// 如果为缓存数据，获得真实数据时清空列表
											mNewsCompanyAdapter
													.setDataSource(newsList);
										} else {
											if (newsList.size() == 0) {
												Toast.makeText(
														getApplicationContext(),
														getString(R.string.common_label_last_page),
														Toast.LENGTH_SHORT)
														.show();
											}
											mNewsCompanyAdapter
													.appendDataSource(newsList);
										}
										mNewsCompanyAdapter.incPageNumber();

									} else {
										Toast.makeText(
												getApplicationContext(),
												res.getString(Constant.JSON_MESSAGE),
												Toast.LENGTH_SHORT).show();
									}
								} catch (JSONException e) {
									e.printStackTrace();
								}
							}

							@Override
							public void onFinish() {
								mPullToRefreshListView.onRefreshComplete();
								super.onFinish();
							}
						}));
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		mNewsCompanyAdapter.setPageNumber(1);
		getQipeiData();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		getQipeiData();
	}

}
