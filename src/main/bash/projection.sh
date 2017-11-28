#!/bin/bash
#-projwin ulx uly lrx lry:

#coque
#clipsrc -179 -89 179 89
#77290 75579 80721 78138

outdir="dtm_lux_city/"
xmin=76750
ymin=74250
xmax=78000
ymax=75750


#outdir="dtm_kirchberg_hospital_park/"
#xmin=80500
#ymin=77000
#xmax=81500
#ymax=78250

#outdir="dtm_parc_neuman/"
#xmin=75250
#ymin=75500
#xmax=76500
#ymax=76500


mkdir $outdir

mkdir $outdir"img"
#clip
gdal_translate -projwin $xmin $ymax $xmax $ymin -of GTiff dtm_input/dtm.tif $outdir"img/dtm.tif"
#create contour
gdal_contour -a ELEV_DM -i 500.0 $outdir"img/dtm.tif" $outdir"img/contour_5m.shp"
gdal_contour -a ELEV_DM -i 1000.0 $outdir"img/dtm.tif" $outdir"img/contour_10m.shp"
#contour as dxf
ogr2ogr -overwrite -f "DXF" $outdir"img/contour_5m.dxf" $outdir"img/contour_5m.shp"
ogr2ogr -overwrite -f "DXF" $outdir"img/contour_10m.dxf" $outdir"img/contour_10m.shp"


mkdir $outdir"vect"

for layer in COURBE_NORM COURBE_MAITR COURBE_INTER COURBE_CUV BAS_TALUS TALUS LEVEE
do
ogr2ogr -overwrite -f "ESRI Shapefile" \
   $outdir"vect/"$layer".shp" \
   "dtm_input/dtm_bdtopo/"$layer".shp" \
   -clipsrc $xmin $ymin $xmax $ymax
ogr2ogr -overwrite -f "DXF" "dtm_input/dtm_bdtopo/"$layer".dxf" "dtm_input/dtm_bdtopo/"$layer".shp"
done
