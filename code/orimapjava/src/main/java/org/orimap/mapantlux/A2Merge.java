package org.orimap.mapantlux;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class A2Merge {
	final static Logger LOGGER = LogManager.getLogger(A2Merge.class.getName());


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


	public static void main(String[] args) throws Throwable {
		LOGGER.info("Start");

		String pathOut = "/home/juju/Bureau/orienteering/lidar/";
		String listFile = pathOut + "lux_merged/lux_list.txt";
		new File(pathOut + "lux_merged/").mkdirs();

		LOGGER.info("Get output files");
		Set<String> files = A0Status.getFiles(pathOut+"out/lux/");
		LOGGER.info(files.size());

		int xS = 47500, yS = 55500;
		int xE = 108000, yE = 140000;
		int step = 10000;

		for(int x=xS; x<xE; x+=step)
			for(int y=yS; y<yE; y+=step) {
				LOGGER.info(x + " " + y);
				String sign = "_" + x + "_" + y;

				LOGGER.info("   Make output files list");
				int nb=0;
				BufferedWriter writer = new BufferedWriter(new FileWriter(listFile));

				for(String f : files) {
					if(f.contains("_EPSG2169.laz_depr.png")) continue;
					if(f.contains("_EPSG2169.laz_depr.pgw")) continue;
					if(f.contains("_EPSG2169.laz.pgw")) continue;
					if(f.contains("_undergrowth")) continue;
					if(f.contains("_vege")) continue;

					//exclude files out of the tile
					String f2 = f.replace(pathOut+"lux/", "");
					String[] sp = f2.split("_");
					int x_ = Integer.parseInt(sp[2]);
					if(x_<x) continue;
					if(x_>=x+step) continue;
					int y_ = Integer.parseInt(sp[3]);
					if(y_<y) continue;
					if(y_>=y+step) continue;

					//System.out.println(f);
					writer.write(f);
					writer.write("\n");

					nb++;
				}
				writer.close();
				LOGGER.info("   "+nb);
				if(nb == 0) continue;

				LOGGER.info("   Run gdalbuildvrt");
				String cmd = "   gdalbuildvrt -input_file_list " +listFile+ " -overwrite " +pathOut+"lux_merged/lux"+sign+".vrt";
				LOGGER.info("   " + cmd);
				run(cmd);
			}

		LOGGER.info("End");
	}


	public static void run(String cmd) {
		//ProcessBuilder processBuilder = new ProcessBuilder(cmd);
		//ProcessBuilder processBuilder = new ProcessBuilder("gdalwarp", inF, outF, "-tr", resT+"", resT+"", "-r", "mode");
		ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", cmd);
		//ProcessBuilder processBuilder = new ProcessBuilder();
		//processBuilder.command("bash", "-c", "ls /home/juju/");
		//processBuilder.command(cmd);

		try {
			Process process = processBuilder.start();
			//StringBuilder output = new StringBuilder();
			//BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			//String line;
			/*while ((line = reader.readLine()) != null) {
				//output.append(line + "\n");
			}*/

			int exitVal = process.waitFor();
			if (exitVal == 0) {
				System.out.println("Success!");
				//System.out.println(output);
			} else {
				System.err.println("Problem");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
