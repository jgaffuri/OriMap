# How to produce an orienteering map from ACT LIDAR data with Karttapullatin?

This is a short tutorial for [Luxembourg Orienteering Club](https://orienteering.lu/) members. Not only.

## Requirements

- A PC with windows
- A bit of time !

## Download Karttapullatin

See on [Karttapullatin website](http://www.routegadget.net/karttapullautin/). Download the version of the program corresponding to your PC (64 of 32 bits). For modern PCs, 64 bits is the one to use.

## Select the extent of your map area

- From [geoportail.lu](https://map.geoportail.lu/theme/main?version=3&zoom=14&X=684902&Y=6379262&lang=en&layers=&opacities=&bgLayer=topogr_global), go to the area of interest. As an example, we are going to make a map of *Noumerléen* area:

![Noumerléen](img/1_area.png)

- Display the coordinates: Click on the **i** icon on the bottom right corner. You should then see a new panel at the bottom with some information such as the scale bar, the geographical position and elevation of the mouse pointer. Ensure the selected coordinate system is **LUREF**.

![position](img/2_pos.png)

- Note the minimum and maximum coordinates of you map bounding box.
- For our example, the map bounding box is:
   - Xmin: 79100  -->  Xmax: 81300
   - Ymin: 93400  -->  Ymax: 94300

## Download the LIDAR data

- Search for "LIDAR" on [Luxembourg open data portel](https://data.public.lu/fr/). You should find [this page](https://data.public.lu/en/datasets/lidar-2019-releve-3d-du-territoire-luxembourgeois/). Yes: it is in French... but no need for strong French grammar knowledge here.

The LIDAR data for the entire Luxembourgish territory is decomposed into around 11000 tiles of 500mx500m. These tiles are grouped into zip files of 3x3 tiles (that is covering 1500mx1500m) which are proposed for download. Each zip file is named after **the coordinates of its lower left corner point**. For example, the file *lidar2019-ndp-c14-r44-ll69500-122500-epsg2169.zip* covers an area of 1500x1500m whose lower left corner position is *X=ll69500 Y=122500*.

To select the right files corresponding to the map bounding box:
- Click on the button *"See the 1793 resources of type Main file"* at the bottom of the list to show all available zip files.
- Search the files corresponding to your bounding map, considering that the coordinates are multiple of 500. You may require a single one, or several, depending on the map size and location. For our case, we should download 4 zip files:
  - [lidar2019-ndp-c20-r24-ll78500-92500-epsg2169.zip](https://download.data.public.lu/resources/lidar-2019-releve-3d-du-territoire-luxembourgeois/20200109-075037/lidar2019-ndp-c20-r24-ll78500-92500-epsg2169.zip)
  - [lidar2019-ndp-c21-r24-ll80000-92500-epsg2169.zip](https://download.data.public.lu/resources/lidar-2019-releve-3d-du-territoire-luxembourgeois/20200109-093636/lidar2019-ndp-c21-r24-ll80000-92500-epsg2169.zip)
  - [lidar2019-ndp-c21-r25-ll80000-94000-epsg2169.zip](https://download.data.public.lu/resources/lidar-2019-releve-3d-du-territoire-luxembourgeois/20200109-093959/lidar2019-ndp-c21-r25-ll80000-94000-epsg2169.zip)
  - [lidar2019-ndp-c20-r25-ll78500-94000-epsg2169.zip](https://download.data.public.lu/resources/lidar-2019-releve-3d-du-territoire-luxembourgeois/20200109-075309/lidar2019-ndp-c20-r25-ll78500-94000-epsg2169.zip)


(describe)

## Prepare the workspace

## Setup the program

## After...


Any comment? Anything unclear, missing, incorrect or wrong? Feel free to edit or comment.
