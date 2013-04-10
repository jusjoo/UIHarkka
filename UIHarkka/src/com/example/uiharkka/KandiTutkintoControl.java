package com.example.uiharkka;

import java.util.List;

public class KandiTutkintoControl {

	private final KandiTutkintoView view;
	private final List<Opiskelija> opiskelijat;

	public KandiTutkintoControl(List<Opiskelija> opiskelija) {
		this.view = new KandiTutkintoView(this);
		this.opiskelijat = opiskelija;
	}

	public List<Opiskelija> getOpiskelijat() {
		return this.opiskelijat;
	}

	public KandiTutkintoView getView() {
		return view;
	}

}
