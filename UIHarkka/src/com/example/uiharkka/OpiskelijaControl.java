package com.example.uiharkka;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Notification;

public class OpiskelijaControl {

	private final AllData data;
	private final OpiskelijaView view;
	private Opiskelija current;
	private Kandi kandi;

	private List<Suoritus> kandiinSopimattomat;

	public OpiskelijaControl(Opiskelija o, AllData data) {
		this.data = data;
		this.current = o;

		// laitetaan joku oletukseksi
		this.kandi = this.data.getKandit().get(0);

		this.view = new OpiskelijaView(this);
	}

	public List<Kandi> annaKandit() {
		return data.getKandit();
	}

	public OpiskelijaView getView() {
		return view;
	}

	public String annaPisteet() {
		return current.annaPisteet() + "";
	}

	public String annaPaaAine() {
		return current.annaPaaAine();
	}

	public String annaKoulutusOhjelma() {
		return current.annaKoulutusOhjelma();
	}

	public String annaAloitusVuosi() {
		return current.annaAloitusVuosi();
	}

	public String getOpNum() {
		return current.getOpNum() + "";
	}

	public String annaNimi() {
		System.out.println(current.annaNimi());
		return current.annaNimi();
	}

	public void asetaNykyinenKandi(Kandi k) {

	}

	public List<Suoritus> annaKandiinSopivatSuoritukset() {
		List<Suoritus> result = new ArrayList<Suoritus>();
		kandiinSopimattomat = new ArrayList<Suoritus>();

		// käy läpi opiskelijan suoritukset
		for (Suoritus s : current.annaSuoritukset()) {
			// käy läpi kandiin kuuluvat kurssit
			boolean found = false;
			for (Kurssi k : kandi.annaKurssit()) {
				if (s.getKurssi().equals(k)) {
					// jos samat, lisätään tuloslistaan
					result.add(s);
					found = true;
					break;
				}
			}
			// lisätään epäsopivien listaan jos ei löytynyt
			if (!found) {
				kandiinSopimattomat.add(s);
			}
		}
		return result;
	}

	public List<Suoritus> annaKandiinSopimattomatSuoritukset() {
		if (kandiinSopimattomat != null) {
			return kandiinSopimattomat;
		}

		List<Suoritus> result = new ArrayList<Suoritus>();

		// käy läpi opiskelijan suoritukset
		for (Suoritus s : current.annaSuoritukset()) {
			// käy läpi kandiin kuuluvat kurssit
			boolean found = false;
			for (Kurssi k : kandi.annaKurssit()) {
				if (s.getKurssi().equals(k)) {
					// jos samat, skipataan eikä lisätä
					found = true;
					break;
				}
			}
			// lisätään epäsopivien listaan jos ei löytynyt samaa
			if (!found) {
				result.add(s);
			}
		}
		return result;

	}

	public void vaihdaKandi(String kandiNimi) {
		kandiinSopimattomat = null;
		for(Kandi k : data.getKandit()) {
			if (k.annaNimi().equals(kandiNimi)){
				this.kandi = k;
				break;
			}
		}
		view.paivitaTaulut();
		
	}

	public void vaihdaOpiskelija(String nimi) {
		kandiinSopimattomat = null;
		
		for(Opiskelija o : data.getOpiskelijat()){
			if (o.annaNimi().equals(nimi)) {
				this.current = o;
				break;
			}
		}
				
		view.paivitaKaikki();

	}

	public List<Opiskelija> annaOpiskelijat() {
		return data.getOpiskelijat();
	}

}
