echo "1"
pdal pipeline p1.json
echo "2"
pdal pipeline p2.json
echo "3"
pdal pipeline p3.json

echo "hillshading" 
gdaldem hillshade pdal_out/dtm_1.tif pdal_out/hillshade_1.tif -z 1 -s 1 -az 315 -alt 45
gdaldem hillshade pdal_out/dtm_2.tif pdal_out/hillshade_2.tif -z 1 -s 1 -az 315 -alt 45
gdaldem hillshade pdal_out/dtm_3.tif pdal_out/hillshade_3.tif -z 1 -s 1 -az 315 -alt 45

echo "slope"
gdaldem slope pdal_out/dtm_1.tif pdal_out/slope_1.tif -s 1
gdaldem slope pdal_out/dtm_2.tif pdal_out/slope_2.tif -s 1
gdaldem slope pdal_out/dtm_3.tif pdal_out/slope_3.tif -s 1

