package org.orimap.mapantlux;

import java.util.ArrayList;

import eu.europa.ec.eurostat.jgiscotools.feature.Feature;
import eu.europa.ec.eurostat.jgiscotools.io.geo.GeoData;

public class Download {

	public static void main(String[] args) {

		String baseURL = "https://download.data.public.lu/resources/lidar-2019-releve-3d-du-territoire-luxembourgeois/20200108-130715/lidar2019-ndp-c0-r26-ll48500-95500-epsg2169.zip";

		System.out.println("Load tiles");
		ArrayList<Feature> ziptiles = GeoData.getFeatures("/home/juju/Bureau/orienteering/lidar/lux_project/lidar_urls.gpkg");
		System.out.println(ziptiles.size());


	}






}
