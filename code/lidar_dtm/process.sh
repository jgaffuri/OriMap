FOLDER_PATH="/home/juju/Bureau/lidar_test/"

echo "pipeline"
pdal pipeline p.json

echo "hillshading" 
gdaldem hillshade "${FOLDER_PATH}pdal_out/dtm.tif" "${FOLDER_PATH}pdal_out/hillshade.tif" -z 1 -s 1 -az 315 -alt 45

echo "slope"
gdaldem slope "${FOLDER_PATH}pdal_out/dtm.tif" "${FOLDER_PATH}pdal_out/slope.tif" -s 1

echo "aspect"
gdaldem aspect "${FOLDER_PATH}pdal_out/dtm.tif" "${FOLDER_PATH}pdal_out/aspect.tif"
#-zero_for_flat


gdal_translate -tr 0.25 0.25 -of PNG "${FOLDER_PATH}pdal_out/hillshade.tif" "${FOLDER_PATH}pdal_out/hillshade.png"
gdal_translate -tr 0.25 0.25 -of PNG "${FOLDER_PATH}pdal_out/slope.tif" "${FOLDER_PATH}pdal_out/slope.png"
gdal_translate -tr 0.25 0.25 -of PNG "${FOLDER_PATH}pdal_out/aspect.tif" "${FOLDER_PATH}pdal_out/aspect.png"

