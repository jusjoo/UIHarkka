package com.example.uiharkka;

import java.util.ArrayList;
import java.util.List;

public class Kandi {

	private String nimi;
	private List<Kurssi> sis�lt�;
	
	public Kandi(String nimi) {
		sis�lt� = new ArrayList<Kurssi>();
		this.nimi = nimi;
	}
	
	public void add(Kurssi k) {
		sis�lt�.add(k);
	}
}
