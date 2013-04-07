package com.example.uiharkka;

import java.util.ArrayList;
import java.util.List;

public class Kandi {

	private final String nimi;
	private final List<Kurssi> sisalto;

	public Kandi(String nimi) {
		sisalto = new ArrayList<Kurssi>();
		this.nimi = nimi;
	}

	public void add(Kurssi k) {
		sisalto.add(k);
	}
}
