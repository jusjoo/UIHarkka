package com.example.uiharkka;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class KurssikohtainenView extends VerticalLayout {
	private final KurssikohtainenControl ctrl;
	private final Table kurssilista = new Table("Kurssit");
	private final Table suorittajat = new Table("Suorittajat");

	public KurssikohtainenView(KurssikohtainenControl ctrl) {
		this.ctrl = ctrl;
		kurssiView();
	}

	public void kurssiView() {

		kurssilista.setSelectable(true);
		kurssilista.setImmediate(true);
		kurssilista.addContainerProperty("KURSSIN NIMI", String.class, null);
		kurssilista.addContainerProperty("KURSSIKOODI", String.class, null);

		suorittajat.setImmediate(true);
		suorittajat
				.addContainerProperty("OPISKELIJAN NIMI", String.class, null);

		kurssilista.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(final ValueChangeEvent event) {
				int valueString = (Integer) event.getProperty().getValue();
				String kurssinID = (String) kurssilista.getContainerProperty(
						valueString, "KURSSIKOODI").getValue();
				ctrl.setKurssiID(kurssinID);
				ctrl.setYksittainenKurssi();
				asetaSuorittajat();
			}
		});
		asetaKurssitListaan();

		addComponent(kurssilista);
		addComponent(suorittajat);

	}

	public void asetaKurssitListaan() {
		List<Kurssi> kurssit = new ArrayList<Kurssi>();
		kurssit = ctrl.getKurssit();

		kurssilista.removeAllItems();

		for (int i = 0; i < kurssit.size(); i++) {

			kurssilista.addItem(new Object[] { kurssit.get(i).annaNimi(),
					kurssit.get(i).annaID() }, new Integer(i + 1));
		}

	}

	public void asetaSuorittajat() {
		List<Opiskelija> opiskelija = new ArrayList<Opiskelija>();
		opiskelija = ctrl.getKurssikohtainenSuoritus();

		suorittajat.removeAllItems();

		for (int i = 0; i < opiskelija.size(); i++) {
			suorittajat.addItem(new Object[] { opiskelija.get(i).getNimi() },
					new Integer(i + 1));
		}
	}
}
