package com.example.uiharkka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;

public class KandiTutkintoView extends VerticalLayout {

	private final KandiTutkintoControl ctrl;
	private TwinColSelect op;
	private List<Opiskelija> opiskelijalista;
	private final List<Opiskelija> valitutOp;
	private List<String> opListasta;
	private ListSelect selector;
	private Table tulosJoukko;

	public KandiTutkintoView(KandiTutkintoControl ctrl) {
		this.ctrl = ctrl;
		valitutOp = new ArrayList<Opiskelija>();
		kandiView();
	}

	public void kandiView() {

		HorizontalLayout horLayout = new HorizontalLayout();
		Panel panel = new Panel(horLayout);
		panel.setWidth("800px");
		op = new TwinColSelect();
		selector = new ListSelect("Kandirakenne");
		tulosJoukko = new Table();

		// Tulostaulu opintopisteiden mukaan
		tulosJoukko.setImmediate(true);
		tulosJoukko.addContainerProperty("OPISKELIJA", String.class, null);
		tulosJoukko.addContainerProperty("KURSSIT KÄYTY", Integer.class, null);
		tulosJoukko.addContainerProperty("KANDI", String.class, null);
		horLayout.addComponent(selector);
		horLayout.addComponent(op);
		horLayout.addComponent(tulosJoukko);

		luoKandinValinta(ctrl.annaKandit());
		asetaKaikkiOpiskelijat();

		this.addComponent(panel);

	}

	public void asetaKaikkiOpiskelijat() {

		opiskelijalista = new ArrayList<Opiskelija>();
		opiskelijalista = ctrl.getOpiskelijat();

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
				String valueString = String.valueOf(event.getProperty()
						.getValue());
				tulosJoukko.removeAllItems();
				// Trimmaa tulosta
				valueString = valueString.replace("[", "");
				valueString = valueString.replace("]", "");
				System.out.println(valueString);
				// Viipaloi arraylistiin
				opListasta = new ArrayList<String>(Arrays.asList(valueString
						.split(", ")));
				vertaaKandit(opListasta);
			}
		});

	}

	public void tarkkaileListaMuutokset() {

	}

	public void vertaaKandit(List<String> value) {
		valitutOp.clear();
		for (int i = 0; i < opiskelijalista.size(); i++) {
			for (int k = 0; k < opListasta.size(); k++) {
				if (opiskelijalista.get(i).annaNimi().equals(value.get(k))) {
					valitutOp.add(opiskelijalista.get(i));
				}
			}

		}
		tulosJoukko.removeAllItems();
		asetaKandiOpiskelijat();

	}

	private void luoKandinValinta(List<Kandi> kandit) {

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
				if (opListasta == null) {
					System.out.println("TYHJÄ"); // TÄHÄN VOIS LAITTAA
													// VAROTUKSEN?
				} else {
					vertaaKandit(opListasta);
				}
			}
		});

	}

	public void asetaKandiOpiskelijat() {
		tulosJoukko.removeAllItems();
		for (int i = 0; i < valitutOp.size(); i++) {

			tulosJoukko
					.addItem(
							new Object[] {
									valitutOp.get(i).getNimi(),
									ctrl.annaKandiinSopivatSuoritukset(valitutOp
											.get(i)), ctrl.getKandi() },
							new Integer(i + 1));
		}

	}
}
