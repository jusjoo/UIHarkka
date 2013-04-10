package com.example.uiharkka;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;

public class KandiTutkintoView extends VerticalLayout {

	private final KandiTutkintoControl ctrl;
	private TwinColSelect op;

	public KandiTutkintoView(KandiTutkintoControl ctrl) {
		this.ctrl = ctrl;
		kandiView();
	}

	public void kandiView() {

		TwinColSelect op = new TwinColSelect();

		op.setNullSelectionAllowed(true);
		op.setMultiSelect(true);
		op.setImmediate(true);
		op.setLeftColumnCaption("Kaikki opiskelijat");
		op.setRightColumnCaption("Valitut opiskelijat");
		asetaOpiskelijat();

		addComponent(op);
	}

	public void asetaOpiskelijat() {
		List<Opiskelija> opiskelijalista = new ArrayList<Opiskelija>();
		opiskelijalista = ctrl.getOpiskelijat();

		if (opiskelijalista == null) {
			System.out.println("DS;MSD;MSDA");
		} else {
			for (int i = 0; i < opiskelijalista.size(); i++) {
				op.addItem(i);
				op.setCaption(opiskelijalista.get(i).getNimi());
				op.setRows(i);
			}
		}

	}
}
