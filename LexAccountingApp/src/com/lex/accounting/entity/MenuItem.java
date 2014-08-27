package com.lex.accounting.entity;

public class MenuItem {

    private String mTitle;
    private int mIconRes;

    public MenuItem(String title, int iconRes) {
        mTitle = title;
        mIconRes = iconRes;
    }

	public String getmTitle() {
		return mTitle;
	}

	public int getmIconRes() {
		return mIconRes;
	}
    
}
