package com.example.uiharkka;

public class Kurssi {

	private final int kurssiID;
	private final String kurssinNimi;
	private final int opintopisteet;

	public Kurssi(int kurssiID, String kurssinNimi, int opintopisteet) {
		this.kurssiID = kurssiID;
		this.kurssinNimi = kurssinNimi;
		this.opintopisteet = opintopisteet;
	}

	@Override
	public String toString() {
		return "Kurssin nimi: " + kurssinNimi + ", Kurssin ID: " + kurssiID
				+ ", Kurssin laajuus:" + opintopisteet + "op";
	}
}
