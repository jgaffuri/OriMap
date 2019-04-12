/**
 * 
 */
package org.orimap;

import org.locationtech.jts.geom.Envelope;
import org.opencarto.io.SHPUtil;
import org.opencarto.io.SHPUtil.SHPData;
import org.opencarto.util.FeatureUtil;

/**
 * @author julien Gaffuri
 *
 */
public class OriSHP {

	public static void main(String[] args) {
		System.out.println("Start");

		Envelope envClip = new Envelope(77000, 80000, 75800, 78200);
		String inBasePath = "/home/juju/Bureau/orienteering/omap_luxembourg_shp/BDLTC_SHP/";
		String outBasePath = "/home/juju/Bureau/out/";
		double equidistance = 10;





		//101,L,contour
		//102,L,index_contour
		//103,L,form_line
		//104,L,earth_bank
		//105,L,earth_wall
		//106,L,ruined_earth_wall
		//107,L,erosion_gully
		//108,L,small_erosion_gully
		//109,P,small_knoll
		//110,PO,small_elongated_knoll
		//111,P,small_depression
		//112,P,pit
		//113,S,broken_ground
		//114,S,very_broken_ground
		//115,P,prominent_landform_feature
		//201,L,impassable_cliff
		//202,L,cliff
		//203,P,rocky_pit_cave
		//204,P,boulder
		//205,P,large_boulder
		//206,S,gigantic_boulder
		//207,P,boulder_cluster
		//208,S,boulder_field
		//209,S,dense_boulder_field
		//210,S,stony_ground_slow_running
		//211,S,stony_ground_walk
		//212,S,stony_ground_fight
		//213,S,sandy_ground
		//214,S,bare_rock
		//215,L,trench

		//301,S,uncrossable_body_of_water
		//302,S,shallow_body_of_water
		//303,P,waterhole
		//304,L,crossable_watercourse
		//305,L,small_crossable_watercourse
		//306,L,minor_seasonal_water_channel
		//307,S,uncrossable_marsh
		//308,S,marsh
		//309,L,narrow_marsh
		//310,S,indistinct_marsh
		//311,P,well_fountain_water_tank
		//312,P,spring
		//313,P,prominent_water_feature

		//401,S,open_land
		//402,S,open_land_scattered_trees
		//403,S,rough_open_land
		//404,S,rough_open_land_scattered_trees
		//405,S,forest
		//406,S,Vegetation_slow
		//407,S,vegetation_slow_good_visibility
		//408,S,vegetation_walk
		//409,S,vegetation_walk_good_visibility
		//410,S,vegetation_fight
		//411,S,vegetation_impassable
		//412,S,cultivated_land
		//413,S,orchard
		//414,S,vineyard_similar
		//415,L,distinct_cultivation_boundary
		//416,L,distinct_vegetation_boundary

		//417,P,prominent_large_tree
		//418,P,prominent_bush_or_tree
		//419,P,prominent_vegetation_feature

		//501,S,paved_area
		//502,L,wide_road
		//503,L,road
		//504,L,vehicle_track
		//505,L,footpath
		//506,L,small_footpath
		//507,L,less_distinct_small_footpath
		//508,L,narrow_ride_or_linear_trace
		//509,L,railway
		clipSHP(inBasePath + "VFTE/VOIE_FER.shp", outBasePath+"509_railway.shp", envClip);

		//510,L,power_line_cableway_skilift
		clipSHP(inBasePath + "VFTE/TRANS_CABL.shp", outBasePath+"510_power_line_cableway_skilift.shp", envClip);
		//511,L,major_power_line
		clipSHP(inBasePath + "VFTE/PYLONE.shp", outBasePath+"511_major_power_line.shp", envClip);

		//512,L,bridge_tunnel

		//513,L,wall
		//514,L,ruined_wall
		//515,L,impassable_wall
		//516,L,fence
		//517,L,ruined_fence
		//518,L,impassable_fence
		//519,PO,crossing_point
		//520,S,area_shall_not_entered
		//521,S,building
		clipSHP(inBasePath + "BATI/BATIMENT.shp", outBasePath+"521_building.shp", envClip);
		//522,S,canopy
		//523,S,ruin
		//524,P,high_tower
		//525,P,small_tower
		//526,P,cairn
		clipSHP(inBasePath + "GEO/POINT_GEOD.shp", outBasePath+"526_cairn.shp", envClip);
		//527,P,fodder_rack
		//528,L,prominent_line_feature
		//529,L,prominent_impassable_line_feature
		//530,P,prominent_man_made_feature_ring
		//531,P,prominent_man_made_feature_x

		//601,L,magnetic_north_line

		System.out.println("end");
	}

	public static void clipSHP(String in, String out, Envelope env) {
		SHPData fsd = SHPUtil.loadSHP(in);
		fsd.fs = FeatureUtil.clip(fsd.fs, env);
		SHPUtil.saveSHP(fsd.fs, out, fsd.ft.getCoordinateReferenceSystem());
	}



	/*
		//createSHPRepository("/home/juju/Bureau/orienteering/omap_luxembourg_shp/shpDev/");

	private static final String specsFile = "src/main/resources/specs.csv";

	//create empty shp files for orienteering map template
	public static void createSHPRepository(String path) {
		//ensure folders are there
		new File(path).mkdirs();

		try {
			Iterable<CSVRecord> entries = CSVFormat.RFC4180.withHeader("code", "geom", "desc").parse(new FileReader(specsFile));
			for (CSVRecord e : entries) {
				System.out.println(e.get("code") + " - " + e.get("geom") + " - " + e.get("desc"));
				//TODO create SHP
			}
		}
		catch (FileNotFoundException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }


	}
	 */
}
