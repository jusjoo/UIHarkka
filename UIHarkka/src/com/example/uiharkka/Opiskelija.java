package com.example.uiharkka;

import java.util.List;

public class Opiskelija {

	private int opNum;
	private int nopat;
	private String aloitusVuosi;
	private String nimi;
	private String koulutusOhj;
	private String paaAine;
	private List<Suoritus> suoritetutKurssit;

	public Opiskelija(String nimi, int opNum, int aloitusVuosi, int nopat,
			String koulutusOhj, String paaAine) {

	}

	public void lisaaSuoritus(Suoritus suoritus) {
		suoritetutKurssit.add(suoritus);
	}

	public int getOpNum() {
		return opNum;
	}

}
