package com.example.uiharkka;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class OpiskelijaJoukkoUI extends UI {

	private TextField ala;
	private TextField yla;
	private TextField aVuosi;
	private final OpiskelijaJoukko opJoukko;
	private final Table tulosJoukkoVuosi = new Table("Tulokset");
	private final Table tulosJoukkoOp = new Table("Tulokset");

	public OpiskelijaJoukkoUI(OpiskelijaJoukko opJoukko) {
		this.opJoukko = opJoukko;
	}

	@Override
	protected void init(VaadinRequest request) {

		VerticalLayout layout = new VerticalLayout();

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

		ala.addTextChangeListener(new TextChangeListener() {
			@Override
			public void textChange(final TextChangeEvent event) {
			}
		});

		ala.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(final ValueChangeEvent event) {
				final String valueString = String.valueOf(event.getProperty()
						.getValue());
				opJoukko.setAlaraja(Integer.parseInt(valueString));

			}
		});

		yla.addTextChangeListener(new TextChangeListener() {
			@Override
			public void textChange(final TextChangeEvent event) {
			}
		});

		yla.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(final ValueChangeEvent event) {
				final String valueString = String.valueOf(event.getProperty()
						.getValue());
				opJoukko.setYlaraja(Integer.parseInt(valueString));

			}
		});

		aVuosi.addTextChangeListener(new TextChangeListener() {
			@Override
			public void textChange(final TextChangeEvent event) {
			}
		});

		aVuosi.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(final ValueChangeEvent event) {
				final String valueString = String.valueOf(event.getProperty()
						.getValue());
				opJoukko.setVuosi(valueString);

			}
		});

		tulosJoukkoVuosi.setSelectable(true);
		tulosJoukkoVuosi.setImmediate(true);
		tulosJoukkoVuosi.addContainerProperty("OPISKELIJA", String.class, null);
		tulosJoukkoVuosi.addContainerProperty("ALOITUSVUOSI", String.class,
				null);
		addTuloksetVuosi();

		tulosJoukkoOp.setSelectable(true);
		tulosJoukkoOp.setImmediate(true);
		tulosJoukkoOp.addContainerProperty("OPISKELIJA", String.class, null);
		tulosJoukkoOp.addContainerProperty("OPINTOPISTEET", String.class, null);
		addTuloksetOp();

		layout.addComponent(ala);
		layout.addComponent(yla);
		layout.addComponent(aVuosi);
		layout.addComponent(tulosJoukkoVuosi);

	}

	// Lisää tiettynä vuotena aloittaneet opiskelijat tauluun
	public void addTuloksetVuosi() {
		List<Opiskelija> opiskelija = new ArrayList<Opiskelija>();
		opiskelija = opJoukko.getOpiskelijaVuosi();
		for (int i = 1; opiskelija.get(i) != null; i++) {

			tulosJoukkoVuosi.addItem(new Object[] {
					opiskelija.get(i).getNimi(),
					opiskelija.get(i).getAloitusvuosi() }, new Integer(i));
		}

	}

	public void addTuloksetOp() {
		List<Opiskelija> opiskelija = new ArrayList<Opiskelija>();
		opiskelija = opJoukko.getOpiskelijatOP();

		// Onko opintopisteet asetettu textfieldiin?
		if (opJoukko.noppaCheck()) {
			for (int i = 1; opiskelija.get(i) != null; i++) {
				tulosJoukkoOp.addItem(new Object[] {
						opiskelija.get(i).getNimi(),
						opiskelija.get(i).getNopat() }, new Integer(i));
			}
		}
		// Jos opintopisteitä ei asetettu, aseta kaikki opiskelijat tauluun
		else {
			for (int i = 1; opiskelija.get(i) != null; i++) {
				tulosJoukkoOp.addItem(new Object[] {
						opiskelija.get(i).getNimi(),
						opiskelija.get(i).getNopat() }, new Integer(i));
			}
		}

	}
}
