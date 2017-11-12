/**
 * 
 */
package org.orimap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * @author julien Gaffuri
 *
 */
public class OriSHP {
	private static final String specsFile = "";

	//create empty shp files for orienteering map template
	public static void createSHPRepository(String path) {
		//ensure folders are there
		new File(path).mkdirs();

		try {
			Iterable<CSVRecord> entries = CSVFormat.EXCEL.parse(new FileReader(specsFile));
			for (CSVRecord entry : entries) {
				//String lastName = record.get("Last Name");
				//String firstName = record.get("First Name");
			}
		}
		catch (FileNotFoundException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }


	}

	public static void main(String[] args) {
		createSHPRepository("/home/juju/Bureau/orienteering/omap_luxembourg_shp/shp_/");
	}

}
