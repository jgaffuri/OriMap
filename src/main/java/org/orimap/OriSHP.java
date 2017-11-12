/**
 * 
 */
package org.orimap;

import java.io.File;

/**
 * @author julien Gaffuri
 *
 */
public class OriSHP {


	//create empty shp files for orienteering map template
	public static void createSHPRepository(String path) {
		//ensure folders are there
		new File(path).mkdirs();

		
		
	}

	public static void main(String[] args) {
		createSHPRepository("/home/juju/Bureau/orienteering/omap_luxembourg_shp/shp_/");
	}

}
