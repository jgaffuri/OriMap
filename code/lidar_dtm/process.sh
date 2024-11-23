FOLDER_PATH="/home/juju/Bureau/lidar_test/"

echo "pipeline"
pdal pipeline p.json

echo "hillshading" 
gdaldem hillshade "${FOLDER_PATH}pdal_out/dtm.tif" "${FOLDER_PATH}pdal_out/hillshade.tif" -z 1 -s 1 -az 315 -alt 45

echo "slope"
gdaldem slope "${FOLDER_PATH}pdal_out/dtm.tif" "${FOLDER_PATH}pdal_out/slope.tif" -s 1
