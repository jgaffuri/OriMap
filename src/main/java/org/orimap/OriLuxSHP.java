/**
 * 
 */
package org.orimap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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

		//TODO finalise OSM to ori
		//TODO make qgis style for ori schema (...)
		//TODO get /home/juju/Bureau/orienteering/omap_luxembourg_shp/shp/ into ori comp

		String basePathBDT = "/home/juju/Bureau/orienteering/data/BDLTC_SHP/";
		String basePathOSM = "/home/juju/Bureau/orienteering/data/OSM/luxembourg-latest-free.shp/";
		String basePathCadastre = "/home/juju/Bureau/orienteering/data/pcn-cadastre/";

		String basePathOriBDT = "/home/juju/Bureau/orienteering/data/ori_BDT/";
		String basePathOriOSM = "/home/juju/Bureau/orienteering/data/ori_OSM/";
		String basePathOriCadastre = "/home/juju/Bureau/orienteering/data/ori_cadastre/";


		//System.out.println("BDT to ori");
		//extractBDTToOri(basePathBDT, basePathOriBDT);
		System.out.println("OSM to ori");
		extractOSMToOri(basePathOSM, basePathOriOSM);
		//System.out.println("cadastre to ori");
		//extractCadastreToOri(basePathCadastre, basePathOriCadastre);

		//Envelope kirchbergEnv = new Envelope(77000, 80000, 75800, 78200);

		System.out.println("End");
	}

	public static void extractBDTToOri(String inBasePath, String outBasePath) throws Exception {

		//101_L_contour
		extractSHP(inBasePath + "ALTI/COURBE.shp", outBasePath+"101_L_contour.shp", null, CQL.toFilter( "NATURE = 0 OR NATURE = 3" ));
		//102_L_index_contour
		extractSHP(inBasePath + "ALTI/COURBE.shp", outBasePath+"102_L_index_contour.shp", null, CQL.toFilter( "NATURE = 1" ));
		//103_L_form_line
		extractSHP(inBasePath + "ALTI/COURBE.shp", outBasePath+"103_L_form_line.shp", null, CQL.toFilter( "NATURE = 2" ));
		//104_L_earth_bank
		extractSHP(inBasePath + "ORO/LIGNE_ORO.shp", outBasePath+"104_L_earth_bank.shp", null, CQL.toFilter( "NATURE = 0 OR NATURE = 2" ));
		//105_L_earth_wall
		extractSHP(inBasePath + "ORO/LIGNE_ORO.shp", outBasePath+"105_L_earth_wall.shp", null, CQL.toFilter( "NATURE = 1" ));
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
		extractSHP(inBasePath + "HYDR/SURFACE_EAU.shp", outBasePath+"301_S_uncrossable_body_of_water.shp", null, CQL.toFilter( "NATURE = 0 OR NATURE = 3 OR NATURE = 4 OR NATURE = 5 OR NATURE = 6" ));
		//302_S_shallow_body_of_water
		extractSHP(inBasePath + "HYDR/SURFACE_EAU.shp", outBasePath+"302_S_shallow_body_of_water.shp", null, CQL.toFilter( "NATURE = 1" ));
		//303_P_waterhole
		//304_L_crossable_watercourse
		extractSHP(inBasePath + "HYDR/TRONCON_EAU.shp", outBasePath+"304_L_crossable_watercourse.shp", null, CQL.toFilter( "POSITION_S >= 0 AND TYPE = 0" ));
		//305_L_small_crossable_watercourse
		//306_L_minor_seasonal_water_channel
		extractSHP(inBasePath + "HYDR/TRONCON_EAU.shp", outBasePath+"306_L_minor_seasonal_water_channel.shp", null, CQL.toFilter( "POSITION_S >= 0 AND TYPE = 1" ));
		//307_S_uncrossable_marsh
		//308_S_marsh
		extractSHP(inBasePath + "HYDR/SURFACE_EAU.shp", outBasePath+"308_S_marsh.shp", null, CQL.toFilter( "NATURE = 2" ));
		//309_L_narrow_marsh
		//310_S_indistinct_marsh
		//311_P_well_fountain_water_tank
		extractSHP(inBasePath + "BATI/CONSTRUC_PONCT.shp", outBasePath+"311_P_well_fountain_water_tank.shp", null, CQL.toFilter( "NATURE = 12" ));
		extractSHP(inBasePath + "HYDR/POINT_EAU.shp", outBasePath+"311_P_well_fountain_water_tank2.shp", null, CQL.toFilter( "NATURE = 1 OR NATURE = 4" ));
		//312_P_spring
		extractSHP(inBasePath + "HYDR/POINT_EAU.shp", outBasePath+"312_P_spring.shp", null, CQL.toFilter( "NATURE = 2 OR NATURE = 3" ));
		//313_P_prominent_water_feature
		extractSHP(inBasePath + "HYDR/POINT_EAU.shp", outBasePath+"313_P_prominent_water_feature.shp", null, CQL.toFilter( "NATURE = 0" ));

		//401_S_open_land
		//402_S_open_land_scattered_trees
		//402.1_S_open_land_scattered_trees
		extractSHP(inBasePath + "VEGE/VEGETATION_SURF.shp", outBasePath+"402.1_S_open_land_scattered_trees.shp", null, CQL.toFilter( "NATURE = 5" ));
		//403_S_rough_open_land
		extractSHP(inBasePath + "BATI/TERR_SPORT.shp", outBasePath+"403_S_rough_open_land.shp");
		//404_S_rough_open_land_scattered_trees
		//405_S_forest
		extractSHP(inBasePath + "VEGE/VEGETATION_SURF.shp", outBasePath+"405_S_forest.shp", null, CQL.toFilter( "NATURE = 0 OR NATURE = 1 OR NATURE = 2" ));
		//406_S_vegetation_slow
		//407_S_vegetation_slow_good_visibility
		//408_S_vegetation_walk
		extractSHP(inBasePath + "VEGE/VEGETATION_SURF.shp", outBasePath+"408_vegetation_walk.shp", null, CQL.toFilter( "NATURE = 3" ));
		//409_S_vegetation_walk_good_visibility
		//410_S_vegetation_fight
		//411_S_vegetation_impassable
		//411.2_L_vegetation_impassable_minw
		extractSHP(inBasePath + "VEGE/VEGETATION_LIN.shp", outBasePath+"411.2_L_vegetation_impassable_minw.shp", null, CQL.toFilter( "NATURE = 1 OR NATURE = 2" ));
		//412_S_cultivated_land
		//413_S_orchard
		extractSHP(inBasePath + "VEGE/VEGETATION_SURF.shp", outBasePath+"413_S_orchard.shp", null, CQL.toFilter( "NATURE = 4" ));
		//414_S_vineyard_similar
		extractSHP(inBasePath + "VEGE/VEGETATION_SURF.shp", outBasePath+"414_S_vineyard_similar.shp", null, CQL.toFilter( "NATURE = 6" ));
		//415_L_distinct_cultivation_boundary
		extractSHP(inBasePath + "VEGE/VEGETATION_LIN.shp", outBasePath+"415_L_distinct_cultivation_boundary.shp", null, CQL.toFilter( "NATURE = 0" ));
		//416_L_distinct_vegetation_boundary
		extractSHP(inBasePath + "VEGE/VEGETATION_LIN.shp", outBasePath+"416_L_distinct_vegetation_boundary.shp", null, CQL.toFilter( "NATURE = 3 OR NATURE = 4" ));

		//417_P_prominent_large_tree
		//418_P_prominent_bush_or_tree
		extractSHP(inBasePath + "VEGE/ARB_ISOLE.shp", outBasePath+"418_P_prominent_bush_or_tree.shp");
		//419_P_prominent_vegetation_feature


		//501_S_paved_area_with_bn
		extractSHP(inBasePath + "BATI/PISTE_AERO.shp", outBasePath+"501_S_paved_area_with_bn.shp");
		//501.1_S_paved_area
		extractSHP(inBasePath + "VCR/SURFACE_ROUTE.shp", outBasePath+"501.1_S_paved_area.shp");
		extractSHP(inBasePath + "HYDR/ECLUSE.shp", outBasePath+"501.1_S_paved_area2.shp");

		//502_L_wide_road
		extractSHP(inBasePath + "VCR/TRONCON_ROUTE.shp", outBasePath+"502_L_wide_road.shp", null, CQL.toFilter( "(ETAT=0 OR ETAT=1) AND POSITION_S >= 0 AND TYPE = 0" ));
		//503_L_road
		//504_L_vehicle_track
		extractSHP(inBasePath + "VCR/TRONCON_ROUTE.shp", outBasePath+"504_L_vehicle_track.shp", null, CQL.toFilter( "(ETAT=0 OR ETAT=1) AND POSITION_S >= 0 AND TYPE = 1" ));
		//505_L_footpath
		extractSHP(inBasePath + "VCR/TRONCON_ROUTE.shp", outBasePath+"505_L_footpath.shp", null, CQL.toFilter( "(ETAT=0 OR ETAT=1) AND POSITION_S >= 0 AND TYPE = 2" ));
		//506_L_small_footpath
		extractSHP(inBasePath + "VCR/TRONCON_ROUTE.shp", outBasePath+"506_L_small_footpath.shp", null, CQL.toFilter( "(ETAT=0 OR ETAT=1) AND POSITION_S >= 0 AND (TYPE = 3 OR TYPE = 4)" ));
		//507_L_less_distinct_small_footpath
		//508_L_narrow_ride_or_linear_trace

		//509_L_railway
		extractSHP(inBasePath + "VFTE/VOIE_FER.shp", outBasePath+"509_L_railway.shp", null, CQL.toFilter( "POSITION_S >= 0" ));

		//510_L_power_line_cableway_skilift
		extractSHP(inBasePath + "VFTE/TRANS_CABL.shp", outBasePath+"510_L_power_line_cableway_skilift.shp");
		//511_L_major_power_line
		extractSHP(inBasePath + "VFTE/LIGNE_ELEC.shp", outBasePath+"511_L_major_power_line.shp");

		//512_L_bridge_tunnel
		extractSHP(inBasePath + "VCR/TUNNEL.shp", outBasePath+"512_L_bridge_tunnel.shp");

		//513_L_wall
		//514_L_ruined_wall
		//515_L_impassable_wall
		extractSHP(inBasePath + "BATI/CONSTRUC_LINE.shp", outBasePath+"515_L_impassable_wall.shp", null, CQL.toFilter( "NATURE = 4 OR NATURE = 5" ));
		extractSHP(inBasePath + "VCR/PARAPET.shp", outBasePath+"515_L_impassable_wall2.shp");
		//516_L_fence
		//517_L_ruined_fence
		//518_L_impassable_fence
		extractSHP(inBasePath + "BATI/CONSTRUC_LINE.shp", outBasePath+"518_L_impassable_fence.shp", null, CQL.toFilter( "NATURE = 6" ));
		//519,PO,crossing_point
		//520_S_area_shall_not_entered
		extractSHP(inBasePath + "BATI/CIMETIERE.shp", outBasePath+"520_S_area_shall_not_entered.shp");
		//521_S_building
		extractSHP(inBasePath + "BATI/BATIMENT.shp", outBasePath+"521_S_building.shp");
		extractSHP(inBasePath + "BATI/CONSTRUC_SURF.shp", outBasePath+"521_S_building2.shp");
		//521.1_P_building_min
		extractSHP(inBasePath + "BATI/CONSTRUC_PONCT.shp", outBasePath+"521.1_P_building_min.shp", null, CQL.toFilter( "NATURE = 9 OR NATURE = 10 OR NATURE = 11 OR NATURE = 13" ));
		//521.4_L_building_outline
		extractSHP(inBasePath + "BATI/LIM_TOIT.shp", outBasePath+"521.4_L_building_outline.shp");
		//522_S_canopy
		//523_L_ruin
		extractSHP(inBasePath + "BATI/CONSTRUC_LINE.shp", outBasePath+"523_L_ruin.shp", null, CQL.toFilter( "NATURE = 3" ));
		//524_P_high_tower
		extractSHP(inBasePath + "VFTE/PYLONE.shp", outBasePath+"524_P_high_tower.shp");
		extractSHP(inBasePath + "BATI/CONSTRUC_PONCT.shp", outBasePath+"524_P_high_tower2.shp", null, CQL.toFilter( "NATURE = 1" ));
		//525_P_small_tower
		//526_P_cairn
		extractSHP(inBasePath + "ADM/BORN_FRONT.shp", outBasePath+"526_P_cairn.shp");
		extractSHP(inBasePath + "GEO/POINT_GEOD.shp", outBasePath+"526_P_cairn2.shp");
		//527_P_fodder_rack
		//528_L_prominent_line_feature
		extractSHP(inBasePath + "BATI/CONSTRUC_LINE.shp", outBasePath+"528_L_prominent_line_feature.shp", null, CQL.toFilter( "NATURE = 1 OR NATURE = 2" ));
		//529_L_prominent_impassable_line_feature
		//530_P_prominent_man_made_feature_o
		extractSHP(inBasePath + "BATI/CONSTRUC_PONCT.shp", outBasePath+"530_P_prominent_man_made_feature_o.shp", null, CQL.toFilter( "NATURE = 2 OR NATURE = 3 OR NATURE = 4 OR NATURE = 5 OR NATURE = 6 OR NATURE = 7 OR NATURE = 8" ));
		//531_P_prominent_man_made_feature_x

		//601_L_magnetic_north_line
		//603.0_P_spot_height
		extractSHP(inBasePath + "ALTI/POINT_COTE.shp", outBasePath+"603.0_P_spot_height.shp", null, CQL.toFilter( "ECHELLE = 1" ));

	}

	public static void extractOSMToOri(String inBasePath, String outBasePath) throws Exception {
		//TODO complete

		/*
extractSHP(inBasePath + "gis_osm_roads_free_1_LUXPROJ.shp", outBasePath+".shp", null, CQL.toFilter( "fclass = ''" ));
att: bridge - tunnel
*** revers ing
		*/

		//101_L_contour
		//102_L_index_contour
		//103_L_form_line
		//104_L_earth_bank
		//105_L_earth_wall
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
		//201.1_P_impassable_cliff_ms
		extractSHP(inBasePath + "gis_osm_natural_free_1_LUXPROJ.shp", outBasePath+"201.1_P_impassable_cliff_ms.shp", null, CQL.toFilter( "fclass = 'cliff'" ));
		//201.2_S_impassable_cliff
		extractSHP(inBasePath + "gis_osm_natural_a_free_1_LUXPROJ.shp", outBasePath+"201.2_S_impassable_cliff.shp", null, CQL.toFilter( "fclass = 'cliff'" ));
		//202_L_cliff
		//203_P_rocky_pit_cave
		extractSHP(inBasePath + "gis_osm_natural_free_1_LUXPROJ.shp", outBasePath+"203_P_rocky_pit_cave.shp", null, CQL.toFilter( "fclass = 'cave_entrance'" ));
		extractSHP(inBasePath + "gis_osm_natural_free_1_LUXPROJ.shp", outBasePath+"203_P_rocky_pit_cave2.shp", null, CQL.toFilter( "fclass = 'peak'" ));
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
		extractSHP(inBasePath + "gis_osm_natural_a_free_1_LUXPROJ.shp", outBasePath+".shp", null, CQL.toFilter( "fclass = 'beach'" ));
		//214_S_bare_rock
		//215_L_trench

		//301_S_uncrossable_body_of_water
		extractSHP(inBasePath + "gis_osm_water_a_free_1_LUXPROJ.shp", outBasePath+"301_S_uncrossable_body_of_water.shp", null, CQL.toFilter( "fclass = 'water' OR fclass = 'river' OR fclass = 'reservoir'" ));
		//302_S_shallow_body_of_water
		//303_P_waterhole
		//304_L_crossable_watercourse
		extractSHP(inBasePath + "gis_osm_waterways_free_1_LUXPROJ.shp", outBasePath+"304_L_crossable_watercourse.shp", null, CQL.toFilter( "fclass = 'river' OR fclass = 'canal'" ));
		//305_L_small_crossable_watercourse
		extractSHP(inBasePath + "gis_osm_waterways_free_1_LUXPROJ.shp", outBasePath+"305_L_small_crossable_watercourse.shp", null, CQL.toFilter( "fclass = 'stream'" ));
		//306_L_minor_seasonal_water_channel
		//307_S_uncrossable_marsh
		//308_S_marsh
		extractSHP(inBasePath + "gis_osm_water_a_free_1_LUXPROJ.shp", outBasePath+"308_S_marsh.shp", null, CQL.toFilter( "fclass = 'wetland'" ));
		//309_L_narrow_marsh
		//310_S_indistinct_marsh
		//311_P_well_fountain_water_tank
		//312_P_spring
		extractSHP(inBasePath + "gis_osm_natural_free_1_LUXPROJ.shp", outBasePath+"312_P_spring.shp", null, CQL.toFilter( "fclass = 'spring'" ));
		//313_P_prominent_water_feature

		//401_S_open_land
		//grass,meadow,recreation_ground,(park)
		extractSHP(inBasePath + "gis_osm_landuse_a_free_1_LUXPROJ.shp", outBasePath+"401_S_open_land.shp", null, CQL.toFilter( "fclass = 'grass' OR fclass = 'meadow' OR fclass = 'recreation_ground'" ));
		//402_S_open_land_scattered_trees
		//402.1_S_open_land_scattered_trees
		//403_S_rough_open_land
		//404_S_rough_open_land_scattered_trees
		//405_S_forest
		//406_S_vegetation_slow
		//forest
		extractSHP(inBasePath + "gis_osm_landuse_a_free_1_LUXPROJ.shp", outBasePath+"406_S_vegetation_slow.shp", null, CQL.toFilter( "fclass = 'forest'" ));
		//407_S_vegetation_slow_good_visibility
		//scrub
		extractSHP(inBasePath + "gis_osm_landuse_a_free_1_LUXPROJ.shp", outBasePath+"407_S_vegetation_slow_good_visibility.shp", null, CQL.toFilter( "fclass = 'scrub'" ));
		//408_S_vegetation_walk
		//409_S_vegetation_walk_good_visibility
		//410_S_vegetation_fight
		//411_S_vegetation_impassable
		//411.2_L_vegetation_impassable_minw
		//412_S_cultivated_land
		//farm
		extractSHP(inBasePath + "gis_osm_landuse_a_free_1_LUXPROJ.shp", outBasePath+"412_S_cultivated_land.shp", null, CQL.toFilter( "fclass = 'farm'" ));
		//413_S_orchard
		//orchard
		extractSHP(inBasePath + "gis_osm_landuse_a_free_1_LUXPROJ.shp", outBasePath+"413_S_orchard.shp", null, CQL.toFilter( "fclass = 'orchard'" ));
		//414_S_vineyard_similar
		//vineyard
		extractSHP(inBasePath + "gis_osm_landuse_a_free_1_LUXPROJ.shp", outBasePath+"414_S_vineyard_similar.shp", null, CQL.toFilter( "fclass = 'vineyard'" ));
		//415_L_distinct_cultivation_boundary
		//416_L_distinct_vegetation_boundary

		//417_P_prominent_large_tree
		//418_P_prominent_bush_or_tree
		extractSHP(inBasePath + "gis_osm_natural_free_1_LUXPROJ.shp", outBasePath+"418_P_prominent_bush_or_tree.shp", null, CQL.toFilter( "fclass = 'tree'" ));
		//419_P_prominent_vegetation_feature

		//501_S_paved_area_with_bn
		//501.1_S_paved_area

		//502_L_wide_road
		//503_L_road
		//504_L_vehicle_track
		//505_L_footpath
		//506_L_small_footpath
		//507_L_less_distinct_small_footpath
		//508_L_narrow_ride_or_linear_trace

		//509_L_railway
		extractSHP(inBasePath + "gis_osm_railways_free_1_LUXPROJ.shp", outBasePath+"509_L_railway.shp", null, CQL.toFilter( "fclass = 'rail' OR fclass = 'tram' OR fclass = 'funicular' OR fclass = 'miniature_railway' OR fclass = 'narrow_gauge'" ));

		//510_L_power_line_cableway_skilift
		//511_L_major_power_line

		//512_L_bridge_tunnel

		//513_L_wall
		//514_L_ruined_wall
		//515_L_impassable_wall
		//516_L_fence
		//517_L_ruined_fence
		//518_L_impassable_fence
		//519,PO,crossing_point
		//520_S_area_shall_not_entered
		//private: residential,allotments,cemetery,commercial,health,industrial,military,quarry
		extractSHP(inBasePath + "gis_osm_landuse_a_free_1_LUXPROJ.shp", outBasePath+"520_S_area_shall_not_entered.shp", null, CQL.toFilter( "fclass = 'residential' OR fclass = 'allotments' OR fclass = 'cemetery' OR fclass = 'commercial' OR fclass = 'health' OR fclass = 'industrial' OR fclass = 'military' OR fclass = 'quarry'" ));
		//521_S_building
		extractSHP(inBasePath + "gis_osm_buildings_a_free_1_LUXPROJ.shp", outBasePath+"521_S_building.shp", null, CQL.toFilter( "NOT(type = 'roof' OR type = 'carport' OR type = 'tent') AND NOT (type = 'ruins' OR type = 'collapsed')" ));
		//521.1_P_building_min
		//521.4_L_building_outline
		//522_S_canopy
		extractSHP(inBasePath + "gis_osm_buildings_a_free_1_LUXPROJ.shp", outBasePath+"522_S_canopy.shp", null, CQL.toFilter( "type = 'roof' OR type = 'carport' OR type = 'tent'" ));
		//523_L_ruin
		extractSHP(inBasePath + "gis_osm_buildings_a_free_1_LUXPROJ.shp", outBasePath+"523_L_ruin.shp", null, CQL.toFilter( "type = 'ruins' OR type = 'collapsed'" ));
		//524_P_high_tower
		//525_P_small_tower
		//526_P_cairn
		//527_P_fodder_rack
		//528_L_prominent_line_feature
		//529_L_prominent_impassable_line_feature
		//530_P_prominent_man_made_feature_o
		//531_P_prominent_man_made_feature_x

		//601_L_magnetic_north_line
		//603.0_P_spot_height
	}

	public static void extractCadastreToOri(String inBasePath, String outBasePath) throws Exception {
		//ignored for parcelles: 5048	point géodésique

		//101_L_contour
		//102_L_index_contour
		//103_L_form_line
		//104_L_earth_bank
		//105_L_earth_wall
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
		//5050	rochers
		extractSHP(inBasePath + "PARCELLES.shp", outBasePath+"206_S_gigantic_boulder.shp", null, CQL.toFilter( "CODE_NATUR = 5050" ) );
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
		//5000	rivière
		//5001	ruisseau
		//5002	canal
		//5003	étang
		//5005	bassin
		//5006	surface hydrographique
		extractSHP(inBasePath + "PARCELLES.shp", outBasePath+"301_S_uncrossable_body_of_water.shp", null, CQL.toFilter( "CODE_NATUR = 5000 OR CODE_NATUR = 5001 OR CODE_NATUR = 5002 OR CODE_NATUR = 5003 OR CODE_NATUR = 5005 OR CODE_NATUR = 5006" ) );
		//302_S_shallow_body_of_water
		//303_P_waterhole
		//304_L_crossable_watercourse
		//305_L_small_crossable_watercourse
		//306_L_minor_seasonal_water_channel
		//307_S_uncrossable_marsh
		//308_S_marsh
		//5004	zone humide
		extractSHP(inBasePath + "PARCELLES.shp", outBasePath+"308_S_marsh.shp", null, CQL.toFilter( "CODE_NATUR = 5004" ) );
		//309_L_narrow_marsh
		//310_S_indistinct_marsh
		//311_P_well_fountain_water_tank
		//312_P_spring
		//313_P_prominent_water_feature

		//401_S_open_land
		//5008	pâture
		//5009	pré
		//5010	vaine
		extractSHP(inBasePath + "PARCELLES.shp", outBasePath+"401_S_open_land.shp", null, CQL.toFilter( "CODE_NATUR = 5008 OR CODE_NATUR = 5009 OR CODE_NATUR = 5010" ) );
		//402_S_open_land_scattered_trees
		//402.1_S_open_land_scattered_trees
		//403_S_rough_open_land
		/*5040	sentier
		5037	chemin repris
		5038	chemin d'exploitation
		5027	parc
		5013	jardin
		5028	place verte
		5033	terrain des sports
		5030	aire de loisirs
		5049	site archéologique
		5021	carrière
		5022	crassier
		5051	nature multiple*/
		extractSHP(inBasePath + "PARCELLES.shp", outBasePath+"403_S_rough_open_land.shp", null, CQL.toFilter( "CODE_NATUR = 5040 OR CODE_NATUR = 5037 OR CODE_NATUR = 5038 OR CODE_NATUR = 5027 OR CODE_NATUR = 5013 OR CODE_NATUR = 5028 OR CODE_NATUR = 5033 OR CODE_NATUR = 5030 OR CODE_NATUR = 5049 OR CODE_NATUR = 5021 OR CODE_NATUR = 5022 OR CODE_NATUR = 5051" ) );
		//404_S_rough_open_land_scattered_trees
		//405_S_forest
		//5017	bois
		extractSHP(inBasePath + "PARCELLES.shp", outBasePath+"405_S_forest.shp", null, CQL.toFilter( "CODE_NATUR = 5017" ) );
		//406_S_vegetation_slow
		//407_S_vegetation_slow_good_visibility
		//408_S_vegetation_walk
		/*5052	bande écologique
		5053	réserve naturelle
		5019	haie
		5020	plantation*/
		extractSHP(inBasePath + "PARCELLES.shp", outBasePath+"408_S_vegetation_walk.shp", null, CQL.toFilter( "CODE_NATUR = 5052 OR CODE_NATUR = 5053 OR CODE_NATUR = 5019 OR CODE_NATUR = 5020" ) );
		//409_S_vegetation_walk_good_visibility
		//5018	broussailles
		extractSHP(inBasePath + "PARCELLES.shp", outBasePath+"409_S_vegetation_walk_good_visibility.shp", null, CQL.toFilter( "CODE_NATUR = 5018" ) );
		//410_S_vegetation_fight
		//411_S_vegetation_impassable
		//411.2_L_vegetation_impassable_minw
		//412_S_cultivated_land
		//5007	terre labourable
		extractSHP(inBasePath + "PARCELLES.shp", outBasePath+"412_S_cultivated_land.shp", null, CQL.toFilter( "CODE_NATUR = 5007" ) );
		//413_S_orchard
		//5016	verger
		extractSHP(inBasePath + "PARCELLES.shp", outBasePath+"413_S_orchard.shp", null, CQL.toFilter( "CODE_NATUR = 5016" ) );
		//414_S_vineyard_similar
		//5011	vigne
		extractSHP(inBasePath + "PARCELLES.shp", outBasePath+"414_S_vineyard_similar.shp", null, CQL.toFilter( "CODE_NATUR = 5011" ) );
		//415_L_distinct_cultivation_boundary
		//416_L_distinct_vegetation_boundary

		//417_P_prominent_large_tree
		//418_P_prominent_bush_or_tree
		//419_P_prominent_vegetation_feature

		//501_S_paved_area_with_bn
		//501.1_S_paved_area
		//TODO no - too many
		/*5035	autoroute
		5036	route nationale
		5041	rue
		5039	piste cyclable
		5024	place //TODO ?
		5025	place (occupée) //TODO ?
		5032	piste d'essai
		5043	place voirie //TODO ?
		 */
		extractSHP(inBasePath + "PARCELLES.shp", outBasePath+"501.1_S_paved_area.shp", null, CQL.toFilter( "CODE_NATUR = 5035 OR CODE_NATUR = 5036 OR CODE_NATUR = 5041 OR CODE_NATUR = 5039 OR CODE_NATUR = 5032 OR CODE_NATUR = 5043" ) );
		//502_L_wide_road
		//503_L_road
		//504_L_vehicle_track
		//505_L_footpath
		//506_L_small_footpath
		//507_L_less_distinct_small_footpath
		//508_L_narrow_ride_or_linear_trace

		//509_L_railway

		//510_L_power_line_cableway_skilift
		//511_L_major_power_line

		//512_L_bridge_tunnel

		//513_L_wall
		//514_L_ruined_wall
		//515_L_impassable_wall
		//516_L_fence
		//517_L_ruined_fence
		//518_L_impassable_fence
		//519,PO,crossing_point
		//520_S_area_shall_not_entered
		/*5023	décharge
		5026	cimetière
		5034	camp militaire
		5031	champ de tir
		5044	voie ferrée
		5029	camping
		5045	aéroport
		5046	écluse
		5047	port
		 */
		extractSHP(inBasePath + "PARCELLES.shp", outBasePath+"520_S_area_shall_not_entered.shp", null, CQL.toFilter( "CODE_NATUR = 5023 OR CODE_NATUR = 5024 OR CODE_NATUR = 5025 OR CODE_NATUR = 5026 OR CODE_NATUR = 5034 OR CODE_NATUR = 5031 OR CODE_NATUR = 5044 OR CODE_NATUR = 5029 OR CODE_NATUR = 5045 OR CODE_NATUR = 5046 OR CODE_NATUR = 5047" ) );
		//521_S_building
		extractSHP(inBasePath + "BATIMENTS.shp", outBasePath+"521_S_building.shp");
		//521.1_P_building_min
		//521.4_L_building_outline
		//522_S_canopy
		//523_L_ruin
		//524_P_high_tower
		//525_P_small_tower
		//526_P_cairn
		//527_P_fodder_rack
		//528_L_prominent_line_feature
		//529_L_prominent_impassable_line_feature
		//530_P_prominent_man_made_feature_o
		//531_P_prominent_man_made_feature_x

		//601_L_magnetic_north_line
		//603.0_P_spot_height
	}






	//clip and filter SHP
	public static void extractSHP(String in, String out) { extractSHP(in, out, null); }
	public static void extractSHP(String in, String out, Envelope env) { extractSHP(in, out, env, null); }
	public static void extractSHP(String in, String out, Envelope env, Filter f) {
		SHPData fsd = SHPUtil.loadSHP(in, f);
		if(env != null) fsd.fs = FeatureUtil.clip(fsd.fs, env);
		SHPUtil.saveSHP(fsd.fs, out, fsd.ft.getCoordinateReferenceSystem());
	}


	//clip ori data
	//TODO not tested
	public void clipOri(String inPath, String outPath, Envelope clipEnv) {
		for(String file : loadOriSchema()) {
			if(new File(inPath + file).exists())
				extractSHP(inPath + file, outPath + file, clipEnv);
		}

	}


	public static ArrayList<String> loadOriSchema() {
		ArrayList<String> out = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/specs.csv"));
			String line = reader.readLine();
			while (line != null) {
				out.add(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) { e.printStackTrace(); }
		return out;
	}

}
