package com.example.uiharkka;

import java.util.ArrayList;
import java.util.List;

public class KandiTutkintoControl {

	private final KandiTutkintoView view;
	private List<Opiskelija> opis;
	private final AllData data;
	private Kandi kandi;

	public KandiTutkintoControl(AllData data) {
		this.data = data;
		opis = new ArrayList<Opiskelija>();
		opis = data.getOpiskelijat();
		this.kandi = data.getKandit().get(0);
		this.view = new KandiTutkintoView(this);
	}

	public List<Opiskelija> getOpiskelijat() {
		return opis;
	}

	public KandiTutkintoView getView() {
		return view;
	}

	public int annaKandiinSopivatSuoritukset(Opiskelija o) {
		int suoritustenLkm = 0;
		// käy läpi opiskelijan suoritukset
		for (Suoritus s : o.annaSuoritukset()) {
			// käy läpi kandiin kuuluvat kurssit
			for (Kurssi k : kandi.annaKurssit()) {
				if (s.getKurssi().equals(k)) {
					// jos samat, lisätään tuloslistaan
					suoritustenLkm++;
					break;
				}
			}
		}
		return suoritustenLkm;
	}

	public void setKandi(Kandi kandi) {
		this.kandi = kandi;
	}

	public List<Kandi> annaKandit() {
		return data.getKandit();
	}

	public void vaihdaKandi(String kandiNimi) {
		for (Kandi k : data.getKandit()) {
			if (k.annaNimi().equals(kandiNimi)) {
				this.kandi = k;
				break;
			}
		}
	}
}
