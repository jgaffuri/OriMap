package org.orimap.mapantlux;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.europa.ec.eurostat.jgiscotools.feature.Feature;
import eu.europa.ec.eurostat.jgiscotools.io.geo.GeoData;

public class Download {
	final static Logger LOGGER = LogManager.getLogger(Download.class.getName());

	//one night = 9h
	//time per tile: 15min
	//one night = 9h*4tiles/hour*4processors = 144 tiles
	//9 tiles per zip -> one night = 16 zips = 4*4 zips 
	//1287 zips: 1287/12 = 100 nights

	public static void main(String[] args) {
		LOGGER.info("Start");

		String destDir = "/home/juju/Bureau/orienteering/lidar/in/lux/";

		LOGGER.info("Load tiles");
		ArrayList<Feature> ziptiles = GeoData.getFeatures("/home/juju/Bureau/orienteering/lidar/lux_project/lidar_urls.gpkg");
		LOGGER.info(ziptiles.size());

		int xMin = 48500;
		int xMax = 59000;//;51500
		int yMin = 80500;
		int yMax = 130000;//; 95500

		//TODO parallelisation
		//ArrayList<String> urls = new ArrayList();

		for(Feature zt : ziptiles) {
			int x = (Integer) zt.getAttribute("x_llc");
			if(x<xMin) continue;
			if(x>xMax) continue;
			int y = (Integer) zt.getAttribute("y_llc");
			if(y<yMin) continue;
			if(y>yMax) continue;

			//get url
			String url = zt.getAttribute("url").toString();

			LOGGER.info("Download " + url);
			String zipFile = "/home/juju/Bureau/temp/"+x+"_"+y+".zip";
			try {
				FileUtils.copyURLToFile(new URL(url), new File(zipFile));
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}


			LOGGER.info("Unzip " + zipFile);
			try {

				byte[] buffer = new byte[1024];
				ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));

				ZipEntry zipEntry = zis.getNextEntry();
				while (zipEntry != null) {
					File newFile = new File(destDir + zipEntry);
					LOGGER.info("   " + zipEntry);

					//fix (win case)
					File parent = newFile.getParentFile();
					if (!parent.isDirectory() && !parent.mkdirs()) {
						throw new IOException("Failed to create directory " + parent);
					}

					//write
					FileOutputStream fos = new FileOutputStream(newFile);
					int len;
					while ((len = zis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
					fos.close();

					zipEntry = zis.getNextEntry();
				}

				zis.closeEntry();
				zis.close();
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

			LOGGER.info("Delete " + zipFile);
			new File(zipFile).delete();

		}

		LOGGER.info("End");
	}

}
