package com.zhj.listviewdemo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

public final class AsyncHttpResponseHandler extends
		com.loopj.android.http.JsonHttpResponseHandler {
	private JsonHttpResponseHandler mAsyncHttpResponseHandler;
	private Context mContext;
	private boolean mIsSilent;

	public AsyncHttpResponseHandler(Context context,
			JsonHttpResponseHandler asyncHttpResponseHandler) {
		this(context, asyncHttpResponseHandler, false);
	}

	public AsyncHttpResponseHandler(Context context,
			JsonHttpResponseHandler asyncHttpResponseHandler, boolean silent) {
		mContext = context;
		mAsyncHttpResponseHandler = asyncHttpResponseHandler;
		mIsSilent = silent;
	}

	@Override
	public void onFailure(Throwable e, JSONObject rlt) {
		super.onFailure(e, rlt);
		if (Constant.DEBUG) {
			System.out.println("------onFailure------http result: "
					+ e.toString());
		}

		if (!mIsSilent) {
			if (mContext != null) {
				Toast.makeText(mContext,
						R.string.common_toast_connectionfailed,
						Toast.LENGTH_SHORT).show();
				if (mAsyncHttpResponseHandler == null) {
					mAsyncHttpResponseHandler.onFailure(e, rlt);
				}
			}
		}
	}

	@Override
	public void onFailure(Throwable e, JSONArray errorResponse) {
		super.onFailure(e, errorResponse);
		if (Constant.DEBUG) {
			System.out.println("------onFailure------http result: "
					+ e.toString());
		}

		if (!mIsSilent) {
			if (mContext != null) {
				Toast.makeText(mContext,
						R.string.common_toast_connectionfailed,
						Toast.LENGTH_SHORT).show();
				if (mAsyncHttpResponseHandler == null) {
					mAsyncHttpResponseHandler.onFailure(e, errorResponse);
				}
			}
		}
	}

	@Override
	public void onSuccess(JSONObject arg0) {
		if (Constant.DEBUG) {
			System.out.println("------onSuccess------http result: "
					+ arg0.toString());
		}
		try {
			switch (arg0.getInt(Constant.JSON_KEY_CODE)) {
			case Constant.CODE_API_NEED_UPDATE:
			case Constant.CODE_API_VERSION_ERROR:
			case Constant.CODE_DATA_FAIL:
			case Constant.CODE_DB_ERROR:
			case Constant.CODE_ERROR:
			case Constant.CODE_MISSING_API_VERSION:
			case Constant.CODE_SERVICE_UNAVAILABLE:
			case Constant.CODE_MISSING_METHOD:
			case Constant.CODE_SERVICE_ERROR:
			case Constant.CODE_TIMEOUT:
			case Constant.CODE_USER_PERMISSIONS:
				if (!mIsSilent) {
					if (mContext != null) {
						Toast.makeText(mContext, R.string.common_toast_error,
								Toast.LENGTH_SHORT).show();
					}
				}
				break;
			case Constant.CODE_SUCCESS:
			case Constant.CODE_FAILURE:
			default:
				if (mContext != null) {
					if (mAsyncHttpResponseHandler == null) {
						super.onSuccess(arg0);
					} else {
						mAsyncHttpResponseHandler.onSuccess(arg0);
					}
				}
				break;
			}

		} catch (JSONException e) {
			if (Constant.DEBUG) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onFinish() {
		if (mContext != null) {
			if (mAsyncHttpResponseHandler == null) {
				super.onFinish();
			} else {
				mAsyncHttpResponseHandler.onFinish();
			}
		}
	}

	@Override
	public void onStart() {
		if (mContext != null) {
			if (mAsyncHttpResponseHandler == null) {
				super.onStart();
			} else {
				mAsyncHttpResponseHandler.onStart();
			}
		}
	}
}
