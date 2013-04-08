package com.example.uiharkka;

import java.util.List;

public class AllData {

	private List<Opiskelija> opiskelijat;
	private List<Kurssi> kurssit;
	private List<Kandi> kandit;

	public void lisaaOpiskelija(Opiskelija op) {
		opiskelijat.add(op);
	}

	public void lisaaKurssi(Kurssi kurs) {
		kurssit.add(kurs);
	}

	public void printOpiskelijat() {
		for (int i = 0; i < opiskelijat.size(); i++) {
			System.out.println(opiskelijat.get(i));
		}
	}

	public void printKurssit() {
		for (int i = 0; i < kurssit.size(); i++) {
			System.out.println(kurssit.get(i));
		}
	}

	public void printKandit() {
		for (Kandi k : kandit) {
			System.out.println(k);
		}
	}

	public void asetaOpiskelijat(List<Opiskelija> list) {
		this.opiskelijat = list;
	}

	public void asetaKurssit(List<Kurssi> list) {
		this.kurssit = list;
	}

	public void asetaKandit(List<Kandi> list) {
		this.kandit = list;
	}

	public List<Opiskelija> getOpiskelijat() {
		return this.opiskelijat;
	}

	public List<Kandi> getKandit() {
		return this.kandit;
	}

	public List<Kurssi> getKurssit() {
		return this.kurssit;
	}
}
