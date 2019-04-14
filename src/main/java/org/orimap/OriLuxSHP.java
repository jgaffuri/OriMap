/**
 * 
 */
package org.orimap;

import org.geotools.filter.text.cql2.CQL;
import org.geotools.geometry.jts.JTS;
import org.locationtech.jts.geom.Envelope;
import org.opencarto.io.SHPUtil;
import org.opencarto.io.SHPUtil.SHPData;
import org.opencarto.util.FeatureUtil;
import org.opengis.filter.Filter;

/**
 * @author julien Gaffuri
 *
 */
public class OriLuxSHP {

	public static void main(String[] args) throws Exception {
		System.out.println("Start");

		Envelope envClip = new Envelope(77000, 80000, 75800, 78200);
		String inBasePath = "/home/juju/Bureau/orienteering/omap_luxembourg_shp/BDLTC_SHP/";
		String outBasePath = "/home/juju/Bureau/out/";

		/*
		 *ADM
		 *ALTI
		 *BATI
		 *GEO
		HYDR
		 *NYM
		 *ORO
		VCR
		 *VEGE
		 *VFTE
		 */


		//101,L,contour
		clipSHP(inBasePath + "ALTI/COURBE.shp", outBasePath+"101_contour.shp", envClip, CQL.toFilter( "NATURE = 0 OR NATURE = 3" ));
		//102,L,index_contour
		clipSHP(inBasePath + "ALTI/COURBE.shp", outBasePath+"102_index_contour.shp", envClip, CQL.toFilter( "NATURE = 1" ));
		//103,L,form_line
		clipSHP(inBasePath + "ALTI/COURBE.shp", outBasePath+"103_form_line.shp", envClip, CQL.toFilter( "NATURE = 2" ));
		//104,L,earth_bank
		clipSHP(inBasePath + "ORO/LIGNE_ORO.shp", outBasePath+"104_earth_bank.shp", envClip, CQL.toFilter( "NATURE = 0 OR NATURE = 2" ));
		//105,L,earth_wall
		clipSHP(inBasePath + "ORO/LIGNE_ORO.shp", outBasePath+"105_earth_wall.shp", envClip, CQL.toFilter( "NATURE = 1" ));
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
		clipSHP(inBasePath + "BATI/CONSTRUC_PONCT.shp", outBasePath+"313_prominent_water_feature.shp", envClip, CQL.toFilter( "NATURE = 12" ));

		//401,S,open_land
		//SHPUtil.saveGeomsSHP(JTS.toGeometry(envClip), outBasePath+"401_open_land.shp"); //TODO complement of VEGE + others?
		//402,S,open_land_scattered_trees
		//402.1,S,open_land_scattered_trees
		clipSHP(inBasePath + "VEGE/VEGETATION_SURF.shp", outBasePath+"402.1_open_land_scattered_trees.shp", envClip, CQL.toFilter( "NATURE = 5" ));
		//403,S,rough_open_land
		clipSHP(inBasePath + "BATI/TERR_SPORT.shp", outBasePath+"403_rough_open_land.shp", envClip);
		//404,S,rough_open_land_scattered_trees
		//405,S,forest
		clipSHP(inBasePath + "VEGE/VEGETATION_SURF.shp", outBasePath+"405_forest.shp", envClip, CQL.toFilter( "NATURE = 0 OR NATURE = 1 OR NATURE = 2" ));
		//406,S,Vegetation_slow
		//407,S,vegetation_slow_good_visibility
		//408,S,vegetation_walk
		clipSHP(inBasePath + "VEGE/VEGETATION_SURF.shp", outBasePath+"408_vegetation_walk.shp", envClip, CQL.toFilter( "NATURE = 3" ));
		//409,S,vegetation_walk_good_visibility
		//410,S,vegetation_fight
		//411,S,vegetation_impassable
		//411.2,L,vegetation_impassable_minw
		clipSHP(inBasePath + "VEGE/VEGETATION_LIN.shp", outBasePath+"411.2_vegetation_impassable_minw.shp", envClip, CQL.toFilter( "NATURE = 1 OR NATURE = 2" ));
		//412,S,cultivated_land
		//413,S,orchard
		clipSHP(inBasePath + "VEGE/VEGETATION_SURF.shp", outBasePath+"413_orchard.shp", envClip, CQL.toFilter( "NATURE = 4" ));
		//414,S,vineyard_similar
		clipSHP(inBasePath + "VEGE/VEGETATION_SURF.shp", outBasePath+"414_vineyard_similar.shp", envClip, CQL.toFilter( "NATURE = 6" ));
		//415,L,distinct_cultivation_boundary
		clipSHP(inBasePath + "VEGE/VEGETATION_LIN.shp", outBasePath+"415_distinct_cultivation_boundary.shp", envClip, CQL.toFilter( "NATURE = 0" ));
		//416,L,distinct_vegetation_boundary
		clipSHP(inBasePath + "VEGE/VEGETATION_LIN.shp", outBasePath+"416_distinct_vegetation_boundary.shp", envClip, CQL.toFilter( "NATURE = 3 OR NATURE = 4" ));

		//417,P,prominent_large_tree
		//418,P,prominent_bush_or_tree
		clipSHP(inBasePath + "VEGE/ARB_ISOLE.shp", outBasePath+"418_prominent_bush_or_tree.shp", envClip);
		//419,P,prominent_vegetation_feature

		//501,S,paved_area_with_bn
		clipSHP(inBasePath + "BATI/PISTE_AERO.shp", outBasePath+"501_paved_area_with_bn.shp", envClip);
		//501.1,S,paved_area
		///home/juju/Bureau/orienteering/omap_luxembourg_shp/BDLTC_SHP/HYDR/ECLUSE.shp
		//502,L,wide_road
		//503,L,road
		//504,L,vehicle_track
		//505,L,footpath
		//506,L,small_footpath
		//507,L,less_distinct_small_footpath
		//508,L,narrow_ride_or_linear_trace
		//509,L,railway
		clipSHP(inBasePath + "VFTE/VOIE_FER.shp", outBasePath+"509_railway.shp", envClip, CQL.toFilter( "POSITION_S >= 0" ));

		//510,L,power_line_cableway_skilift
		clipSHP(inBasePath + "VFTE/TRANS_CABL.shp", outBasePath+"510_power_line_cableway_skilift.shp", envClip);
		//511,L,major_power_line
		clipSHP(inBasePath + "VFTE/LIGNE_ELEC.shp", outBasePath+"511_major_power_line.shp", envClip);

		//512,L,bridge_tunnel

		//513,L,wall
		//514,L,ruined_wall
		//515,L,impassable_wall
		clipSHP(inBasePath + "BATI/CONSTRUC_LINE.shp", outBasePath+"515_impassable_wall.shp", envClip, CQL.toFilter( "NATURE = 4 OR NATURE = 5" ));
		//516,L,fence
		//517,L,ruined_fence
		//518,L,impassable_fence
		clipSHP(inBasePath + "BATI/CONSTRUC_LINE.shp", outBasePath+"518_impassable_fence.shp", envClip, CQL.toFilter( "NATURE = 6" ));
		//519,PO,crossing_point
		//520,S,area_shall_not_entered
		clipSHP(inBasePath + "BATI/CIMETIERE.shp", outBasePath+"520_area_shall_not_entered.shp", envClip);
		//521,S,building
		clipSHP(inBasePath + "BATI/BATIMENT.shp", outBasePath+"521_building.shp", envClip);
		clipSHP(inBasePath + "BATI/CONSTRUC_SURF.shp", outBasePath+"521_building2.shp", envClip); //TODO merge
		//521.1,P,building_min
		clipSHP(inBasePath + "BATI/CONSTRUC_PONCT.shp", outBasePath+"521.1_building_min.shp", envClip, CQL.toFilter( "NATURE = 9 OR NATURE = 10 OR NATURE = 11 OR NATURE = 13" ));
		//521.4,L,building_outline
		clipSHP(inBasePath + "BATI/LIM_TOIT.shp", outBasePath+"521.4_building_outline.shp", envClip);
		//522,S,canopy
		//523,L,ruin
		clipSHP(inBasePath + "BATI/CONSTRUC_LINE.shp", outBasePath+"523_ruin.shp", envClip, CQL.toFilter( "NATURE = 3" ));
		//524,P,high_tower
		clipSHP(inBasePath + "VFTE/PYLONE.shp", outBasePath+"524_high_tower.shp", envClip);
		clipSHP(inBasePath + "BATI/CONSTRUC_PONCT.shp", outBasePath+"524_high_tower.shp", envClip, CQL.toFilter( "NATURE = 1" )); //TODO merge
		//525,P,small_tower
		//526,P,cairn
		clipSHP(inBasePath + "GEO/POINT_GEOD.shp", outBasePath+"526_cairn.shp", envClip);
		clipSHP(inBasePath + "ADM/BORN_FRONT.shp", outBasePath+"526_cairn2.shp", envClip); //TODO merge
		//527,P,fodder_rack
		//528,L,prominent_line_feature
		clipSHP(inBasePath + "BATI/CONSTRUC_LINE.shp", outBasePath+"528_prominent_line_feature.shp", envClip, CQL.toFilter( "NATURE = 1 OR NATURE = 2" ));
		//529,L,prominent_impassable_line_feature
		//530,P,prominent_man_made_feature_ring
		clipSHP(inBasePath + "BATI/CONSTRUC_PONCT.shp", outBasePath+"530_prominent_man_made_feature_ring.shp", envClip, CQL.toFilter( "NATURE = 2 OR NATURE = 3 OR NATURE = 4 OR NATURE = 5 OR NATURE = 6 OR NATURE = 7 OR NATURE = 8" ));
		//531,P,prominent_man_made_feature_x

		//601,L,magnetic_north_line
		//603.0,P,spot_height
		clipSHP(inBasePath + "ALTI/POINT_COTE.shp", outBasePath+"603.0_spot_height.shp", envClip, CQL.toFilter( "ECHELLE = 1" ));

		System.out.println("end");
	}

	public static void clipSHP(String in, String out, Envelope env) { clipSHP(in, out, env, null); }
	public static void clipSHP(String in, String out, Envelope env, Filter f) {
		SHPData fsd = SHPUtil.loadSHP(in, f);
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
