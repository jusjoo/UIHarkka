package com.example.uiharkka;

import java.util.List;

public class KurssikohtainenControl {

	private final AllData data;
	private final List<Kurssi> kurssit;
	private final KurssikohtainenView view;

	public KurssikohtainenControl(AllData data) {
		this.data = data;
		kurssit = data.getKurssit();
		view = new KurssikohtainenView(this);

	}

	public List<Kurssi> getKurssit() {
		return this.kurssit;
	}

	public KurssikohtainenView getView() {
		return view;
	}
}
