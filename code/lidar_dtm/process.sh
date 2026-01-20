FOLDER_PATH="/home/juju/orienteering/zoufftgen/"

#echo "pipeline"
#pdal pipeline p.json

echo "hillshading" 
gdaldem hillshade "${FOLDER_PATH}dtm.tif" "${FOLDER_PATH}hillshade.tif" -z 1 -s 1 -az 315 -alt 45

echo "slope"
gdaldem slope "${FOLDER_PATH}dtm.tif" "${FOLDER_PATH}slope.tif" -s 1
echo "slope calc"
gdal_calc.py -A "${FOLDER_PATH}slope.tif" --outfile="${FOLDER_PATH}slope_255.tif" --calc="((1-(A/90))**3)*255"
#echo "slope 90 to 255"
#gdal_translate -tr 0.25 0.25 -scale 0 90 0 255 "${FOLDER_PATH}slope.tif" "${FOLDER_PATH}slope_255.tif"
#echo "slope color-relief"
#gdaldem color-relief "${FOLDER_PATH}slope_255.tif" slope_color.txt "${FOLDER_PATH}slope_color.tif" -alpha

#echo "aspect"
gdaldem aspect "${FOLDER_PATH}dtm.tif" "${FOLDER_PATH}aspect.tif"
#-zero_for_flat

echo "convert to png"
gdal_translate -tr 0.25 0.25 -of PNG -co WORLDFILE=YES "${FOLDER_PATH}hillshade.tif" "${FOLDER_PATH}hillshade.png"
gdal_translate -tr 0.25 0.25 -of PNG -co WORLDFILE=YES "${FOLDER_PATH}slope_255.tif" "${FOLDER_PATH}slope.png"
#gdal_translate -tr 0.25 0.25 -of PNG -co WORLDFILE=YES "${FOLDER_PATH}slope_color.tif" "${FOLDER_PATH}slope_color.png"
gdal_translate -tr 0.25 0.25 -of PNG -co WORLDFILE=YES "${FOLDER_PATH}aspect.tif" "${FOLDER_PATH}aspect.png"

echo "clean"
#rm "${FOLDER_PATH}dtm.tif"
rm "${FOLDER_PATH}hillshade.tif"
rm "${FOLDER_PATH}slope.tif"
rm "${FOLDER_PATH}slope_255.tif"
#rm "${FOLDER_PATH}slope_color.tif"
rm "${FOLDER_PATH}aspect.tif"

