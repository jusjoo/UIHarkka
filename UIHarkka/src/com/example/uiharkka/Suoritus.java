package com.example.uiharkka;

public class Suoritus {

	private final String pvm;
	private final Kurssi kurssi;

	public Suoritus(String pvm, Kurssi kurssi) {
		this.pvm = pvm;
		this.kurssi = kurssi;
	}

	public Kurssi getKurssi() {
		return kurssi;
	}

	public Object annaPvm() {
		return pvm;
	}
}
