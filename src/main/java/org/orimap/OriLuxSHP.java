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

		Envelope envClip = new Envelope(77000, 80000, 75800, 78200);
		String inBasePath = "/home/juju/Bureau/orienteering/data/BDLTC_SHP/";
		String inBaseOSMPath = "/home/juju/Bureau/orienteering/data/osm/luxembourg-latest-free.shp/";
		String outBasePath = "/home/juju/Bureau/out/";

		//TODO open terrain
		//TODO BU: make diffs with OSM/BDT/CAD

		//101_L_contour
		clipSHP(inBasePath + "ALTI/COURBE.shp", outBasePath+"101_L_contour.shp", envClip, CQL.toFilter( "NATURE = 0 OR NATURE = 3" ));
		//102_L_index_contour
		clipSHP(inBasePath + "ALTI/COURBE.shp", outBasePath+"102_L_index_contour.shp", envClip, CQL.toFilter( "NATURE = 1" ));
		//103_L_form_line
		clipSHP(inBasePath + "ALTI/COURBE.shp", outBasePath+"103_L_form_line.shp", envClip, CQL.toFilter( "NATURE = 2" ));
		//104_L_earth_bank
		clipSHP(inBasePath + "ORO/LIGNE_ORO.shp", outBasePath+"104_L_earth_bank.shp", envClip, CQL.toFilter( "NATURE = 0 OR NATURE = 2" ));
		//105_L_earth_wall
		clipSHP(inBasePath + "ORO/LIGNE_ORO.shp", outBasePath+"105_L_earth_wall.shp", envClip, CQL.toFilter( "NATURE = 1" ));
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
		clipSHP(inBasePath + "HYDR/SURFACE_EAU.shp", outBasePath+"301_S_uncrossable_body_of_water.shp", envClip, CQL.toFilter( "NATURE = 0 OR NATURE = 3 OR NATURE = 4 OR NATURE = 5 OR NATURE = 6" ));
		//302_S_shallow_body_of_water
		clipSHP(inBasePath + "HYDR/SURFACE_EAU.shp", outBasePath+"302_S_shallow_body_of_water.shp", envClip, CQL.toFilter( "NATURE = 1" ));
		//303_P_waterhole
		//304_L_crossable_watercourse
		clipSHP(inBasePath + "HYDR/TRONCON_EAU.shp", outBasePath+"304_L_crossable_watercourse.shp", envClip, CQL.toFilter( "POSITION_S >= 0 AND TYPE = 0" ));
		//305_L_small_crossable_watercourse
		//306_L_minor_seasonal_water_channel
		clipSHP(inBasePath + "HYDR/TRONCON_EAU.shp", outBasePath+"306_L_minor_seasonal_water_channel.shp", envClip, CQL.toFilter( "POSITION_S >= 0 AND TYPE = 1" ));
		//307_S_uncrossable_marsh
		//308_S_marsh
		clipSHP(inBasePath + "HYDR/SURFACE_EAU.shp", outBasePath+"308_S_marsh.shp", envClip, CQL.toFilter( "NATURE = 2" ));
		//309_L_narrow_marsh
		//310_S_indistinct_marsh
		//311_P_well_fountain_water_tank
		clipSHP(inBasePath + "BATI/CONSTRUC_PONCT.shp", outBasePath+"311_P_well_fountain_water_tank.shp", envClip, CQL.toFilter( "NATURE = 12" ));
		clipSHP(inBasePath + "HYDR/POINT_EAU.shp", outBasePath+"311_P_well_fountain_water_tank2.shp", envClip, CQL.toFilter( "NATURE = 1 OR NATURE = 4" ));
		//312_P_spring
		clipSHP(inBasePath + "HYDR/POINT_EAU.shp", outBasePath+"312_P_spring.shp", envClip, CQL.toFilter( "NATURE = 2 OR NATURE = 3" ));
		//313_P_prominent_water_feature
		clipSHP(inBasePath + "HYDR/POINT_EAU.shp", outBasePath+"313_P_prominent_water_feature.shp", envClip, CQL.toFilter( "NATURE = 0" ));

		//401_S_open_land
		clipSHP(inBaseOSMPath + "gis_osm_landuse_a_free_1_LUXPROJ.shp", outBasePath+"401_S_open_land.shp", envClip, CQL.toFilter( "fclass = 'grass'" ));
		//SHPUtil.saveGeomsSHP(JTS.toGeometry(envClip), outBasePath+"401_S_open_land.shp"); //TODO complement of VEGE + others?
		//402_S_open_land_scattered_trees
		//402.1_S_open_land_scattered_trees
		clipSHP(inBasePath + "VEGE/VEGETATION_SURF.shp", outBasePath+"402.1_S_open_land_scattered_trees.shp", envClip, CQL.toFilter( "NATURE = 5" ));
		//403_S_rough_open_land
		clipSHP(inBasePath + "BATI/TERR_SPORT.shp", outBasePath+"403_S_rough_open_land.shp", envClip);
		//404_S_rough_open_land_scattered_trees
		//405_S_forest
		clipSHP(inBasePath + "VEGE/VEGETATION_SURF.shp", outBasePath+"405_S_forest.shp", envClip, CQL.toFilter( "NATURE = 0 OR NATURE = 1 OR NATURE = 2" ));
		//406_S_Vegetation_slow
		//407_S_vegetation_slow_good_visibility
		//408_S_vegetation_walk
		clipSHP(inBasePath + "VEGE/VEGETATION_SURF.shp", outBasePath+"408_vegetation_walk.shp", envClip, CQL.toFilter( "NATURE = 3" ));
		//409_S_vegetation_walk_good_visibility
		//410_S_vegetation_fight
		//411_S_vegetation_impassable
		//411.2_L_vegetation_impassable_minw
		clipSHP(inBasePath + "VEGE/VEGETATION_LIN.shp", outBasePath+"411.2_L_vegetation_impassable_minw.shp", envClip, CQL.toFilter( "NATURE = 1 OR NATURE = 2" ));
		//412_S_cultivated_land
		clipSHP(inBaseOSMPath + "gis_osm_landuse_a_free_1_LUXPROJ.shp", outBasePath+"412_S_cultivated_land.shp", envClip, CQL.toFilter( "fclass = 'farm'" ));
		//413_S_orchard
		clipSHP(inBasePath + "VEGE/VEGETATION_SURF.shp", outBasePath+"413_S_orchard.shp", envClip, CQL.toFilter( "NATURE = 4" ));
		//414_S_vineyard_similar
		clipSHP(inBasePath + "VEGE/VEGETATION_SURF.shp", outBasePath+"414_S_vineyard_similar.shp", envClip, CQL.toFilter( "NATURE = 6" ));
		//415_L_distinct_cultivation_boundary
		clipSHP(inBasePath + "VEGE/VEGETATION_LIN.shp", outBasePath+"415_L_distinct_cultivation_boundary.shp", envClip, CQL.toFilter( "NATURE = 0" ));
		//416_L_distinct_vegetation_boundary
		clipSHP(inBasePath + "VEGE/VEGETATION_LIN.shp", outBasePath+"416_L_distinct_vegetation_boundary.shp", envClip, CQL.toFilter( "NATURE = 3 OR NATURE = 4" ));

		//417_P_prominent_large_tree
		//418_P_prominent_bush_or_tree
		clipSHP(inBasePath + "VEGE/ARB_ISOLE.shp", outBasePath+"418_P_prominent_bush_or_tree.shp", envClip);
		//419_P_prominent_vegetation_feature


		//501_S_paved_area_with_bn
		clipSHP(inBasePath + "BATI/PISTE_AERO.shp", outBasePath+"501_S_paved_area_with_bn.shp", envClip);
		//501.1_S_paved_area
		clipSHP(inBasePath + "VCR/SURFACE_ROUTE.shp", outBasePath+"501.1_S_paved_area.shp", envClip);
		clipSHP(inBasePath + "HYDR/ECLUSE.shp", outBasePath+"501.1_S_paved_area2.shp", envClip);

		//502_L_wide_road
		clipSHP(inBasePath + "VCR/TRONCON_ROUTE.shp", outBasePath+"502_L_wide_road.shp", envClip, CQL.toFilter( "(ETAT=0 OR ETAT=1) AND POSITION_S >= 0 AND TYPE = 0" ));
		//503_L_road
		//504_L_vehicle_track
		clipSHP(inBasePath + "VCR/TRONCON_ROUTE.shp", outBasePath+"504_L_vehicle_track.shp", envClip, CQL.toFilter( "(ETAT=0 OR ETAT=1) AND POSITION_S >= 0 AND TYPE = 1" ));
		//505_L_footpath
		clipSHP(inBasePath + "VCR/TRONCON_ROUTE.shp", outBasePath+"505_L_footpath.shp", envClip, CQL.toFilter( "(ETAT=0 OR ETAT=1) AND POSITION_S >= 0 AND TYPE = 2" ));
		//506_L_small_footpath
		clipSHP(inBasePath + "VCR/TRONCON_ROUTE.shp", outBasePath+"506_L_small_footpath.shp", envClip, CQL.toFilter( "(ETAT=0 OR ETAT=1) AND POSITION_S >= 0 AND (TYPE = 3 OR TYPE = 4)" ));
		//507_L_less_distinct_small_footpath
		//508_L_narrow_ride_or_linear_trace

		//509_L_railway
		clipSHP(inBasePath + "VFTE/VOIE_FER.shp", outBasePath+"509_L_railway.shp", envClip, CQL.toFilter( "POSITION_S >= 0" ));

		//510_L_power_line_cableway_skilift
		clipSHP(inBasePath + "VFTE/TRANS_CABL.shp", outBasePath+"510_L_power_line_cableway_skilift.shp", envClip);
		//511_L_major_power_line
		clipSHP(inBasePath + "VFTE/LIGNE_ELEC.shp", outBasePath+"511_L_major_power_line.shp", envClip);

		//512_L_bridge_tunnel
		clipSHP(inBasePath + "VCR/TUNNEL.shp", outBasePath+"512_L_bridge_tunnel.shp", envClip);

		//513_L_wall
		//514_L_ruined_wall
		//515_L_impassable_wall
		clipSHP(inBasePath + "BATI/CONSTRUC_LINE.shp", outBasePath+"515_L_impassable_wall.shp", envClip, CQL.toFilter( "NATURE = 4 OR NATURE = 5" ));
		clipSHP(inBasePath + "VCR/PARAPET.shp", outBasePath+"515_L_impassable_wall2.shp", envClip);
		//516_L_fence
		//517_L_ruined_fence
		//518_L_impassable_fence
		clipSHP(inBasePath + "BATI/CONSTRUC_LINE.shp", outBasePath+"518_L_impassable_fence.shp", envClip, CQL.toFilter( "NATURE = 6" ));
		//519,PO,crossing_point
		//520_S_area_shall_not_entered
		clipSHP(inBasePath + "BATI/CIMETIERE.shp", outBasePath+"520_S_area_shall_not_entered.shp", envClip);
		clipSHP(inBaseOSMPath + "gis_osm_landuse_a_free_1_LUXPROJ.shp", outBasePath+"520_S_area_shall_not_entered2.shp", envClip, CQL.toFilter( "fclass = 'residential'" ));
		//521_S_building
		clipSHP(inBasePath + "BATI/BATIMENT.shp", outBasePath+"521_S_building.shp", envClip);
		clipSHP(inBasePath + "BATI/CONSTRUC_SURF.shp", outBasePath+"521_S_building2.shp", envClip);
		//521.1_P_building_min
		clipSHP(inBasePath + "BATI/CONSTRUC_PONCT.shp", outBasePath+"521.1_P_building_min.shp", envClip, CQL.toFilter( "NATURE = 9 OR NATURE = 10 OR NATURE = 11 OR NATURE = 13" ));
		//521.4_L_building_outline
		clipSHP(inBasePath + "BATI/LIM_TOIT.shp", outBasePath+"521.4_L_building_outline.shp", envClip);
		//522_S_canopy
		//523_L_ruin
		clipSHP(inBasePath + "BATI/CONSTRUC_LINE.shp", outBasePath+"523_L_ruin.shp", envClip, CQL.toFilter( "NATURE = 3" ));
		//524_P_high_tower
		clipSHP(inBasePath + "VFTE/PYLONE.shp", outBasePath+"524_P_high_tower.shp", envClip);
		clipSHP(inBasePath + "BATI/CONSTRUC_PONCT.shp", outBasePath+"524_P_high_tower2.shp", envClip, CQL.toFilter( "NATURE = 1" ));
		//525_P_small_tower
		//526_P_cairn
		clipSHP(inBasePath + "ADM/BORN_FRONT.shp", outBasePath+"526_P_cairn.shp", envClip);
		clipSHP(inBasePath + "GEO/POINT_GEOD.shp", outBasePath+"526_P_cairn2.shp", envClip);
		//527_P_fodder_rack
		//528_L_prominent_line_feature
		clipSHP(inBasePath + "BATI/CONSTRUC_LINE.shp", outBasePath+"528_L_prominent_line_feature.shp", envClip, CQL.toFilter( "NATURE = 1 OR NATURE = 2" ));
		//529_L_prominent_impassable_line_feature
		//530_P_prominent_man_made_feature_ring
		clipSHP(inBasePath + "BATI/CONSTRUC_PONCT.shp", outBasePath+"530_P_prominent_man_made_feature_ring.shp", envClip, CQL.toFilter( "NATURE = 2 OR NATURE = 3 OR NATURE = 4 OR NATURE = 5 OR NATURE = 6 OR NATURE = 7 OR NATURE = 8" ));
		//531_P_prominent_man_made_feature_x

		//601_L_magnetic_north_line
		//603.0_P_spot_height
		clipSHP(inBasePath + "ALTI/POINT_COTE.shp", outBasePath+"603.0_P_spot_height.shp", envClip, CQL.toFilter( "ECHELLE = 1" ));

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
				//create SHP
			}
		}
		catch (FileNotFoundException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }


	}
	 */
}
