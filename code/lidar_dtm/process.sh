cd /home/juju/Bureau/lidar_test/

echo "pipeline"
pdal pipeline p4.json

echo "hillshading" 
gdaldem hillshade pdal_out/dtm_4.tif pdal_out/hillshade_4.tif -z 1 -s 1 -az 315 -alt 45

echo "slope"
gdaldem slope pdal_out/dtm_4.tif pdal_out/slope_4.tif -s 1
