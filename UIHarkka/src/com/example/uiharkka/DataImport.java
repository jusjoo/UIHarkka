package com.example.uiharkka;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataImport {

	private String kanditFile;
	private String opiskelijatFile;
	private String suorituksetFile;
	
	private List<Opiskelija> opiskelijat;

	/*
	 * Import data from given 
	 */
	public DataImport(String kanditFile, String opiskelijatFile, String suorituksetFile) throws IOException {
		this.kanditFile = kanditFile;
		this.opiskelijatFile = opiskelijatFile;
		this.suorituksetFile = suorituksetFile;
		
		opiskelijat = new ArrayList<Opiskelija>();
		
		importStudents();
	}

	private void importStudents() throws IOException {
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
			
			opiskelijat.add(o);
		}
	}
	
	
}
