package com.zhj.listviewdemo;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class NewsJSONConvert {
	private static final String COL_ID = "id";
	private static final String COL_USER_ID = "user_id";
	private static final String COL_TITLE = "title";
	private static final String COL_INTRO = "intro";
	private static final String COL_CONTENT = "content";
	private static final String COL_CREATE_TIME = "create_time"; 
	private static final String COL_IMAGE_URL = "image_url";
	private static final String COL_TYPE = "type";
	private static final String COL_MEDIA_DURATION = "media_duration" ;
	private static final String COL_MEDIA_URL = "media_url" ;
	private static final String COL_EXPIRES = "expires" ;
	private static final String COL_PAGE_URL = "page_url";
	
	public static ArrayList<News> convertJsonArrayToItemList(JSONArray jsonArray)
			throws JSONException {
		int length = jsonArray.length();
		System.out.println("====~~~~~~~~~~~~length:"+length);
		ArrayList<News> list = new ArrayList<News>();
		for (int i = 0; i < length; i++) {
			list.add(convertJsonToItem(jsonArray.getJSONObject(i)));
		}
		return list;
	}

	public static News convertJsonToItem(JSONObject json) throws JSONException {
		News news = new News();
		news.setContent(json.optString(COL_CONTENT));
		news.setCreateTime(Integer.valueOf(json.getString(COL_CREATE_TIME)));
		news.setExpires(json.optInt(COL_EXPIRES));
		news.setId(Integer.valueOf(json.getString(COL_ID)));
		news.setImageUrl(json.getString(COL_IMAGE_URL));
		news.setInfro(json.getString(COL_INTRO));
		news.setMediaDuration(json.optInt(COL_MEDIA_DURATION));
		news.setMediaUrl(json.optString(COL_MEDIA_URL));
//		news.setNextId();
//		news.setNextTitle();
//		news.setPrevId();
//		news.setPrevTitle();
		news.setTitle(json.getString(COL_TITLE));
		news.setType(Integer.valueOf(json.getString(COL_TYPE)));
		news.setUserId(json.optInt(COL_USER_ID));
		news.setPageUrl(json.optString(COL_PAGE_URL));
		
		return news;
	}
}
