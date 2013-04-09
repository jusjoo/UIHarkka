package com.example.uiharkka;

import java.util.List;

public class KurssikohtainenControl {

	private final AllData data;
	private final List<Kurssi> kurssit;
	private final KurssikohtainenView view;
	private String kurssiID;

	public KurssikohtainenControl(AllData data) {
		this.data = data;
		kurssit = data.getKurssit();
		view = new KurssikohtainenView(this);
		kurssiID = "";

	}

	public List<Kurssi> getKurssit() {
		return this.kurssit;
	}

	public KurssikohtainenView getView() {
		return view;
	}

	public void setKurssiID(String ID) {
		this.kurssiID = ID;
	}
}
