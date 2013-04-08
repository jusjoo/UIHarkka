package com.example.uiharkka;

import java.util.List;

public class OpiskelijaControl {

	private AllData data;
	private OpiskelijaView view;
	private Opiskelija current; 
	
	public OpiskelijaControl(Opiskelija o, AllData data) {
		this.data = data;
		this.current = o;
		
		this.view = new OpiskelijaView(this);
	}


	public List<Kandi> annaKandit() {
		// TODO Auto-generated method stub
		return data.getKandit();
	}

	public OpiskelijaView getView() {
		return view;
	}

	public String annaPisteet() {
		return current.annaPisteet()+"";
	}

	public String annaPaaAine() {
		return current.annaPaaAine();
	}

	public String annaKoulutusOhjelma() {
		return current.annaKoulutusOhjelma();
	}

	public String annaAloitusVuosi() {
		return current.annaAloitusVuosi();
	}

	public String getOpNum() {
		return current.getOpNum()+"";
	}

	public String annaNimi() {
		System.out.println(current.annaNimi());
		return current.annaNimi();
	}
	
	
}
