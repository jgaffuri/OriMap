## Computing resource estimation

Objective: Estimate necessary computing resources to run karttapullautin on the entire Luxembourg territory.

# Disk space

Number of zip files to download (from [here](https://map.geoportail.lu/theme/main?version=3&zoom=10&X=721195&Y=6400425&lang=en&layers=1788&opacities=0.75&bgLayer=orthogr_2013_global&crosshair=false&rotation=0&time=)): Around 40*60 = 2400

The size of each file is around 300 MB. When unzipped, the files have a similar size. Total size is thus around 700 GB

It is however not necessary to download all the files at once - a moving window can be used, and only the necessary data is downloaded on-the-fly, processed, and then deleted.

The disk space to estimate is only those of the output data.

The output of each tile is

