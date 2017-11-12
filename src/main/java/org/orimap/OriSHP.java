/**
 * 
 */
package org.orimap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * @author julien Gaffuri
 *
 */
public class OriSHP {
	private static final String specsFile = "src/main/resources/specs.csv";

	//create empty shp files for orienteering map template
	public static void createSHPRepository(String path) {
		//ensure folders are there
		new File(path).mkdirs();

		try {
			Iterable<CSVRecord> entries = CSVFormat.RFC4180.withHeader("code", "geom", "desc").parse(new FileReader(specsFile));
			for (CSVRecord e : entries) {
				System.out.println(e.get("code") + " - " + e.get("geom") + " - " + e.get("desc"));
				//TODO create SHP
			}
		}
		catch (FileNotFoundException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }


	}

	public static void main(String[] args) {
		System.out.println("Start");
		createSHPRepository("/home/juju/Bureau/orienteering/omap_luxembourg_shp/shpDev/");
		System.out.println("end");
	}

}
