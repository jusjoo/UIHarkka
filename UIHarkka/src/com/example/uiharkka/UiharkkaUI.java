package com.example.uiharkka;

import java.io.IOException;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Main UI class
 */

@SuppressWarnings("serial")
public class UiharkkaUI extends UI {

	private AllData allData;

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();

		String basepath = VaadinService.getCurrent().getBaseDirectory()
				.getAbsolutePath();

		String kandit = basepath + "/data/kandit.txt";
		String opiskelijat = basepath + "/data/opiskelijat.txt";
		String suoritukset = basepath + "/data/suoritukset.txt";

		allData = new AllData();

		DataImport dataImport;
		try {
			dataImport = new DataImport(kandit, opiskelijat, suoritukset);
			allData.asetaOpiskelijat(dataImport.getOpiskelijat());
			allData.asetaKurssit(dataImport.getKurssit());
			allData.asetaKandit(dataImport.getKandit());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		OpiskelijaJoukko opiskelijajoukko = new OpiskelijaJoukko(allData);
		opiskelijajoukko.setAlaraja(0);
		opiskelijajoukko.setYlaraja(250);
		opiskelijajoukko.setVuosi("2007S");

		allData.printOpiskelijat();
		allData.printKurssit();
		allData.printKandit();

		layout.setMargin(true);
		setContent(layout);

		Button button = new Button("Click Me");
		button.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				layout.addComponent(new Label("Thank you for clicking"));

			}
		});
		layout.addComponent(button);
		layout.addComponent(opiskelijajoukko.getView());

		OpiskelijaControl ctrl = new OpiskelijaControl(allData.getOpiskelijat()
				.get(0), allData);
		layout.addComponent(ctrl.getView());
	}
}
