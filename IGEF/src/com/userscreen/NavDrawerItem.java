package com.userscreen;

public class NavDrawerItem {
	private String title;
	private int icon;

	public NavDrawerItem(String string, int resourceId) {
		// TODO Auto-generated constructor stub
		this.title = string;
		this.icon = resourceId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		
		System.out.println(title);
		this.title = title;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

}
