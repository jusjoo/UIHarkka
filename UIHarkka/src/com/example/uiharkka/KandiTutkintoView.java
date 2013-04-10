package com.example.uiharkka;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Table;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;

public class KandiTutkintoView extends VerticalLayout {

	private final KandiTutkintoControl ctrl;
	private TwinColSelect op;
	private List<Opiskelija> opiskelijalista;
	private final List<Opiskelija> valitutOp;
	private ListSelect selector;
	private Table tulosJoukko;

	public KandiTutkintoView(KandiTutkintoControl ctrl) {
		this.ctrl = ctrl;
		valitutOp = new ArrayList<Opiskelija>();
		kandiView();
	}

	public void kandiView() {
		tulosJoukko = new Table();
		asetaKaikkiOpiskelijat();
		luoKandinValinta(ctrl.annaKandit());
		addComponent(tulosJoukko);

	}

	public void asetaKaikkiOpiskelijat() {

		opiskelijalista = new ArrayList<Opiskelija>();
		opiskelijalista = ctrl.getOpiskelijat();

		// Kaikki twincol-setit täällä
		TwinColSelect op = new TwinColSelect();

		op.setNullSelectionAllowed(true);
		op.setMultiSelect(true);
		op.setImmediate(true);
		op.setLeftColumnCaption("Kaikki opiskelijat");
		op.setRightColumnCaption("Valitut opiskelijat");

		if (opiskelijalista.isEmpty()) {
			System.out.println("tyhjä");
		} else {
			for (int i = 0; i < opiskelijalista.size(); i++) {
				op.addItem(opiskelijalista.get(i).getNimi());
				op.setRows(i);
			}
		}

		op.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(final ValueChangeEvent event) {
				final String valueString = String.valueOf(event.getProperty()
						.getValue());
				vertaaKandit(valueString);

			}
		});

		addComponent(op);
	}

	public void vertaaKandit(String value) {
		for (int i = 0; i < opiskelijalista.size(); i++) {
			if (opiskelijalista.get(i).annaNimi().equals(value)) {
				valitutOp.add(opiskelijalista.get(i));
				// ctrl.annaKandiinSopivatSuoritukset(opiskelijalista.get(i));
			}
		}
		asetaKandiOpiskelijat();

	}

	private void luoKandinValinta(List<Kandi> kandit) {
		selector = new ListSelect("Kandirakenne");
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
				ctrl.vaihdaKandi(selector.getValue().toString());
			}
		});

		addComponent(selector);

	}

	public void asetaKandiOpiskelijat() {

		// Tulostaulu opintopisteiden mukaan
		tulosJoukko.setImmediate(true);
		tulosJoukko.addContainerProperty("OPISKELIJA", String.class, null);
		tulosJoukko.addContainerProperty("KURSSIT KÄYTY", String.class, null);

		tulosJoukko.removeAllItems();

		for (int i = 0; i < valitutOp.size(); i++) {

			tulosJoukko
					.addItem(
							new Object[] {
									valitutOp.get(i).getNimi(),
									ctrl.annaKandiinSopivatSuoritukset(valitutOp
											.get(i)) }, new Integer(i + 1));
		}

	}
}
