
for layer in AIRE_AMEN ALLEE ARB_ISOLE AXE_CHEMIN AXE_ROUTE AXE_SENTIER BARRAGE
do
echo "------ "$layer

#ogr2ogr -f "ESRI Shapefile" gis_osm_buildings_a_free_1_LUXPROJ.shp gis_osm_buildings_a_free_1.shp -t_srs EPSG:2169 -s_srs EPSG:4326
#ogr2ogr -overwrite -f "DXF" $outdir$layer".dxf" $outdir$layer".shp"

done
