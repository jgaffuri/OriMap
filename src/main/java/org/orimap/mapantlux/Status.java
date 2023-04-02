/**
 * 
 */
package org.orimap.mapantlux;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author juju
 *
 */
public class Status {
	final static Logger LOGGER = LogManager.getLogger(Status.class.getName());

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LOGGER.info("Start");

		String path = "/home/juju/Bureau/orienteering/lidar/";
		String pathIn = path + "in/lux/";
		String pathOut = path + "out/lux/";






		LOGGER.info("End");
	}

	public static Set<String> listFilesUsingJavaIO(String dir) {
		return Stream.of(new File(dir).listFiles())
				.filter(file -> !file.isDirectory())
				.map(File::getName)
				.collect(Collectors.toSet());
	}

}
