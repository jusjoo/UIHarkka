package com.example.uiharkka;

public class Suoritus {

	private String pvm;
	private Kurssi kurssi;

	public Suoritus(String pvm, Kurssi kurssi) {
		this.pvm = pvm;
		this.kurssi = kurssi;
	}

	public Kurssi getKurssi() {
		return kurssi;
	}
}
