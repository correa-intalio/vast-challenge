package com.md.dm.vi.tp;

/**
 * 
 * 
 */
public class App {
	public static void main(String[] args) throws Exception {
		DataImporter dataImporter = new DataImporter();
		dataImporter.processWeatherInformation();
		dataImporter.processPopulationInformation();
		dataImporter.processMicroblogsInformation();
	}
}
