/**
 * 
 */
package org.orimap;

import org.geotools.filter.text.cql2.CQL;
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

		Envelope kirchbergEnv = new Envelope(77000, 80000, 75800, 78200);
		String inBasePath = "/home/juju/Bureau/orienteering/data/BDLTC_SHP/";
		String inBaseOSMPath = "/home/juju/Bureau/orienteering/data/osm/luxembourg-latest-free.shp/";
		String outBasePath = "/home/juju/Bureau/out/";

		//TODO decompose: fun BDT to ori, fun OSM to ori, fun cadastre to ori. comparison, fusion
		//TODO function ori clip

		//101_L_contour
		extractSHP(inBasePath + "ALTI/COURBE.shp", outBasePath+"101_L_contour.shp", kirchbergEnv, CQL.toFilter( "NATURE = 0 OR NATURE = 3" ));
		//102_L_index_contour
		extractSHP(inBasePath + "ALTI/COURBE.shp", outBasePath+"102_L_index_contour.shp", kirchbergEnv, CQL.toFilter( "NATURE = 1" ));
		//103_L_form_line
		extractSHP(inBasePath + "ALTI/COURBE.shp", outBasePath+"103_L_form_line.shp", kirchbergEnv, CQL.toFilter( "NATURE = 2" ));
		//104_L_earth_bank
		extractSHP(inBasePath + "ORO/LIGNE_ORO.shp", outBasePath+"104_L_earth_bank.shp", kirchbergEnv, CQL.toFilter( "NATURE = 0 OR NATURE = 2" ));
		//105_L_earth_wall
		extractSHP(inBasePath + "ORO/LIGNE_ORO.shp", outBasePath+"105_L_earth_wall.shp", kirchbergEnv, CQL.toFilter( "NATURE = 1" ));
		//106_L_ruined_earth_wall
		//107_L_erosion_gully
		//108_L_small_erosion_gully
		//109_P_small_knoll
		//110,PO,small_elongated_knoll
		//111_P_small_depression
		//112_P_pit
		//113_S_broken_ground
		//114_S_very_broken_ground
		//115_P_prominent_landform_feature
		//201_L_impassable_cliff
		//202_L_cliff
		//203_P_rocky_pit_cave
		//204_P_boulder
		//205_P_large_boulder
		//206_S_gigantic_boulder
		//207_P_boulder_cluster
		//208_S_boulder_field
		//209_S_dense_boulder_field
		//210_S_stony_ground_slow_running
		//211_S_stony_ground_walk
		//212_S_stony_ground_fight
		//213_S_sandy_ground
		//214_S_bare_rock
		//215_L_trench

		//301_S_uncrossable_body_of_water
		extractSHP(inBasePath + "HYDR/SURFACE_EAU.shp", outBasePath+"301_S_uncrossable_body_of_water.shp", kirchbergEnv, CQL.toFilter( "NATURE = 0 OR NATURE = 3 OR NATURE = 4 OR NATURE = 5 OR NATURE = 6" ));
		//302_S_shallow_body_of_water
		extractSHP(inBasePath + "HYDR/SURFACE_EAU.shp", outBasePath+"302_S_shallow_body_of_water.shp", kirchbergEnv, CQL.toFilter( "NATURE = 1" ));
		//303_P_waterhole
		//304_L_crossable_watercourse
		extractSHP(inBasePath + "HYDR/TRONCON_EAU.shp", outBasePath+"304_L_crossable_watercourse.shp", kirchbergEnv, CQL.toFilter( "POSITION_S >= 0 AND TYPE = 0" ));
		//305_L_small_crossable_watercourse
		//306_L_minor_seasonal_water_channel
		extractSHP(inBasePath + "HYDR/TRONCON_EAU.shp", outBasePath+"306_L_minor_seasonal_water_channel.shp", kirchbergEnv, CQL.toFilter( "POSITION_S >= 0 AND TYPE = 1" ));
		//307_S_uncrossable_marsh
		//308_S_marsh
		extractSHP(inBasePath + "HYDR/SURFACE_EAU.shp", outBasePath+"308_S_marsh.shp", kirchbergEnv, CQL.toFilter( "NATURE = 2" ));
		//309_L_narrow_marsh
		//310_S_indistinct_marsh
		//311_P_well_fountain_water_tank
		extractSHP(inBasePath + "BATI/CONSTRUC_PONCT.shp", outBasePath+"311_P_well_fountain_water_tank.shp", kirchbergEnv, CQL.toFilter( "NATURE = 12" ));
		extractSHP(inBasePath + "HYDR/POINT_EAU.shp", outBasePath+"311_P_well_fountain_water_tank2.shp", kirchbergEnv, CQL.toFilter( "NATURE = 1 OR NATURE = 4" ));
		//312_P_spring
		extractSHP(inBasePath + "HYDR/POINT_EAU.shp", outBasePath+"312_P_spring.shp", kirchbergEnv, CQL.toFilter( "NATURE = 2 OR NATURE = 3" ));
		//313_P_prominent_water_feature
		extractSHP(inBasePath + "HYDR/POINT_EAU.shp", outBasePath+"313_P_prominent_water_feature.shp", kirchbergEnv, CQL.toFilter( "NATURE = 0" ));

		//401_S_open_land
		extractSHP(inBaseOSMPath + "gis_osm_landuse_a_free_1_LUXPROJ.shp", outBasePath+"401_S_open_land.shp", kirchbergEnv, CQL.toFilter( "fclass = 'grass'" ));
		//SHPUtil.saveGeomsSHP(JTS.toGeometry(envClip), outBasePath+"401_S_open_land.shp"); //TODO complement of VEGE + others?
		//402_S_open_land_scattered_trees
		//402.1_S_open_land_scattered_trees
		extractSHP(inBasePath + "VEGE/VEGETATION_SURF.shp", outBasePath+"402.1_S_open_land_scattered_trees.shp", kirchbergEnv, CQL.toFilter( "NATURE = 5" ));
		//403_S_rough_open_land
		extractSHP(inBasePath + "BATI/TERR_SPORT.shp", outBasePath+"403_S_rough_open_land.shp", kirchbergEnv);
		//404_S_rough_open_land_scattered_trees
		//405_S_forest
		extractSHP(inBasePath + "VEGE/VEGETATION_SURF.shp", outBasePath+"405_S_forest.shp", kirchbergEnv, CQL.toFilter( "NATURE = 0 OR NATURE = 1 OR NATURE = 2" ));
		//406_S_Vegetation_slow
		//407_S_vegetation_slow_good_visibility
		//408_S_vegetation_walk
		extractSHP(inBasePath + "VEGE/VEGETATION_SURF.shp", outBasePath+"408_vegetation_walk.shp", kirchbergEnv, CQL.toFilter( "NATURE = 3" ));
		//409_S_vegetation_walk_good_visibility
		//410_S_vegetation_fight
		//411_S_vegetation_impassable
		//411.2_L_vegetation_impassable_minw
		extractSHP(inBasePath + "VEGE/VEGETATION_LIN.shp", outBasePath+"411.2_L_vegetation_impassable_minw.shp", kirchbergEnv, CQL.toFilter( "NATURE = 1 OR NATURE = 2" ));
		//412_S_cultivated_land
		extractSHP(inBaseOSMPath + "gis_osm_landuse_a_free_1_LUXPROJ.shp", outBasePath+"412_S_cultivated_land.shp", kirchbergEnv, CQL.toFilter( "fclass = 'farm'" ));
		//413_S_orchard
		extractSHP(inBasePath + "VEGE/VEGETATION_SURF.shp", outBasePath+"413_S_orchard.shp", kirchbergEnv, CQL.toFilter( "NATURE = 4" ));
		//414_S_vineyard_similar
		extractSHP(inBasePath + "VEGE/VEGETATION_SURF.shp", outBasePath+"414_S_vineyard_similar.shp", kirchbergEnv, CQL.toFilter( "NATURE = 6" ));
		//415_L_distinct_cultivation_boundary
		extractSHP(inBasePath + "VEGE/VEGETATION_LIN.shp", outBasePath+"415_L_distinct_cultivation_boundary.shp", kirchbergEnv, CQL.toFilter( "NATURE = 0" ));
		//416_L_distinct_vegetation_boundary
		extractSHP(inBasePath + "VEGE/VEGETATION_LIN.shp", outBasePath+"416_L_distinct_vegetation_boundary.shp", kirchbergEnv, CQL.toFilter( "NATURE = 3 OR NATURE = 4" ));

		//417_P_prominent_large_tree
		//418_P_prominent_bush_or_tree
		extractSHP(inBasePath + "VEGE/ARB_ISOLE.shp", outBasePath+"418_P_prominent_bush_or_tree.shp", kirchbergEnv);
		//419_P_prominent_vegetation_feature


		//501_S_paved_area_with_bn
		extractSHP(inBasePath + "BATI/PISTE_AERO.shp", outBasePath+"501_S_paved_area_with_bn.shp", kirchbergEnv);
		//501.1_S_paved_area
		extractSHP(inBasePath + "VCR/SURFACE_ROUTE.shp", outBasePath+"501.1_S_paved_area.shp", kirchbergEnv);
		extractSHP(inBasePath + "HYDR/ECLUSE.shp", outBasePath+"501.1_S_paved_area2.shp", kirchbergEnv);

		//502_L_wide_road
		extractSHP(inBasePath + "VCR/TRONCON_ROUTE.shp", outBasePath+"502_L_wide_road.shp", kirchbergEnv, CQL.toFilter( "(ETAT=0 OR ETAT=1) AND POSITION_S >= 0 AND TYPE = 0" ));
		//503_L_road
		//504_L_vehicle_track
		extractSHP(inBasePath + "VCR/TRONCON_ROUTE.shp", outBasePath+"504_L_vehicle_track.shp", kirchbergEnv, CQL.toFilter( "(ETAT=0 OR ETAT=1) AND POSITION_S >= 0 AND TYPE = 1" ));
		//505_L_footpath
		extractSHP(inBasePath + "VCR/TRONCON_ROUTE.shp", outBasePath+"505_L_footpath.shp", kirchbergEnv, CQL.toFilter( "(ETAT=0 OR ETAT=1) AND POSITION_S >= 0 AND TYPE = 2" ));
		//506_L_small_footpath
		extractSHP(inBasePath + "VCR/TRONCON_ROUTE.shp", outBasePath+"506_L_small_footpath.shp", kirchbergEnv, CQL.toFilter( "(ETAT=0 OR ETAT=1) AND POSITION_S >= 0 AND (TYPE = 3 OR TYPE = 4)" ));
		//507_L_less_distinct_small_footpath
		//508_L_narrow_ride_or_linear_trace

		//509_L_railway
		extractSHP(inBasePath + "VFTE/VOIE_FER.shp", outBasePath+"509_L_railway.shp", kirchbergEnv, CQL.toFilter( "POSITION_S >= 0" ));

		//510_L_power_line_cableway_skilift
		extractSHP(inBasePath + "VFTE/TRANS_CABL.shp", outBasePath+"510_L_power_line_cableway_skilift.shp", kirchbergEnv);
		//511_L_major_power_line
		extractSHP(inBasePath + "VFTE/LIGNE_ELEC.shp", outBasePath+"511_L_major_power_line.shp", kirchbergEnv);

		//512_L_bridge_tunnel
		extractSHP(inBasePath + "VCR/TUNNEL.shp", outBasePath+"512_L_bridge_tunnel.shp", kirchbergEnv);

		//513_L_wall
		//514_L_ruined_wall
		//515_L_impassable_wall
		extractSHP(inBasePath + "BATI/CONSTRUC_LINE.shp", outBasePath+"515_L_impassable_wall.shp", kirchbergEnv, CQL.toFilter( "NATURE = 4 OR NATURE = 5" ));
		extractSHP(inBasePath + "VCR/PARAPET.shp", outBasePath+"515_L_impassable_wall2.shp", kirchbergEnv);
		//516_L_fence
		//517_L_ruined_fence
		//518_L_impassable_fence
		extractSHP(inBasePath + "BATI/CONSTRUC_LINE.shp", outBasePath+"518_L_impassable_fence.shp", kirchbergEnv, CQL.toFilter( "NATURE = 6" ));
		//519,PO,crossing_point
		//520_S_area_shall_not_entered
		extractSHP(inBasePath + "BATI/CIMETIERE.shp", outBasePath+"520_S_area_shall_not_entered.shp", kirchbergEnv);
		extractSHP(inBaseOSMPath + "gis_osm_landuse_a_free_1_LUXPROJ.shp", outBasePath+"520_S_area_shall_not_entered2.shp", kirchbergEnv, CQL.toFilter( "fclass = 'residential'" ));
		//521_S_building
		extractSHP(inBasePath + "BATI/BATIMENT.shp", outBasePath+"521_S_building.shp", kirchbergEnv);
		extractSHP(inBasePath + "BATI/CONSTRUC_SURF.shp", outBasePath+"521_S_building2.shp", kirchbergEnv);
		//521.1_P_building_min
		extractSHP(inBasePath + "BATI/CONSTRUC_PONCT.shp", outBasePath+"521.1_P_building_min.shp", kirchbergEnv, CQL.toFilter( "NATURE = 9 OR NATURE = 10 OR NATURE = 11 OR NATURE = 13" ));
		//521.4_L_building_outline
		extractSHP(inBasePath + "BATI/LIM_TOIT.shp", outBasePath+"521.4_L_building_outline.shp", kirchbergEnv);
		//522_S_canopy
		//523_L_ruin
		extractSHP(inBasePath + "BATI/CONSTRUC_LINE.shp", outBasePath+"523_L_ruin.shp", kirchbergEnv, CQL.toFilter( "NATURE = 3" ));
		//524_P_high_tower
		extractSHP(inBasePath + "VFTE/PYLONE.shp", outBasePath+"524_P_high_tower.shp", kirchbergEnv);
		extractSHP(inBasePath + "BATI/CONSTRUC_PONCT.shp", outBasePath+"524_P_high_tower2.shp", kirchbergEnv, CQL.toFilter( "NATURE = 1" ));
		//525_P_small_tower
		//526_P_cairn
		extractSHP(inBasePath + "ADM/BORN_FRONT.shp", outBasePath+"526_P_cairn.shp", kirchbergEnv);
		extractSHP(inBasePath + "GEO/POINT_GEOD.shp", outBasePath+"526_P_cairn2.shp", kirchbergEnv);
		//527_P_fodder_rack
		//528_L_prominent_line_feature
		extractSHP(inBasePath + "BATI/CONSTRUC_LINE.shp", outBasePath+"528_L_prominent_line_feature.shp", kirchbergEnv, CQL.toFilter( "NATURE = 1 OR NATURE = 2" ));
		//529_L_prominent_impassable_line_feature
		//530_P_prominent_man_made_feature_ring
		extractSHP(inBasePath + "BATI/CONSTRUC_PONCT.shp", outBasePath+"530_P_prominent_man_made_feature_ring.shp", kirchbergEnv, CQL.toFilter( "NATURE = 2 OR NATURE = 3 OR NATURE = 4 OR NATURE = 5 OR NATURE = 6 OR NATURE = 7 OR NATURE = 8" ));
		//531_P_prominent_man_made_feature_x

		//601_L_magnetic_north_line
		//603.0_P_spot_height
		extractSHP(inBasePath + "ALTI/POINT_COTE.shp", outBasePath+"603.0_P_spot_height.shp", kirchbergEnv, CQL.toFilter( "ECHELLE = 1" ));

		System.out.println("end");
	}

	//clip and filter SHP
	public static void extractSHP(String in, String out, Envelope env) { extractSHP(in, out, env, null); }
	public static void extractSHP(String in, String out, Envelope env, Filter f) {
		SHPData fsd = SHPUtil.loadSHP(in, f);
		if(env != null) fsd.fs = FeatureUtil.clip(fsd.fs, env);
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
				//create SHP
			}
		}
		catch (FileNotFoundException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }


	}
	 */
}
