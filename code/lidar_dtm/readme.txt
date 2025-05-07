This process lidar data to produce hillshaded dtm, slope and aspect.


* activate pdal:
conda deactivate
conda activate pdal

* set input lidar laz folder path in p.json file

* set output path in p.json file (for dtm.tiff) and process.sh file (FOLDER_PATH variable)

* cd to process.sh folder

* run "bash process.sh" command



TODO:
* move to python (see cartoHD process as an example)
* Do the same for DSM ?

