package com.example.uiharkka;

import java.util.ArrayList;
import java.util.List;

public class OpiskelijaJoukko {
	private final List<Opiskelija> opiskelijaJoukko;
	private final AllData alldata;
	private final OpiskelijaJoukkoUI view;
	private int alaraja;
	private int ylaraja;
	private String vuosi;

	public OpiskelijaJoukko(AllData alldata) {
		this.alldata = alldata;
		opiskelijaJoukko = alldata.getOpiskelijat();
		this.view = new OpiskelijaJoukkoUI(this);
	}

	// Palauttaa opiskelijan opiskelun aloittamisvuoden perusteella
	public List<Opiskelija> getOpiskelijaVuosi() {

		List<Opiskelija> opiskelijatVuosi = new ArrayList<Opiskelija>();
		for (int i = 0; i < opiskelijaJoukko.size(); i++) {
			if (opiskelijaJoukko.get(i).getAloitusvuosi().equals(vuosi)) {
				opiskelijatVuosi.add(opiskelijaJoukko.get(i));
			}
		}
		return opiskelijatVuosi;
	}

	// Palauttaa opiskelijan noppien perusteella
	public List<Opiskelija> getOpiskelijatOP() {

		List<Opiskelija> opiskelijatOP = new ArrayList<Opiskelija>();

		for (int i = 0; i < opiskelijaJoukko.size(); i++) {
			if (opiskelijaJoukko.get(i).getNopat() >= alaraja
					&& opiskelijaJoukko.get(i).getNopat() <= ylaraja) {
				opiskelijatOP.add(opiskelijaJoukko.get(i));
			}
		}
		return opiskelijatOP;

	}

	public List<Opiskelija> getKaikkiOpiskelijat() {

		return opiskelijaJoukko;
	}

	public void setAlaraja(int ala) {
		alaraja = ala;
	}

	public void setYlaraja(int yla) {
		ylaraja = yla;
	}

	public void setVuosi(String vuosi) {
		this.vuosi = vuosi;
	}

	public String getVuosi() {
		return vuosi;
	}

	public boolean noppaCheck() {
		if (alaraja >= 0 && ylaraja >= 0) {
			return true;
		} else {
			return false;
		}
	}

	public OpiskelijaJoukkoUI getView() {
		return view;
	}
}
