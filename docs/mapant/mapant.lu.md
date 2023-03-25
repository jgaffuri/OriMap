# Computing resource estimation

Objective: Estimate necessary computing resources to run karttapullautin on the entire Luxembourg territory.

## Disk space

### Input data

Number of zip files to download (from [here](https://map.geoportail.lu/theme/main?version=3&zoom=10&X=721195&Y=6400425&lang=en&layers=1788&opacities=0.75&bgLayer=orthogr_2013_global&crosshair=false&rotation=0&time=)): Around 40*60 = 2400

The size of each file is around 300 MB. When unzipped, the files have a similar size. Total size is thus around 700 GB

It is however not necessary to download all the files at once - a moving window can be used, and only the necessary data is downloaded on-the-fly, processed, and then deleted. A maximum of 3x3=9 zip files is necessary, which represents less than 3 GB.

### Output data

The output of each tile is around 50 MB, most of this space is occupied by dxf files which are not necessary to keep. Only png images can be kept. There size is around 250 kB per tile. There are 9 tiles per zip file, so in total 20000 tiles. The disk space is: 20000x250kO = around 10 GB.

In conclusion: **15 GB disk space is necessary**

## Computing time

There are 20000 tiles to process. Each takes around 30'. In total, around 300 days.

With 3 processors, this duration can be reduced to 3 times less: **100 days***.

