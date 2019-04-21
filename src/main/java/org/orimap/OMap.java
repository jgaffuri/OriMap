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

	public Envelope env;
	public String outPath;

	public OMap(Envelope env, String outPath) {
		this.env = env;
		this.outPath = outPath;
	}

}
