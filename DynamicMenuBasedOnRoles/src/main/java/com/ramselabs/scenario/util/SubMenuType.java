package com.ramselabs.scenario.util;

public enum SubMenuType {
	CREATE,DELETE,UPDATE,READ,SEARCH,CONTENT,PROFILE;
	
	public String getName(){
		return this.name();
	}
}
