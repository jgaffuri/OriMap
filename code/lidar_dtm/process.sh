FOLDER_PATH="/home/juju/Bureau/lidar_test/"

echo "pipeline"
pdal pipeline p4.json

echo "hillshading" 
gdaldem hillshade "${FOLDER_PATH}pdal_out/dtm_4.tif" "${FOLDER_PATH}pdal_out/hillshade_4.tif" -z 1 -s 1 -az 315 -alt 45

echo "slope"
gdaldem slope "${FOLDER_PATH}pdal_out/dtm_4.tif" "${FOLDER_PATH}pdal_out/slope_4.tif" -s 1
