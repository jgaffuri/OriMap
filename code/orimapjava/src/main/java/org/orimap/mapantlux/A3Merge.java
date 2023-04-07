package org.orimap.mapantlux;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class A3Merge {
	final static Logger LOGGER = LogManager.getLogger(A3Merge.class.getName());


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

	static String pathOut = "/home/juju/Bureau/orienteering/lidar/";
	static String listFile = pathOut + "lux_merged/lux_list.txt";


	public static void main(String[] args) throws Throwable {
		LOGGER.info("Start");

		//isIndexed(pathOut+"out/lux/LIDAR2019_NdP_59000_97000_EPSG2169.laz.png");
		//isIndexed(pathOut+"out/lux/LIDAR2019_NdP_96500_75500_EPSG2169.laz.png");
		//System.exit(0);

		new File(pathOut + "lux_merged/").mkdirs();

		LOGGER.info("Get output files");
		Set<String> files = A0Status.getFiles(pathOut+"out/lux/");
		LOGGER.info(files.size());

		int xS = 47500, yS = 55500;
		//int xE = 50000, yE = 60000;
		int xE = 108000, yE = 140000;
		int step = 5000;

		for(int x=xS; x<xE; x+=step)
			for(int y=yS; y<yE; y+=step) {
				LOGGER.info(x + " " + y);
				String sign = "_" + x + "_" + y;

				int nb=0;
				StringBuffer sb = new StringBuffer();
				for(String f : files) {
					if(f.contains("_EPSG2169.laz_depr.png")) continue;
					if(f.contains("_EPSG2169.laz_depr.pgw")) continue;
					if(f.contains("_EPSG2169.laz.pgw")) continue;
					if(f.contains("_undergrowth")) continue;
					if(f.contains("_vege")) continue;
					if(f.contains(".xml")) continue;
					if(f.contains(".pgw")) continue;

					int size = (int) Files.size(Paths.get(f));
					if(size == 0) continue;

					//exclude files out of the tile
					String f2 = f.replace(pathOut+"lux/", "");
					String[] sp = f2.split("_");
					int x_ = Integer.parseInt(sp[2]);
					if(x_<x) continue;
					if(x_>=x+step) continue;
					int y_ = Integer.parseInt(sp[3]);
					if(y_<y) continue;
					if(y_>=y+step) continue;

					/*/System.out.println(f);
					if(isIndexed(f)) {
						//LOGGER.info("   to RGB");
						String cmd = "pct2rgb.py " + f + " " + f;
						//System.out.println(cmd);
						A3Merge.run(cmd, false);
					}*/

					sb.append(f);
					sb.append(" ");

					nb++;
				}
				LOGGER.info("   "+nb);
				if(nb == 0) continue;

				LOGGER.info("   Run gdal_merge");
				String cmd = "gdal_merge.py -init \"255 255 255\" -o " +pathOut+"lux_merged/lux"+sign+".tiff " + sb.toString();
				//gdal_merge.py -o /home/juju/Bureau/orienteering/lidar/out/lux.tiff /home/juju/Bureau/orienteering/lidar/out/lux/LIDAR2019_NdP_54500_112500_EPSG2169.laz.png /home/juju/Bureau/orienteering/lidar/out/lux/LIDAR2019_NdP_54500_112000_EPSG2169.laz.png
				//LOGGER.info("   " + cmd);
				A3Merge.run(cmd, true);

				LOGGER.info("   Build pyramids with gdaladdo");
				A3Merge.run("gdaladdo -r average "+pathOut+"lux_merged/lux"+sign+".tiff", true);

			}


		LOGGER.info("End");
	}



	/*private static boolean isIndexed(String f) throws Throwable {

		//TODO
		//use gdalinfo ?
		//or https://stackoverflow.com/questions/16115851/read-image-metadata-from-single-file-with-java



		File file = new File( f );
		ImageInputStream iis = ImageIO.createImageInputStream(file);
		Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
		if (readers.hasNext()) {
			// pick the first available ImageReader
			ImageReader reader = readers.next();
			// attach source to the reader
			reader.setInput(iis, true);
			// read metadata of first image
			IIOMetadata metadata = reader.getImageMetadata(0);
			String[] names = metadata.getMetadataFormatNames();
			for (int i = 0; i < names.length; i++) {
				System.out.println( "Format name: " + names[ i ] );
				Node md = metadata.getAsTree(names[i]);
				NodeList li = md.getChildNodes();
				for(int j =0; j<li.getLength(); j++) {
					IIOMetadataNode aaa = (IIOMetadataNode) li.item(j);
					System.out.println("   " + aaa.getLength());
				}


				//displayMetadata(metadata.getAsTree(names[i]));
			}
		}
	 */



	/*

gdalinfo LIDAR2019_NdP_59000_97000_EPSG2169.laz.png 

Driver: PNG/Portable Network Graphics
Files: LIDAR2019_NdP_59000_97000_EPSG2169.laz.png
       LIDAR2019_NdP_59000_97000_EPSG2169.laz.pgw
Size is 1183, 1183
Origin = (58999.930000000029395,96999.999999999970896)
Pixel Size = (0.423333333333333,-0.423333333333333)
Corner Coordinates:
Upper Left  (   58999.930,   97000.000) 
Lower Left  (   58999.930,   96499.197) 
Upper Right (   59500.733,   97000.000) 
Lower Right (   59500.733,   96499.197) 
Center      (   59250.332,   96749.598) 
Band 1 Block=1183x1 Type=Byte, ColorInterp=Palette
  Image Structure Metadata:
    NBITS=4
  Color Table (RGB with 9 entries)
    0: 255,255,255,255
    1: 166,85,43,255
    2: 180,246,180,255
    3: 160,239,160,255
    4: 140,231,140,255
    5: 200,254,200,255
    6: 255,219,166,255
    7: 0,0,0,255
    8: 120,224,120,255

gdalinfo LIDAR2019_NdP_96500_75500_EPSG2169.laz.png

Driver: PNG/Portable Network Graphics
Files: LIDAR2019_NdP_96500_75500_EPSG2169.laz.png
       LIDAR2019_NdP_96500_75500_EPSG2169.laz.png.aux.xml
Size is 1183, 1184
Origin = (96499.980000000025029,75499.989999999976135)
Pixel Size = (0.423333333333333,-0.423333333333333)
Image Structure Metadata:
  INTERLEAVE=PIXEL
Corner Coordinates:
Upper Left  (   96499.980,   75499.990) 
Lower Left  (   96499.980,   74998.763) 
Upper Right (   97000.783,   75499.990) 
Lower Right (   97000.783,   74998.763) 
Center      (   96750.382,   75249.377) 
Band 1 Block=1183x1 Type=Byte, ColorInterp=Red
Band 2 Block=1183x1 Type=Byte, ColorInterp=Green
Band 3 Block=1183x1 Type=Byte, ColorInterp=Blue

	 */	
	/*
		return false;
	}
	 */






	public static void run(String cmd, boolean message) {
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
				if(message) System.out.println("Done");
				//System.out.println(output);
			} else {
				if(message) System.err.println("Problem");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
