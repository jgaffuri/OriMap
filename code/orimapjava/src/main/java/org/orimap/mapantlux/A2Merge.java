package org.orimap.mapantlux;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class A2Merge {
	final static Logger LOGGER = LogManager.getLogger(A2Merge.class.getName());

	public static void main(String[] args) {
		LOGGER.info("Start");

		String path = "/home/juju/Bureau/orienteering/lidar/";

		//https://gdal.org/programs/gdal_merge.html
		//gdal_merge.py -o /home/juju/Bureau/orienteering/lidar/lux_project/output.tiff /home/juju/Bureau/orienteering/lidar/out/lux/LIDAR2019_NdP_54500_112500_EPSG2169.laz.png /home/juju/Bureau/orienteering/lidar/out/lux/LIDAR2019_NdP_54500_112000_EPSG2169.laz.png
		
		//https://gis.stackexchange.com/questions/340321/merge-png-files-into-one-using-gdal
		//The solution I found was to treat the downloaded images with the ImageMagick tool to change the color space to an RGB color.

		LOGGER.info("End");
	}

}
