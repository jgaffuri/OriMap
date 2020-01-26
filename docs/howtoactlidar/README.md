# How to produce an orienteering map from ACT LIDAR data with Karttapullatin?

This is a short tutorial for [Luxembourg Orienteering Club](https://orienteering.lu/) members. But not only.

## Requirements

- A PC with windows
- A bit of time !

## Download Karttapullatin software

See on [Karttapullatin website](http://www.routegadget.net/karttapullautin/). Download the version of the program corresponding to your PC (64 of 32 bits). For modern PCs, 64 bits is the one to use.

## Select the extent of your map

- From [geoportail.lu](https://map.geoportail.lu/theme/main?version=3&zoom=14&X=684902&Y=6379262&lang=en&layers=&opacities=&bgLayer=topogr_global), go to the area of interest. As an example, we are going to make a map of *Noumerléen* area:

![Noumerléen](img/1_area.png)

- Display the coordinates: Click on the **i** icon on the bottom right corner. You should then see a new panel at the bottom with some information such as the scale bar, the geographical position and elevation of the mouse pointer. Ensure the selected coordinate system is **LUREF**.

![position](img/2_pos.png)

- Note the minimum and maximum coordinates of you map bounding box.
- For our example, the map bounding box is:
   - Xmin: 79100  -->  Xmax: 81300
   - Ymin: 93400  -->  Ymax: 94300

## Download the ACT LIDAR data

- Search for "LIDAR" on [Luxembourg open data portel](https://data.public.lu/fr/). You should find [this page](https://data.public.lu/en/datasets/lidar-2019-releve-3d-du-territoire-luxembourgeois/). Yes: it is in French... but no need for strong French grammar knowledge here.

The LIDAR data for the entire Luxembourgish territory is decomposed into around 11000 tiles of 500mx500m. These tiles are grouped into zip files of 3x3 tiles (that is covering 1500mx1500m) which are proposed for download. Each zip file is named after **the coordinates of its lower left corner point**. For example, the file *lidar2019-ndp-c14-r44-ll69500-122500-epsg2169.zip* covers an area of 1500x1500m whose lower left corner position is *X=ll69500 Y=122500*.

To select the right files corresponding to the map bounding box:
- Click on the button *"See the 1793 resources of type Main file"* at the bottom of the list to show all available zip files.
- Search the files corresponding to your bounding map, considering that the coordinates are multiple of 500. You may require a single one, or several, depending on the map size and location. For our case, we should download 4 zip files:
  - [lidar2019-ndp-c20-r24-ll78500-92500-epsg2169.zip](https://download.data.public.lu/resources/lidar-2019-releve-3d-du-territoire-luxembourgeois/20200109-075037/lidar2019-ndp-c20-r24-ll78500-92500-epsg2169.zip)
  - [lidar2019-ndp-c20-r25-ll78500-94000-epsg2169.zip](https://download.data.public.lu/resources/lidar-2019-releve-3d-du-territoire-luxembourgeois/20200109-075309/lidar2019-ndp-c20-r25-ll78500-94000-epsg2169.zip)
  - [lidar2019-ndp-c21-r24-ll80000-92500-epsg2169.zip](https://download.data.public.lu/resources/lidar-2019-releve-3d-du-territoire-luxembourgeois/20200109-093636/lidar2019-ndp-c21-r24-ll80000-92500-epsg2169.zip)
  - [lidar2019-ndp-c21-r25-ll80000-94000-epsg2169.zip](https://download.data.public.lu/resources/lidar-2019-releve-3d-du-territoire-luxembourgeois/20200109-093959/lidar2019-ndp-c21-r25-ll80000-94000-epsg2169.zip)

Each zip file size is around 300/500Mo.

- Unzip the downloaded zip file. Each of them should contain 9 *\*.laz* files. For our example, we have 9x4=36 *\*.laz* files.

## Prepare the workspace

- Create a working folder were we are going to put all files. For example: *E:myfolder/myfolder2/workspace/*.
- Create here a folder with the input data: *.../workspace/in/noumerleen/*
- Move the 36 downloaded *\*.laz* files in the input data folder.
- Move the software executable files here: *.../workspace/karttapullautin_standalone/*
- Prepare a folder for the output data: *.../workspace/out/noumerleen/*

## Setup the program

The program parameters are defined in the *workspace/karttapullautin_standalone/pullauta.ini* file.
- Edit the *pullauta.ini* file and find the place with:

```
\# batch process mode, process all laz ans las files of this directory
\# off=0, on=1  
batch=0

\# processes
processes=2

\# batch process output folder
batchoutfolder=./out

\# batch process 

t file folder
lazfolder=./in
```

Replace it with:

```
\# batch process mode, process all laz ans las files of this directory
\# off=0, on=1  
batch=1

\# processes
processes=1

\# batch process output folder
batchoutfolder=../out/noumerleen/

\# batch process input file folder
lazfolder=../in/noumerleen/
```
(If you know how many processors your PC has, you can specify it in the `processes=XXX` parameter. This will allow a parallel and thus faster computation)

- Save and launch the program by doulbe clicking on: *pullauta.exe*. This may take several hours to complete depending on the number and size of the *\*.laz* files to process and the power of the PC. You can follow the process progress by looking at the content of the *.../workspace/out/noumerleen/* folder where the output is progressively produced, tile after tile.

Once the process is complete, we are not done yet: This has produced 1 result for each tile, and we need to merge all these tiles into a single output. For that:

- create a *merge.bat* file in *.../workspace/karttapullautin_standalone/* folder with the following content:
```
pullauta pngmerge 1
pullauta pngmergedepr 1
pullauta pngmergevege
pullauta dxfmerge
```
- Double click on this *merge.bat* file to execute all the commands to merge the outputs.

(output)

## After...


Any comment? Anything unclear, missing, incorrect or wrong? Feel free to edit or comment.
