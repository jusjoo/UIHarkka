package com.example.uiharkka;

public class ConfigControl {

	
	private ConfigView view;

	public ConfigControl() {
		this.view = new ConfigView(this);
		
	}
	
	public ConfigView getView(){
		return view;
	}
}
