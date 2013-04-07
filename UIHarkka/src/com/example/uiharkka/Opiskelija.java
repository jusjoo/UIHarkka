package com.example.uiharkka;

import java.util.ArrayList;
import java.util.List;

public class Opiskelija {

	private final int opNum;
	private final int nopat;
	private final String aloitusvuosi;
	private final String nimi;
	private final String koulutusohj;
	private final String paaAine;
	private List<Suoritus> suoritetutKurssit;

	public Opiskelija(String nimi, int opNum, String aloitusvuosi, int nopat,
			String koulutusohj, String paaAine) {
		suoritetutKurssit = new ArrayList<Suoritus>();
		
		this.nimi = nimi;
		this.opNum = opNum;
		this.aloitusvuosi = aloitusvuosi;
		this.nopat = nopat;
		this.koulutusohj = koulutusohj;
		this.paaAine = paaAine;

	}

	public void lisaaSuoritus(Suoritus suoritus) {
		suoritetutKurssit.add(suoritus);
	}

	public int getOpNum() {
		return opNum;
	}

	@Override
	public String toString() {
		return "Nimi: " + nimi + ", Opiskelijanumero: " + opNum
				+ ", Opintopisteet: " + nopat + ", Aloitusvuosi:"
				+ aloitusvuosi + ", Koulutusohjelma; " + koulutusohj
				+ ", Pääaine: " + paaAine;
	}

}
