package org.orimap.mapantlux;

import java.util.ArrayList;

import eu.europa.ec.eurostat.jgiscotools.feature.Feature;
import eu.europa.ec.eurostat.jgiscotools.io.geo.GeoData;
import eu.europa.ec.eurostat.jgiscotools.io.web.HTTPUtil;

public class Download {

	public static void main(String[] args) {
		System.out.println("Start");

		String baseURL = "https://download.data.public.lu/resources/lidar-2019-releve-3d-du-territoire-luxembourgeois/20200108-130715/lidar2019-ndp-c0-r26-ll48500-95500-epsg2169.zip";

		System.out.println("Load tiles");
		ArrayList<Feature> ziptiles = GeoData.getFeatures("/home/juju/Bureau/orienteering/lidar/lux_project/lidar_urls.gpkg");
		System.out.println(ziptiles.size());

		int xMin = 48500;
		int xMax = 51500;//59000;
		int yMin = 80500;
		int yMax = 95500;//130000;

		for(Feature zt : ziptiles) {
			int x = (Integer) zt.getAttribute("x_llc");
			if(x<xMin) continue;
			if(x>xMax) continue;
			int y = (Integer) zt.getAttribute("y_llc");
			if(y<yMin) continue;
			if(y>yMax) continue;

			String url = zt.getAttribute("url").toString();
			System.out.println(url);

			//download
			HTTPUtil.downloadFromURL(url, "/home/juju/Bureau/temp/");

			//unzip
		}

		System.out.println("End");
	}

}
