/**
 * 
 */
package org.orimap.mapantlux;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import eu.europa.ec.eurostat.jgiscotools.feature.Feature;
import eu.europa.ec.eurostat.jgiscotools.io.geo.CRSUtil;
import eu.europa.ec.eurostat.jgiscotools.io.geo.GeoData;

/**
 * @author julien Gaffuri
 */
public class A0Status {
	final static Logger LOGGER = LogManager.getLogger(A0Status.class.getName());

	private final static String path = "/home/juju/Bureau/orienteering/lidar/";
	private final static String pathIn = path + "in/lux/";
	private final static String pathOut = path + "out/lux/";
	//private final static String pathOut2 = path + "out/lux_done/";
	private final static String pathW = "/home/juju/Bureau/workspace/OriMap/code/mapantlux/";
	private final static CoordinateReferenceSystem crs = CRSUtil.getCRS(2169);

	public static void main(String[] args) throws Throwable {
		LOGGER.info("Start");

		//in laz
		in();
		//out png
		out();

		LOGGER.info("End");
	}


	private static void in() {
		LOGGER.info("Get input files");
		Set<String> files = getFiles(pathIn);
		LOGGER.info(files.size());

		LOGGER.info("Make input files geo");
		Collection<Feature> fs = new ArrayList<>();
		for(String f : files) {
			f = f.replace(pathIn, "");
			//System.out.println(f);
			//LIDAR2019_NdP_59500_115000_EPSG2169.laz
			String[] sp = f.split("_");
			int x = Integer.parseInt(sp[2]);
			int y = Integer.parseInt(sp[3]);
			Feature ft = new Feature();
			//ft.setAttribute("file", f);
			//ft.setAttribute("x", x);
			//ft.setAttribute("y", y);
			ft.setGeometry(new GeometryFactory().createPolygon(new Coordinate[] { new Coordinate(x,y), new Coordinate(x+500,y), new Coordinate(x+500,y-500), new Coordinate(x,y-500), new Coordinate(x,y) } ));
			fs.add(ft);
		}
		LOGGER.info(fs.size());

		LOGGER.info("Save");
		//GeoData.save(fs, path+"lux_project/files_input.gpkg", crs);
		GeoData.save(fs, pathW+"files_input.geojson", crs);
	}



	private static void out() throws Throwable {
		LOGGER.info("Get output files");
		Set<String> files = getFiles(pathOut);
		//files.addAll(getFiles(pathOut2));
		LOGGER.info(files.size());

		LOGGER.info("Make output files geo");
		Collection<Feature> fs = new ArrayList<>();
		for(String f : files) {
			if(f.contains("_EPSG2169.laz_depr.png")) continue;
			if(f.contains("_EPSG2169.laz_depr.pgw")) continue;
			if(f.contains("_EPSG2169.laz.pgw")) continue;
			if(f.contains("_undergrowth")) continue;
			if(f.contains("_vege")) continue;
			if(f.contains(".xml")) {
				//System.err.println(f);
				continue;
			}
			if(!f.contains(".png")) {
				System.err.println(f);
				continue;
			}

			String f2 = f.replace(pathOut, "");
			//f2 = f2.replace(pathOut2, "");
			//System.out.println(f2);
			//LIDAR2019_NdP_53000_111000_EPSG2169.laz.png
			String[] sp = f2.split("_");
			int x = Integer.parseInt(sp[2]);
			int y = Integer.parseInt(sp[3]);
			Feature ft = new Feature();
			//ft.setAttribute("file", f);
			//ft.setAttribute("x", x);
			//ft.setAttribute("y", y);
			int size = (int) Files.size(Paths.get(f));
			ft.setAttribute("st", size==0?"1":"0");
			ft.setGeometry(new GeometryFactory().createPolygon(new Coordinate[] { new Coordinate(x,y), new Coordinate(x+500,y), new Coordinate(x+500,y-500), new Coordinate(x,y-500), new Coordinate(x,y) } ));
			fs.add(ft);
		}
		LOGGER.info(fs.size());

		LOGGER.info("Around " + (Math.ceil(10000*fs.size()/(1287*9))/100) + " % completed");

		LOGGER.info("Save");
		//GeoData.save(fs, path+"lux_project/files_output.gpkg", crs);
		GeoData.save(fs, pathW+"files_output.geojson", crs);
	}





	public static Set<String> getFiles(String dir) {
		return Stream.of(new File(dir).listFiles())
				.filter(file -> !file.isDirectory())
				.map(File::getAbsolutePath)
				.collect(Collectors.toSet());
	}

}
