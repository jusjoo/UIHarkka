package com.example.uiharkka;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataImport {

	private String kanditFile;
	private String opiskelijatFile;
	private String suorituksetFile;
	
	private HashMap<Integer, Opiskelija> opiskelijat;
	private HashMap<String, Kurssi> kurssit;
	private HashMap<String, Kandi> kandit;

	/*
	 * Import data from given filenames
	 */
	public DataImport(String kanditFile, String opiskelijatFile, String suorituksetFile) throws IOException {
		this.kanditFile = kanditFile;
		this.opiskelijatFile = opiskelijatFile;
		this.suorituksetFile = suorituksetFile;
		
		opiskelijat = new HashMap<Integer, Opiskelija>();
		kurssit = new HashMap<String, Kurssi>();
		kandit = new HashMap<String, Kandi>();
		
		tuoOpiskelijat();
		tuoSuoritukset();
		tuoKandit();
	}
	
	public List<Opiskelija> getOpiskelijat() {
		return new ArrayList<Opiskelija>(opiskelijat.values());
	}
	public List<Kurssi>	getKurssit() {
		return new ArrayList<Kurssi>(kurssit.values());
	}
	public List<Kandi> getKandit() {
		return new ArrayList<Kandi>(kandit.values());
	}
	

	private void tuoKandit() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(this.kanditFile));
		
		// ensimmäinen rivi kertoo, montako kandia tiedostossa on
		String line = reader.readLine();
		int amount = Integer.parseInt(line);
		
		// tallennetaan tähän väliaikaisesti parit, mille tutkinnolle mikäkin numero kuuluu
		HashMap<Integer, String> parit = new HashMap<Integer, String>();
		
		// haetaan kandien nimet
		for (int i = 0; i < amount; i++) {
			line = reader.readLine();
			kandit.put(line, new Kandi(line));
			parit.put(i+1, line);
		}
		
		// luetaan kurssit kandeihin
		while ((line = reader.readLine()) != null) {
			String[] parts = line.split(" ");
			
			Kurssi kurssi = kurssit.get(parts[1]);
			Kandi kandi = kandit.get(parit.get(Integer.parseInt(parts[0])));
			
			kandi.add(kurssi);	
		}
		
		
	}

	private void tuoOpiskelijat() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(this.opiskelijatFile));
		
		String line;
		while ((line = reader.readLine()) != null) {
			
			String[] parts = line.split(";");
			
			Opiskelija o = new Opiskelija(
					parts[2], 
					Integer.parseInt(parts[1]),
					parts[0],
					Integer.parseInt(parts[3]),
					parts[4],
					parts[5]);
			
			opiskelijat.put(Integer.parseInt(parts[1]), o);
		}
	}
	
	private void tuoSuoritukset() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(this.suorituksetFile));
		
		String line;
		while ((line = reader.readLine()) != null) {
			String [] parts = line.split(";");
			// tarkistetaan onko kurssi jo lisätty
			if (!kurssit.containsKey(parts[1])) {
				// luodaan jos ei ole
				parsiKurssi(parts);
			}
		
			Suoritus s = new Suoritus(parts[3], kurssit.get(parts[1]));
			opiskelijat.get(Integer.parseInt(parts[0])).lisaaSuoritus(s);
		}
	}
	
	/*
	 * Lisää uuden kurssin annetusta suoritusmerkintärivistä
	 */
	private void parsiKurssi(String[] parts) {
		Kurssi k = new Kurssi(parts[1], parts[2], Integer.parseInt(parts[4]));
		kurssit.put(parts[1], k);	
	}
	
}
