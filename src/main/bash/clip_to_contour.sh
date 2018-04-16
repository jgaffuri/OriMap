#!/bin/bash
#-projwin ulx uly lrx lry:

outdirbase="/home/juju/orienteering"

#coque
#clipsrc -179 -89 179 89
#77290 75579 80721 78138

#outdir="dtm_lux_city/"
#xmin=76750
#ymin=74250
#xmax=78000
#ymax=75750


outdir=$outdirbase/"omap_kirchberg_hospital_park/dtm/"
xmin=80500
ymin=77000
xmax=81500
ymax=78250

#outdir="dtm_parc_neuman/"
#xmin=75250
#ymin=75500
#xmax=76500
#ymax=76500


mkdir $outdir

#clip contry wide dtm
gdal_translate -projwin $xmin $ymax $xmax $ymin -of GTiff $outdirbase/"omap_luxembourg_shp/dtm/dtm.tif" $outdir"dtm.tif"
#create contour as shp
gdal_contour -a ELEV_DM -i 500.0 $outdir"dtm.tif" $outdir"contour_5m.shp"
gdal_contour -a ELEV_DM -i 1000.0 $outdir"dtm.tif" $outdir"contour_10m.shp"
#shp to dxf
ogr2ogr -overwrite -f "DXF" $outdir"contour_5m.dxf" $outdir"contour_5m.shp"
ogr2ogr -overwrite -f "DXF" $outdir"contour_10m.dxf" $outdir"contour_10m.shp"


#extract
for layer in COURBE_NORM COURBE_MAITR COURBE_INTER COURBE_CUV BAS_TALUS TALUS LEVEE
do
#clip
ogr2ogr -overwrite -f "ESRI Shapefile" \
   $outdir$layer".shp" \
   "omap_luxembourg_shp/BD-L-TC2008/"$layer".shp" \
   -clipsrc $xmin $ymin $xmax $ymax
#shp to dxf
ogr2ogr -overwrite -f "DXF" $outdir$layer".dxf" $outdir$layer".shp"
done
