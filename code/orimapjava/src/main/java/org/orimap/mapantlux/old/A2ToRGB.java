package org.orimap.mapantlux.old;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.orimap.mapantlux.A0Status;
import org.orimap.mapantlux.A2Merge;

public class A2ToRGB {
	final static Logger LOGGER = LogManager.getLogger(A2ToRGB.class.getName());

	//You're advised to pre-process your rasters with other tools, such as pct2rgb.py or gdal_translate -expand RGB
	//to operate gdalbuildvrt on RGB rasters instead

	static String pathOut = "/home/juju/Bureau/orienteering/lidar/out/lux/";

	public static void main(String[] args) throws Throwable {
		LOGGER.info("Start");

		LOGGER.info("Get output files");
		Set<String> files = A0Status.getFiles(pathOut);
		LOGGER.info(files.size());

		for(String f : files) {
			if(f.contains(".pgw")) continue;
			if(!f.contains(".png")) {
				System.err.println(f);
				continue;
			}

			int size = (int) Files.size(Paths.get(f));
			if(size == 0) continue;

			///home/juju/Bureau/orienteering/lidar/out/lux/LIDAR2019_NdP_51000_108500_EPSG2169.laz.png
			//String f_ = new File(f).getName();
			//LIDAR2019_NdP_90000_80500_EPSG2169.laz.png

			//
			//LOGGER.info(f);
			String cmd = "pct2rgb.py " + f + " " + f;
			//System.out.println(cmd);
			A2Merge.run(cmd, false);
		}

		LOGGER.info("End");
	}

}
