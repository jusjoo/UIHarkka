package com.example.uiharkka;

import java.util.List;

import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field.ValueChangeEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class OpiskelijaView extends VerticalLayout {
	private static final long serialVersionUID = -4186213876588733687L;
	private final OpiskelijaControl control;
	
	private HorizontalLayout top = new HorizontalLayout();
	private HorizontalLayout bottom = new HorizontalLayout();
	
	private Panel personalInfo;
	private Table sopivatTaulukko;
	private Table sopimattomatTaulukko;
	private ListSelect selector;
	


	public OpiskelijaView(final OpiskelijaControl control) {
		this.control = control;
		this.setSpacing(true);
		
		this.addComponent(top);
		this.addComponent(bottom);
		top.setSpacing(true);
		bottom.setSpacing(true);
		
		showPersonalInfo();
		luoKandinValinta(control.annaKandit());
		
		alustaTaulut();
		luoListaSopivista(control.annaKandiinSopivatSuoritukset());
		luoListaSopimattomista(control.annaKandiinSopimattomatSuoritukset());
		
		Button b = new Button("testi");
		this.addComponent(b);
		b.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				control.vaihdaOpiskelija();
				
			}
		});
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
		bottom.addComponent(sopimattomatTaulukko);
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
		bottom.addComponent(sopivatTaulukko);
	}

	private void luoKandinValinta(List<Kandi> kandit) {
		selector = new ListSelect(
				"Kandirakenne");
		selector.setRows(kandit.size());
		selector.setImmediate(true);
		selector.setNullSelectionAllowed(false);
		
		for (Kandi k : kandit) {	
			selector.addItem(k.annaNimi());
		}
		
		selector.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(
					com.vaadin.data.Property.ValueChangeEvent event) {
				control.vaihdaKandi(selector.getValue().toString());
			}
		});
		
		top.addComponent(selector);

	}
	
	public void paivitaTaulut() {
		sopivatTaulukko.removeAllItems();
		sopimattomatTaulukko.removeAllItems();

		luoListaSopivista(control.annaKandiinSopivatSuoritukset());
		luoListaSopimattomista(control.annaKandiinSopimattomatSuoritukset());
	}

	private void showPersonalInfo() {

		FormLayout layout = new FormLayout();
		personalInfo = new Panel(control.annaNimi(), layout);
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

		top.addComponent(personalInfo);
	}
	
	private void poistaKaikki() {
		top.removeComponent(this.personalInfo);
		top.removeComponent(this.selector);
		bottom.removeComponent(this.sopimattomatTaulukko);
		bottom.removeComponent(this.sopivatTaulukko);
	}

	public void paivitaKaikki() {
		poistaKaikki();
		
		showPersonalInfo();
		luoKandinValinta(control.annaKandit());
		alustaTaulut();
		paivitaTaulut();
		
	}

}
