package com.example.uiharkka;

import java.io.IOException;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
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


		allData.printOpiskelijat();
		allData.printKurssit();
		allData.printKandit();

		layout.setMargin(true);
		setContent(layout);


		Table nakymanValinta = new Table("Näkymän valinta");
		nakymanValinta.setSelectable(true);
		nakymanValinta.setImmediate(true);
		nakymanValinta.addContainerProperty("Valinta", String.class, null);
		nakymanValinta.addItem(new Object[] { "Kurssit" }, new Integer(1));
		nakymanValinta.addItem(new Object[] { "Opiskelijat" }, new Integer(2));

		

		Button button = new Button("Click Me");
		button.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				layout.addComponent(new Label("Thank you for clicking"));

			}
		});
		layout.addComponent(button);
		layout.addComponent(nakymanValinta);
		
		layout.addComponent(new OpiskelijaView(allData.getOpiskelijat().get(0), allData));
	}

}
