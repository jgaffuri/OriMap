/**
 * 
 */
package org.orimap;

import org.locationtech.jts.geom.Envelope;

/**
 * @author julien Gaffuri
 *
 */
public class OMap {

	public String name;
	public Envelope env;
	public String outPath;

	public OMap(String name, Envelope env, String outPath) {
		this.name = name;
		this.env = env;
		this.outPath = outPath;
	}

}
