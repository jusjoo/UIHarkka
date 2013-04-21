package com.example.uiharkka;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import com.example.uiharkka.ConfigControl.UploadType;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.StartedEvent;

public class ConfigView extends FormLayout{

	private static final long serialVersionUID = 1L;
	private ConfigControl control;
	
	private final Button paivita;

	public ConfigView(ConfigControl ctrl) {
		this.control = ctrl;
		
		initUploader("kandit-tiedosto", UploadType.KANDIT);
		initUploader("opiskelijat-tiedosto", UploadType.OPISKELIJAT);
		initUploader("suoritukset-tiedosto", UploadType.SUORITUKSET);
			
		this.addComponent(new Label(""));
		paivita = new Button("Ota käyttöön uudet tiedot");
		paivita.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				control.paivitaUudetSisallot();
			}
		});
		paivita.setEnabled(false);
		this.addComponent(paivita);
	}
	
	
	private void initUploader(String caption, UploadType type) {
		Vastaanottaja laskija = new Vastaanottaja(type);
		
		Upload uploader = new Upload(caption+": ", laskija);
		uploader.setButtonCaption("valitse");
		uploader.setImmediate(true);
		ProgressLabel progress = new ProgressLabel(uploader, laskija);
		
		uploader.addStartedListener(progress);
		uploader.addProgressListener(progress);
		uploader.addFinishedListener(progress);
		
		HorizontalLayout rivi = new HorizontalLayout();
		rivi.setSpacing(true);
		this.addComponent(rivi);
		
		rivi.addComponent(uploader);
		rivi.addComponent(progress);
		
	}
	
	private class Vastaanottaja implements Receiver {

		private static final long serialVersionUID = 1L;
		private ArrayList<Byte> tulos;
		private UploadType type;
		
		public Vastaanottaja(UploadType type) {
			this.type = type;
		}
		
		@Override
		public OutputStream receiveUpload(String filename, String mimeType) {
			tulos = new ArrayList<Byte>();
			
			return new OutputStream(){
				public void write(int b) throws IOException {
					tulos.add((byte) b);
				};
			};
		}
		
		public String getReceived() {
			byte[] array = new byte[tulos.size()];
			for (int i=0; i < tulos.size(); i++) {
				array[i] = tulos.get(i);
			}
			
			return new String(array);
		}
		public UploadType getType() {
			return type;
		}
	}
	
	private class ProgressLabel extends Label implements Upload.StartedListener, 
													Upload.ProgressListener, 
													Upload.FailedListener,
													Upload.FinishedListener{
		private static final long serialVersionUID = 1L;
		private Vastaanottaja vastaanottaja;
		
		public ProgressLabel(Upload uploader, Vastaanottaja vastaanottaja) {
			this.vastaanottaja = vastaanottaja;
			this.setValue("");
		}

		@Override
		public void uploadFinished(FinishedEvent event) {
			this.setValue("Lähetys valmis");
			control.asetaSisalto(vastaanottaja.getReceived(), vastaanottaja.getType());
		}

		@Override
		public void uploadFailed(FailedEvent event) {
			this.setValue("Virhe lähetyksessä, yritä uudelleen.");
		}

		@Override
		public void updateProgress(long readBytes, long contentLength) {
			this.setValue("Lähetetty " + readBytes/(float)contentLength * 100 + " %");
		}

		@Override
		public void uploadStarted(StartedEvent event) {
			this.setValue("lähetys alkaa...");
		}
	}
	
	public void asetaValmiiksi() {
		paivita.setEnabled(true);
	}
	
}
