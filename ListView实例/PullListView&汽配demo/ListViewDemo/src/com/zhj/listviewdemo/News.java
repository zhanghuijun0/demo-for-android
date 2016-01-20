package com.zhj.listviewdemo;

import java.io.Serializable;

/**
 * 资讯页面的抽象类
 * @author ZhangHuijun
 *
 */
public class News implements Serializable {
	private int mId;
	private String mTitle;
	private String mImageUrl;
	private int mType;
	private int mExpires;
	private int mUserId;
	private String mInfro;
	private String mContent;
	private int mCreateTime;
	private int mMediaDuration;
	private String mMediaUrl;
	private int mPrevId;
	private String mPrevTitle;
	private int mNextId;
	private String mNextTitle;
	private String mPageUrl;
	
	public int getId() {
		return mId;
	}
	public void setId(int Id) {
		this.mId = Id;
	}
	public String getTitle() {
		return mTitle;
	}
	public void setTitle(String Title) {
		this.mTitle = Title;
	}
	public String getImageUrl() {
		return mImageUrl;
	}
	public void setImageUrl(String ImageUrl) {
		this.mImageUrl = ImageUrl;
	}
	public int getType() {
		return mType;
	}
	public void setType(int Type) {
		this.mType = Type;
	}
	public int getExpires() {
		return mExpires;
	}
	public void setExpires(int Expires) {
		this.mExpires = Expires;
	}
	public int getUserId() {
		return mUserId;
	}
	public void setUserId(int UserId) {
		this.mUserId = UserId;
	}
	public String getInfro() {
		return mInfro;
	}
	public void setInfro(String Infro) {
		this.mInfro = Infro;
	}
	public String getContent() {
		return mContent;
	}
	public void setContent(String Content) {
		this.mContent = Content;
	}
	public int getCreateTime() {
		return mCreateTime;
	}
	public void setCreateTime(int CreateTime) {
		this.mCreateTime = CreateTime;
	}
	public int getMediaDuration() {
		return mMediaDuration;
	}
	public void setMediaDuration(int MediaDuration) {
		this.mMediaDuration = MediaDuration;
	}
	public String getMediaUrl() {
		return mMediaUrl;
	}
	public void setMediaUrl(String MediaUrl) {
		this.mMediaUrl = MediaUrl;
	}
	public int getPrevId() {
		return mPrevId;
	}
	public void setPrevId(int PrevId) {
		this.mPrevId = PrevId;
	}
	public String getPrevTitle() {
		return mPrevTitle;
	}
	public void setPrevTitle(String PrevTitle) {
		this.mPrevTitle = PrevTitle;
	}
	public int getNextId() {
		return mNextId;
	}
	public void setNextId(int NextId) {
		this.mNextId = NextId;
	}
	public String getNextTitle() {
		return mNextTitle;
	}
	public void setNextTitle(String NextTitle) {
		this.mNextTitle = NextTitle;
	}
	public String getPageUrl() {
		return mPageUrl;
	}
	public void setPageUrl(String PageUrl) {
		this.mPageUrl = PageUrl;
	}
	
	
}
