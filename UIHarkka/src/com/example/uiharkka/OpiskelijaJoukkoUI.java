package com.example.uiharkka;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class OpiskelijaJoukkoUI extends VerticalLayout {

	private final TextField ala = new TextField();
	private final TextField yla = new TextField();
	private final TextField aVuosi = new TextField();
	private final OpiskelijaJoukko opJoukko;
	private final Table tulosJoukkoVuosi = new Table("Tulokset");
	private final Table tulosJoukkoOp = new Table("Tulokset");

	public OpiskelijaJoukkoUI(OpiskelijaJoukko opJoukko) {
		this.opJoukko = opJoukko;
		opiskelijaView();

	}

	public void opiskelijaView() {

		HorizontalLayout horLayout = new HorizontalLayout();
		Panel panel = new Panel(horLayout);
		panel.setWidth("600px");

		// Opintopisteiden alarajan textfield
		ala.setImmediate(true);
		ala.setInputPrompt("Opintopisteiden alaraja");
		ala.setMaxLength(10);

		// Opintopisteiden ylarajan textfield
		yla.setImmediate(true);
		yla.setInputPrompt("Opintopisteiden ylaraja");
		yla.setMaxLength(10);

		// Aloitusvuoden textfield
		aVuosi.setImmediate(true);
		aVuosi.setInputPrompt("Aloitusvuosi");
		aVuosi.setMaxLength(10);

		// Tulostaulu vuoden mukaan
		tulosJoukkoVuosi.setSelectable(true);
		tulosJoukkoVuosi.setImmediate(true);
		tulosJoukkoVuosi.addContainerProperty("OPISKELIJA", String.class, null);
		tulosJoukkoVuosi.addContainerProperty("ALOITUSVUOSI", String.class,
				null);
		addTuloksetVuosi();

		// Tulostaulu opintopisteiden mukaan
		tulosJoukkoOp.setSelectable(true);
		tulosJoukkoOp.setImmediate(true);
		tulosJoukkoOp.addContainerProperty("OPISKELIJA", String.class, null);
		tulosJoukkoOp.addContainerProperty("OPINTOPISTEET", String.class, null);

		ala.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(final ValueChangeEvent event) {
				final String valueString = String.valueOf(event.getProperty()
						.getValue());
				opJoukko.setAlaraja(Integer.parseInt(valueString));
				addTuloksetOp();
			}
		});

		yla.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(final ValueChangeEvent event) {
				final String valueString = String.valueOf(event.getProperty()
						.getValue());
				opJoukko.setYlaraja(Integer.parseInt(valueString));
				addTuloksetOp();

			}
		});

		aVuosi.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(final ValueChangeEvent event) {
				final String valueString = String.valueOf(event.getProperty()
						.getValue());
				opJoukko.setVuosi(valueString);
				addTuloksetVuosi();

			}
		});

		VerticalLayout tekstit = new VerticalLayout();
		tekstit.addComponent(ala);
		tekstit.addComponent(yla);
		tekstit.addComponent(aVuosi);
		horLayout.addComponent(tekstit);

		horLayout.addComponent(tulosJoukkoVuosi);
		horLayout.addComponent(tulosJoukkoOp);

		this.addComponent(panel);

	}

	// Lisää tiettynä vuotena aloittaneet opiskelijat tauluun
	public void addTuloksetVuosi() {
		List<Opiskelija> opiskelija = new ArrayList<Opiskelija>();
		opiskelija = opJoukko.getOpiskelijaVuosi();

		tulosJoukkoVuosi.removeAllItems();

		for (int i = 0; i < opiskelija.size(); i++) {

			tulosJoukkoVuosi.addItem(new Object[] {
					opiskelija.get(i).getNimi(),
					opiskelija.get(i).getAloitusvuosi() }, new Integer(i + 1));
		}

	}

	public void addTuloksetOp() {
		List<Opiskelija> opiskelija = new ArrayList<Opiskelija>();
		opiskelija = opJoukko.getOpiskelijatOP();

		// Tyhjentää vanhan taulukon
		tulosJoukkoOp.removeAllItems();

		for (int i = 0; i < opiskelija.size(); i++) {
			tulosJoukkoOp.addItem(new Object[] { opiskelija.get(i).getNimi(),
					Integer.toString(opiskelija.get(i).getNopat()) },
					new Integer(i + 1));
		}
	}
}
