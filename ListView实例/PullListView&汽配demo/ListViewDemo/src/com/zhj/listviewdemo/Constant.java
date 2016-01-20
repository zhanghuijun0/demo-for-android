package com.zhj.listviewdemo;

public final class Constant {
	// TODO change values when release
	public static final boolean DEBUG = true;

	public static final String SHARE_APP_ID = "wx39c73dcdca6dfb27";
	public static final String JSON_KEY_CODE = "code";
	public static final String JSON_KEY_MSG = "msg";
	public static final String JSON_KEY_INFO = "info";
	public static final String JSON_KEY_LIST = "list";
	public static final String JSON_KEY_TIMESTAMP = "timestamp";
	public static final String JSON_KEY_DEVICE_ID = "did";
	public static final String JSON_KEY_ACCESS_FORMAT = "format";
	public static final String JSON_KEY_AREA = "area";
	public static final String JSON_KEY_V = "version";
	public static final String JSON_KEY_ID = "id";
	public static final String JSON_KEY_TYPE = "type";
	public static final String JSON_KEY_PAGE = "page";
	public static final String JSON_MESSAGE = "msg";

	/**
	 * 0x000 Success 请求成功 0x001 Time out 请求/执行超时 0x002 Data fail 数据异常(参数错误)
	 * 0x003 DB error 数据库执行失败 0x004 Service error 服务器导常 0x005 User permissions
	 * 用户权限不够 0x006 Service unavailable 服务不可用 0x007 Missing Method 方法不可用 0x008
	 * Missing api version 版本丢失 0x009 API verion error API版本异常 0x010 API need
	 * update API需要升级 0x011 Error 服务异常调用 0x012 failure 与Success对立的一种逻辑状态
	 */
	public static final int CODE_SUCCESS = 0x000;
	public static final int CODE_TIMEOUT = 0x001;
	public static final int CODE_DATA_FAIL = 0x002;
	public static final int CODE_DB_ERROR = 0x003;
	public static final int CODE_SERVICE_ERROR = 0x004;
	public static final int CODE_USER_PERMISSIONS = 0x005;
	public static final int CODE_SERVICE_UNAVAILABLE = 0x006;
	public static final int CODE_MISSING_METHOD = 0x007;
	public static final int CODE_MISSING_API_VERSION = 0x008;
	public static final int CODE_API_VERSION_ERROR = 0x009;
	public static final int CODE_API_NEED_UPDATE = 0x010;
	public static final int CODE_ERROR = 0x011;
	public static final int CODE_FAILURE = 0x012;

	// api url
	public static final String API_URL_PRE = DEBUG ? "http://192.168.1.74/QiPei_Api/index.php/Api/"
			: "http://chinaqszxapi.sinaapp.com/index.php/Api/";
	public static final String API_GET_NEWS_BANNER = API_URL_PRE
			+ "News/get_banner";
	public static final String API_NEWS_LIST_GET = API_URL_PRE
			+ "News/get_news_list";
	public static final String API_NEWS_LIST_ITEM_GET = API_URL_PRE
			+ "News/get_news_item";
	public static final String API_GET_HOME_BANNER = API_URL_PRE
			+ "Home/get_home_banner";
	public static final String API_ADD_PRODUCT = API_URL_PRE
			+ "Product/add_product";
	public static final String API_EDIT_PRODUCT = API_URL_PRE
			+ "Product/edit_product";
	public static final String API_UPLOAD_PHOTO = API_URL_PRE
			+ "Home/post_picture";
	public static final int PAGE_SIZE = 20;
}
