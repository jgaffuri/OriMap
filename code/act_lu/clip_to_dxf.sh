#!/bin/bash
#-projwin ulx uly lrx lry:

outdirbase="/home/juju/orienteering"

outdir=$outdirbase/"omap_niederanven/dxf/"
xmin=83000
ymin=78000
xmax=87250
ymax=82500

#coque
#clipsrc -179 -89 179 89
#77290 75579 80721 78138

#outdir="dtm_lux_city/"
#xmin=76750
#ymin=74250
#xmax=78000
#ymax=75750

#outdir=$outdirbase/"omap_kirchberg_hospital_park/dtm/"
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

#clip country wide dtm
gdal_translate -projwin $xmin $ymax $xmax $ymin -of GTiff $outdirbase/"omap_luxembourg_shp/dtm/dtm.tif" $outdir"dtm.tif"
#create contour as shp
gdal_contour -a ELEV_DM -i 500.0 $outdir"dtm.tif" $outdir"contour_5m.shp"
gdal_contour -a ELEV_DM -i 1000.0 $outdir"dtm.tif" $outdir"contour_10m.shp"
#shp to dxf
ogr2ogr -overwrite -f "DXF" $outdir"contour_5m.dxf" $outdir"contour_5m.shp"
ogr2ogr -overwrite -f "DXF" $outdir"contour_10m.dxf" $outdir"contour_10m.shp"


#extract
#COURBE_NORM COURBE_MAITR COURBE_INTER COURBE_CUV BAS_TALUS TALUS LEVEE 
for layer in AIRE_AMEN ALLEE ARB_ISOLE AXE_CHEMIN AXE_ROUTE AXE_SENTIER BARRAGE BASSIN BAS_TALUS BATI_AGRI BATI_COMME BATI_INDUS BATIMENT BATI_PUB BATI_RELIG BATI_REMAR BORD_CHAUSS BROUSSAIL CENT_SPORT CHAT_EAU CIMETIERE CLOCHER CONDUITE CONS_LEGER CONS_SPE_P CONS_SPE_S COURBE_CUV COURBE_INTER COURBE_MAITR COURBE_NORM COURS_EAU ECLUSE ENT_MINE GARE HAIE HELIPORT JARDIN LAYON LEVEE LIGNE_ELEC LIM_CULTURE LIM_INDECI MONUMENT_P MONUMENT_S MONU_REL_P MUR MUR_SOUT PARAPET PARKING PASSERELLE PISTE_AERO POINT_COTE POINT_EAU POINT_VUE PONT POSTE_ELEC PRAIRIE PYLONE QUAI RANG_ARBRE RESER_EAU RESER_EAU_P RESERVOIR RIVIERE RUINE RUISSEAU SEPARATEUR SERRE STAT_POMP SURF_HYDRO TALUS TERR_SPORT TRANS_CABL TRANSFO TRANSFO_P TUNNEL VERGER VOIE_FER ZONE_HUMID
do
echo "------ "$layer
#clip
ogr2ogr -overwrite -f "ESRI Shapefile" \
   $outdir$layer".shp" \
   $outdirbase/"omap_luxembourg_shp/BD-L-TC2008/"$layer".shp" \
   -clipsrc $xmin $ymin $xmax $ymax
#shp to dxf
ogr2ogr -overwrite -f "DXF" $outdir$layer".dxf" $outdir$layer".shp"
done
: