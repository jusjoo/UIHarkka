package com.example.uiharkka;

import java.util.List;

public class OpiskelijaControl {

	private AllData data;
	
	public OpiskelijaControl(AllData data) {
		this.data = data;
	}

	public List<Kandi> annaKandit() {
		// TODO Auto-generated method stub
		return data.getKandit();
	}
	
	
}
