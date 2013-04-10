package com.example.uiharkka;

import java.io.IOException;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;

/**
 * Main UI class
 */

@SuppressWarnings("serial")
public class UiharkkaUI extends UI {

	private AllData allData;
	private TabSheet layout;

	@Override
	protected void init(VaadinRequest request) {
		layout = new TabSheet();
		setContent(layout);

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
		KurssikohtainenControl kurssictrl = new KurssikohtainenControl(allData);
		OpiskelijaControl opiskelijaControl = new OpiskelijaControl(allData
				.getOpiskelijat().get(0), allData);
		ConfigControl config = new ConfigControl();
		KandiTutkintoControl kandictrl = new KandiTutkintoControl(
				allData.getOpiskelijat());

		layout.addTab(opiskelijajoukko.getView(), "Opiskelijajoukon näkymä");
		layout.addTab(kurssictrl.getView(), "Kurssikohtainen näkymä");
		layout.addTab(opiskelijaControl.getView(), "Opiskelijakohtainen näkymä");
		layout.addTab(config.getView(), "Konfiguraatio");

		layout.addTab(kandictrl.getView(), "Kanditutkintonäkymät");

	}
}
