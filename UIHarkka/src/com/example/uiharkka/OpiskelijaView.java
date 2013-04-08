package com.example.uiharkka;

import java.util.List;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class OpiskelijaView extends VerticalLayout {
	private static final long serialVersionUID = -4186213876588733687L;
	private final OpiskelijaControl control;
	private Table sopivatTaulukko;
	private Table sopimattomatTaulukko;
	private NativeSelect selector;

	public OpiskelijaView(OpiskelijaControl control) {
		this.control = control;

		showPersonalInfo();
		luoKandinValinta(control.annaKandit());
		
		alustaTaulut();
		luoListaSopivista(control.annaKandiinSopivatSuoritukset());
		luoListaSopimattomista(control.annaKandiinSopimattomatSuoritukset());
	}

	private void alustaTaulut() {
		sopimattomatTaulukko = new Table("Suoritukset, jotka eivät sovi tähän kandiin");
		sopimattomatTaulukko.addContainerProperty("Kurssi ID", 		String.class, null);
		sopimattomatTaulukko.addContainerProperty("Kurssin Nimi",  	String.class, null);
		sopimattomatTaulukko.addContainerProperty("Suoritettu",     String.class, null);
		
		sopivatTaulukko = new Table("Kandiin sopivat suoritukset");
		sopivatTaulukko.addContainerProperty("Kurssi ID", 		String.class, null);
		sopivatTaulukko.addContainerProperty("Kurssin Nimi",  	String.class, null);
		sopivatTaulukko.addContainerProperty("Suoritettu",     String.class, null);	
	}

	private void luoListaSopimattomista(
			List<Suoritus> kandiinSopimattomat) {
		for (int i=0; i < kandiinSopimattomat.size(); i++) {
			Suoritus s = kandiinSopimattomat.get(i);
			sopimattomatTaulukko.addItem(new Object[]{
								s.getKurssi().annaID(), 
								s.getKurssi().annaNimi(), 
								s.annaPvm()
							}, new Integer(i));
		}
		this.addComponent(sopimattomatTaulukko);
	}

	private void luoListaSopivista(List<Suoritus> kandiinSopivat) {
		for (int i=0; i < kandiinSopivat.size(); i++) {
			Suoritus s = kandiinSopivat.get(i);
		
			sopivatTaulukko.addItem(new Object[]{
								s.getKurssi().annaID(), 
								s.getKurssi().annaNimi(), 
								s.annaPvm()
							}, new Integer(i));
		}
		this.addComponent(sopivatTaulukko);
	}

	private void luoKandinValinta(List<Kandi> kandit) {
		selector = new NativeSelect(
				"Valitse tarkasteltava kandirakenne");
		
		Button button = new Button("Valitse");
		for (Kandi k : kandit) {	
			selector.addItem(k.annaNimi());
			selector.setValue(k.annaNimi());
		}

		button.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				control.vaihdaKandi(selector.getValue().toString());
			}
		});
		
		this.addComponent(selector);
		this.addComponent(button);
	}
	
	public void paivitaListat() {
		sopivatTaulukko.removeAllItems();
		sopimattomatTaulukko.removeAllItems();

		luoListaSopivista(control.annaKandiinSopivatSuoritukset());
		luoListaSopimattomista(control.annaKandiinSopimattomatSuoritukset());
	}

	private void showPersonalInfo() {

		FormLayout layout = new FormLayout();
		Panel personalInfo = new Panel(control.annaNimi(), layout);
		personalInfo.setWidth("400px");

		TextField opnro = new TextField("Opiskelijanumero");
		opnro.setValue(control.getOpNum());

		TextField aloitusvuosi = new TextField("Aloitusvuosi");
		aloitusvuosi.setValue(control.annaAloitusVuosi());

		TextField koulutusohjelma = new TextField("Koulutusohjelma");
		koulutusohjelma.setValue(control.annaKoulutusOhjelma());

		TextField paaAine = new TextField("Pääaine");
		paaAine.setValue(control.annaPaaAine());

		TextField pisteet = new TextField("Opintopisteet");
		pisteet.setValue(control.annaPisteet());

		opnro.setReadOnly(true);
		aloitusvuosi.setReadOnly(true);
		koulutusohjelma.setReadOnly(true);
		paaAine.setReadOnly(true);
		pisteet.setReadOnly(true);

		layout.addComponent(opnro);
		layout.addComponent(aloitusvuosi);
		layout.addComponent(koulutusohjelma);
		layout.addComponent(paaAine);
		layout.addComponent(pisteet);

		this.addComponent(personalInfo);
	}

}
