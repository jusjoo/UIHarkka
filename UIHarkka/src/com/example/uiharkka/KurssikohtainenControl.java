package com.example.uiharkka;

import java.util.ArrayList;
import java.util.List;

public class KurssikohtainenControl {

	private final AllData data;
	private final List<Kurssi> kurssit;
	private final List<Opiskelija> opiskelijat;
	private final List<String> pvm;
	private final KurssikohtainenView view;
	private String kurssiID;
	private Kurssi yksittainenKurssi;

	public KurssikohtainenControl(AllData data) {
		this.data = data;
		kurssit = data.getKurssit();
		opiskelijat = data.getOpiskelijat();
		view = new KurssikohtainenView(this);
		kurssiID = "";
		pvm = new ArrayList<String>();

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

	public List<Opiskelija> getKurssikohtainenSuoritus() {
		List<Opiskelija> opiskelijaSuoritus = new ArrayList<Opiskelija>();
		List<Suoritus> opiskelijanKurssit = new ArrayList<Suoritus>();

		// Käy suoritukset opiskelijakohtaisesti läpi
		for (int i = 0; i < opiskelijat.size(); i++) {
			opiskelijanKurssit = opiskelijat.get(i).annaSuoritukset();
			for (int k = 0; k < opiskelijanKurssit.size(); k++) {
				if (opiskelijanKurssit.get(k).getKurssi().annaNimi()
						.equals(yksittainenKurssi.annaNimi())) {
					opiskelijaSuoritus.add(opiskelijat.get(i));
					// Lisää myös suorituspvm eri listaan
					this.pvm.add(opiskelijanKurssit.get(k).annaPvm());
				}
			}

		}
		if (opiskelijaSuoritus.isEmpty()) {
			return null;
		}
		return opiskelijaSuoritus;
	}

	public List<String> getPvm() {
		return pvm;
	}

	public void clearPvm() {
		this.pvm.clear();
	}

	// Mikä on tarkasteltava kurssi?
	public void setYksittainenKurssi() {
		yksittainenKurssi = null;
		for (int i = 0; i < kurssit.size(); i++) {
			if (kurssit.get(i).annaID().equals(kurssiID)) {
				this.yksittainenKurssi = kurssit.get(i);
				break;
			}
		}
	}

}
