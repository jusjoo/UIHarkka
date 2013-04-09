package com.example.uiharkka;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class KurssikohtainenView extends VerticalLayout {
	private final KurssikohtainenControl ctrl;
	private final Table kurssilista = new Table("Kurssit");

	public KurssikohtainenView(KurssikohtainenControl ctrl) {
		this.ctrl = ctrl;
		kurssiView();
	}

	public void kurssiView() {

		// Tulostaulu vuoden mukaan
		kurssilista.setSelectable(true);
		kurssilista.setImmediate(true);
		kurssilista.addContainerProperty("KURSSIN NIMI", String.class, null);
		kurssilista.addContainerProperty("KURSSIKOODI", String.class, null);
		asetaKurssitListaan();

		addComponent(kurssilista);

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
}
