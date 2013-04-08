package com.example.uiharkka;

import java.util.List;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class OpiskelijaView extends VerticalLayout {
	private static final long serialVersionUID = -4186213876588733687L;
	private OpiskelijaControl control;
	
	public OpiskelijaView(OpiskelijaControl control) {
		
		this.control = control;		
		showPersonalInfo();
		luoKandinValinta(control.annaKandit());
		
	}

	private void luoKandinValinta(List<Kandi> kandit) {
		NativeSelect selector = new NativeSelect("Valitse tarkasteltava kandirakenne");
		for(Kandi k : kandit) {
			selector.addItem(k.annaNimi());
			
		}
		this.addComponent(selector);
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
