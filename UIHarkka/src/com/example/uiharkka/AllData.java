package com.example.uiharkka;

import java.util.List;

public class AllData {
	private List<Opiskelija> opiskelijat;
	private List<Kurssi> kurssit;

	// private List<Suoritus>

	public AllData() {

	}

	public void lisaaOpiskelija(Opiskelija op) {
		opiskelijat.add(op);
	}

	public void lisaaKurssi(Kurssi kurs) {
		kurssit.add(kurs);
	}

	public void getOpiskelijat() {
		for (int i = 0; i < opiskelijat.size(); i++) {
			opiskelijat.get(i);
		}
	}

	public void getKurssit() {
		for (int i = 0; i < kurssit.size(); i++) {
			kurssit.get(i);
		}

	}
}
