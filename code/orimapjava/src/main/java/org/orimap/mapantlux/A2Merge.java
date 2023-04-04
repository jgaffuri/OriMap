package org.orimap.mapantlux;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class A2Merge {
	final static Logger LOGGER = LogManager.getLogger(A2Merge.class.getName());

	public static void main(String[] args) {
		LOGGER.info("Start");

		String path = "/home/juju/Bureau/orienteering/lidar/";

		//https://gdal.org/programs/gdal_merge.html
		//gdal_merge.py -o /home/juju/Bureau/orienteering/lidar/out/lux.tiff /home/juju/Bureau/orienteering/lidar/out/lux/LIDAR2019_NdP_54500_112500_EPSG2169.laz.png /home/juju/Bureau/orienteering/lidar/out/lux/LIDAR2019_NdP_54500_112000_EPSG2169.laz.png

		//https://gis.stackexchange.com/questions/340321/merge-png-files-into-one-using-gdal
		//The solution I found was to treat the downloaded images with the ImageMagick tool to change the color space to an RGB color.


		//Try: gdalbuildvrt output.vrt "folderName\*.png" after that, load it in QGIS and try to export it as single file.
		//https://gdal.org/programs/gdalbuildvrt.html
		//This program builds a VRT (Virtual Dataset) that is a mosaic of the list of input GDAL datasets. The list of input GDAL datasets can be specified at the end of the command line, or put in a text file (one filename per line) for very long lists, or it can be a MapServer tileindex (see ref gdaltindex utility). In the later case, all entries in the tile index will be added to the VRT.
//[-input_file_list my_list.txt] [-overwrite]
		//gdalbuildvrt -input_file_list /home/juju/Bureau/orienteering/lidar/out/lux_list.txt -overwrite /home/juju/Bureau/orienteering/lidar/out/lux.vrt

		//You're advised to pre-process your rasters with other tools, such as pct2rgb.py or gdal_translate -expand RGB
		//to operate gdalbuildvrt on RGB rasters instead


		LOGGER.info("End");
	}

}
