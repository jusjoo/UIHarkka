package com.example.uiharkka;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import com.google.debugging.sourcemap.dev.protobuf.DescriptorProtos.FieldDescriptorProto.Type;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Notification;

public class ConfigControl {

	public enum UploadType {KANDIT, OPISKELIJAT, SUORITUKSET}
	
	private ConfigView view;
	private UiharkkaUI mainUI;
	
	private HashMap<UploadType, String> lahetetyt;
	private HashMap<UploadType, String> uudetTiedostot;
	

	public ConfigControl(UiharkkaUI ui) {
		uudetTiedostot = new HashMap<UploadType, String>();
		lahetetyt = new HashMap<UploadType, String>();
		this.view = new ConfigView(this);
		this.mainUI = ui;
	}
	
	public ConfigView getView(){
		return view;
	}

	public void asetaSisalto(String sisalto, UploadType type) {
		lahetetyt.put(type, sisalto);
	}
	
	public void paivitaUudetSisallot() {
		if (tallennaUudetTiedostot()) {
			try {
				DataImport tuotu = new DataImport(uudetTiedostot.get(UploadType.KANDIT), 
													uudetTiedostot.get(UploadType.OPISKELIJAT),
													uudetTiedostot.get(UploadType.SUORITUKSET));
				mainUI.changeData(tuotu);
			} catch (IOException e) {
				Notification.show("Virhe uusien tiedostojen tuonnissa.");
				e.printStackTrace();
			}				
		}
	}

	private boolean tallennaUudetTiedostot() {
		String basepath = VaadinService.getCurrent().getBaseDirectory()
				.getAbsolutePath();
		
		for (UploadType type : lahetetyt.keySet()) {
			BufferedWriter writer;
			String filename = basepath + "/imported/" + type + ".txt";
			File filu = new File(filename);
			try {
				System.out.println("Kijroitetaan tiedosto: " + filename);
				writer = new BufferedWriter(new FileWriter(filu));
				writer.write(lahetetyt.get(type));
				writer.close();
			} catch (IOException e) {
				Notification.show("Virhe kirjoittaessa tiedostoa: " + filu);
				e.printStackTrace();
				return false;
			}
			uudetTiedostot.put(type, filename);	
		}
		
		return true;
		
	}
}
