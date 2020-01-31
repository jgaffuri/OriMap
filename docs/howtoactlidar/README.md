# How to produce an orienteering map from ACT LIDAR data with Karttapullatin?

This is a tutorial for anyone interested in mapping natural areas of Luxembourg using the [ACT LIDAR data](https://act.public.lu/fr/cartographie/lidar.html). This is provided by the [Luxembourg Orienteering Club](https://orienteering.lu/) (feel free to [join us](https://orienteering.lu/en/join/)!).

## Requirements

- A PC with Windows.
- A bit of time and maybe resilience !

## Select the map extent

- From [geoportail.lu](https://map.geoportail.lu/theme/main?version=3&zoom=14&X=684902&Y=6379262&lang=en&layers=&opacities=&bgLayer=topogr_global), go to the area of interest. As an example, we are going to make a map of *Noumerléen*, here:

![Noumerléen](img/1_area.png)

- Display the coordinates: Click on the **i** icon on the bottom right corner. You should then see a new panel at the bottom with some information such as the scale bar, the geographical position and elevation of the mouse pointer. Ensure the selected coordinate system is **LUREF** (which should be the case by default).

![position](img/2_pos.png)

- Note the minimum and maximum coordinates of you map bounding box. For our map, the bounding box is:
   - Xmin: 79100  -->  Xmax: 81300
   - Ymin: 93400  -->  Ymax: 94300

## Download the ACT LIDAR data

- Search for "LIDAR" on [Luxembourg open data portal](https://data.public.lu/fr/). You should find [this page](https://data.public.lu/en/datasets/lidar-2019-releve-3d-du-territoire-luxembourgeois/). Yes: it is in French... but no need for strong French grammar knowledge here.

The LIDAR data for the entire Luxembourgish territory is decomposed into around 11000 tiles of 500m x 500m. These tiles are grouped into zip files of 3x3 tiles (thus, covering 1500m x 1500m) which are proposed for download. Each zip file is named after **the coordinates of its lower left corner**. For example, the file *lidar2019-ndp-c14-r44-ll69500-122500-epsg2169.zip* covers an area of 1500x1500m whose lower left corner position is *X=69500 Y=122500*.

To select the right files corresponding to the map bounding box:
- Click on the button *"See the 1793 resources of type Main file"* at the bottom of the list to show all available zip files.
- Search the files corresponding to your bounding map, considering that the coordinates are multiple of 500. You may require a single one, or several, depending on the map size and location. For our case, we need those 4 downloads:
  - [lidar2019-ndp-c20-r24-ll78500-92500-epsg2169.zip](https://download.data.public.lu/resources/lidar-2019-releve-3d-du-territoire-luxembourgeois/20200109-075037/lidar2019-ndp-c20-r24-ll78500-92500-epsg2169.zip)
  - [lidar2019-ndp-c20-r25-ll78500-94000-epsg2169.zip](https://download.data.public.lu/resources/lidar-2019-releve-3d-du-territoire-luxembourgeois/20200109-075309/lidar2019-ndp-c20-r25-ll78500-94000-epsg2169.zip)
  - [lidar2019-ndp-c21-r24-ll80000-92500-epsg2169.zip](https://download.data.public.lu/resources/lidar-2019-releve-3d-du-territoire-luxembourgeois/20200109-093636/lidar2019-ndp-c21-r24-ll80000-92500-epsg2169.zip)
  - [lidar2019-ndp-c21-r25-ll80000-94000-epsg2169.zip](https://download.data.public.lu/resources/lidar-2019-releve-3d-du-territoire-luxembourgeois/20200109-093959/lidar2019-ndp-c21-r25-ll80000-94000-epsg2169.zip)

Each zip file size is around 300/500Mo.

- Unzip the downloaded zip file. Each of them should contain 9 *\*.laz* files. So, for our example, we have 9x4=36 *\*.laz* files.

## Get Karttapullatin software and prepare the workspace

- Decide on a working folder on your PC, for example: *E:myfolder/myfolder2/workspace/*. This could be a folder on your desktop or anywhere else. No matter.
- Create some folders for the input data: *.../workspace/in/noumerleen/*. Move there the downloaded *\*.laz* files. For our example, we should then have the 36 *\*.laz* files in this folder.
- Download Karttapullatin software on [Karttapullatin website](http://www.routegadget.net/karttapullautin/). Download the version of the program corresponding to your PC (64 of 32 bits). For modern PCs, 64 bits is the one to use.
- No need for any installation: Just move the software executable files here: *.../workspace/karttapullautin_standalone/*
- Install libLAS following [these instructions](https://liblas.org/osgeo4w.html). You can alternatively (TODO descirbe there)
- Prepare a folder for the outputs: *.../workspace/out/noumerleen/*

## Setup Karttapullatin

The program parameters are defined in the *workspace/karttapullautin_standalone/pullauta.ini* file.
- Edit the *pullauta.ini* file and find the place with:

```
# batch process mode, process all laz ans las files of this directory
# off=0, on=1  
batch=0

# processes
processes=2

# batch process output folder
batchoutfolder=./out

# batch process 

# batch process input file folder
lazfolder=./in
```

- Modify this section with these parameter values:

```
batch=1
processes=1
batchoutfolder=../out/noumerleen/
lazfolder=../in/noumerleen/
```
- If you know the number of processors available on your PC, you can specify how many will be used by karttapullautin with `processes=XXX` parameter. This will allow a parallel and thus faster computation.
- **Launch the program** by double clicking on: *pullauta.exe*. This may take several hours to complete depending on the number and size of the *\*.laz* files to process and the computation power of the PC. You can follow the process progress by looking at the content of the *.../workspace/out/noumerleen/* folder, where the output is progressively produced, tile after tile. Some images can be viewed there, step after step.

Once the process is complete, we are not done yet: This has produced 1 output for each tile, and we need to merge all these outputs into a single one. An easy way to proceed the following:

- Create a *merge.bat* file in *.../workspace/karttapullautin_standalone/* folder. Edit it as a text file and add the following content:
```
pullauta pngmerge 1
pullauta pngmergedepr 1
pullauta pngmergevege
pullauta dxfmerge
```
- Double click on this *merge.bat* file to execute all the commands listed here, which merge the outputs. As a result, several **merged_XXXX.YYY** files are produced in the *.../workspace/karttapullautin_standalone/* folder. There are *\*.png* and *\*.jpg* image files, which can be viewed as a normal image, and also *\*.dxf* files, which can be imported in other mapping software (as explained below).

Here is the output obtained for our Noumerléen map:

TODO: show image

- If the output is not satisfying and do not show properly natural features that you know are present on the field, it is possible to fine-tune some parameters in the *pullauta.ini* file. Most of them are explained directly in the file. You can also check the documentation on [Karttapullatin website](http://www.routegadget.net/karttapullautin/) or ask some advice on the [RouteGadget Facebook page](https://www.facebook.com/RouteGadget-177518995597572/).

## After...

- The output *\*.dxf* files can be imported as vector features in mapping software such as [OCAD](https://www.ocad.com/), [OpenOrienteering Mapper](https://www.openorienteering.org/apps/mapper/) or [QGIS](https://qgis.org/). Image outputs can also be used as a backdrop to be digitalised or to support field surveys.
- Other geographical data sources can be used for non-natural features which are not detected properly by karttapullautin. Here are some of them:
  - The Luxembourgish national topographic data base, whose 2015 version can be downloaded freely [**here**](https://data.public.lu/en/datasets/bd-l-tc-2015/). This include some excellent information for building footprints, transport networks (from main roads to hiking tracks), and many other man-made and natural features shown on topographic maps.
  - [OpenStreetMap](https://www.openstreetmap.org/). An extraction for Luxembourg is available [here](http://download.geofabrik.de/europe/luxembourg.html).

This additional vector data can be displayed together or on top of the karttapullautin image to offer some better overview. Here is the output obtained for our Noumerléen map:

TODO: show image

This could be seen as a 'good enough' map for some trainings or simply to detect some interesting areas to be then better mapped with unavoidable field surveys.

## Any comment? Anything unclear, missing, incorrect or wrong? Feel free to edit the file or create an issue [here](https://github.com/jgaffuri/OriMap/issues).
