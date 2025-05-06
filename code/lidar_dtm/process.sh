FOLDER_PATH="/home/juju/orienteering/omap_niederanven/"

echo "pipeline"
pdal pipeline p.json

echo "hillshading" 
gdaldem hillshade "${FOLDER_PATH}dtm.tif" "${FOLDER_PATH}hillshade.tif" -z 1 -s 1 -az 315 -alt 45

echo "slope"
gdaldem slope "${FOLDER_PATH}dtm.tif" "${FOLDER_PATH}slope.tif" -s 1

echo "aspect"
gdaldem aspect "${FOLDER_PATH}dtm.tif" "${FOLDER_PATH}aspect.tif"
#-zero_for_flat


gdal_translate -tr 0.25 0.25 -of PNG -co WORLDFILE=YES "${FOLDER_PATH}hillshade.tif" "${FOLDER_PATH}hillshade.png"
gdal_translate -tr 0.25 0.25 -of PNG -co WORLDFILE=YES "${FOLDER_PATH}slope.tif" "${FOLDER_PATH}slope.png"
gdal_translate -tr 0.25 0.25 -of PNG -co WORLDFILE=YES "${FOLDER_PATH}aspect.tif" "${FOLDER_PATH}aspect.png"

