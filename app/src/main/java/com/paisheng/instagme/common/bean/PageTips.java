package com.paisheng.instagme.common.bean;

import java.io.Serializable;

/**
 * @author: liaoshengjian
 * @Filename:
 * @Description:		ReloadTipsView 提示信息
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/2/17 10:54
 */
public class PageTips implements Serializable {

	/**
	 * 提示
	 */
	private String tips;

	/**
	 * 图标
	 */
	private int iconResid;

	public PageTips() {

	}

	public PageTips(String tips, int iconResid) {
		super();
		this.tips = tips;
		this.iconResid = iconResid;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public int getIconResid() {
		return iconResid;
	}

	public void setIconResid(int iconResid) {
		this.iconResid = iconResid;
	}
}
