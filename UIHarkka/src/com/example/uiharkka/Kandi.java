package com.example.uiharkka;

import java.util.ArrayList;
import java.util.List;

public class Kandi {

	private String nimi;
	private List<Kurssi> sisältö;
	
	public Kandi(String nimi) {
		sisältö = new ArrayList<Kurssi>();
		this.nimi = nimi;
	}
	
	public void add(Kurssi k) {
		sisältö.add(k);
	}
}
