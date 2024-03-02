# Comment produire une carte de course d'orientation à partir des données LIDAR HD IGN avec Karttapullatin?


## Télécharger les données LIDAR HD IGN

Rendez-vous sur [le site de l'IGN](https://www.ign.fr/) pour télécharger les données LIDAR HD classifiées sur votre zone d'intérêt: Voir [cette section](https://geoservices.ign.fr/lidarhd#telechargementclassifiees) ou [cette carte interactive](https://diffusion-lidarhd.ign.fr/) .

Pour ce tutoriel, notre zone d'intérêt correspond aux alentours du fort du Hackenberg:

![Hackenberg](img/hackenberg.png)

Pour cette zone, 4 fichiers doivent être téléchargés. Chaque fichier correspond à une zone carrée de 1km². Ces fichiers sont au format *\*.laz* et font environ 100/150 Mo. Chaque fichier peut être visualisé facilement avec [plas.io](https://plas.io/), pour un apercu rapide des données.

## Télécharger le logiciel Karttapullatin et preparer l'espace de travail

- Choisir un dossier de travail sur votre PC, par exemple *E:monDossier/monDossier2/tutoriel/*. Ca peut être simplement un dossier sur votre bureau...
- Créer des sous-dossiers où mettre les données téléchargée: *.../tutoriel/entree/hackenberg/*. Y placer les 4 fichiers *\*.laz* téléchargés.
- Télécharger le logiciel Karttapullatin depuis [le site Karttapullatin](http://www.routegadget.net/karttapullautin/). Télécharger la version 32 ou 64 bits en fonction de votre PC - s'il est récent, ce devrait être un 64 bits.
- Pas besoin d'installer le lociciel: Déplacez juste le dossier téléchargé et décompréssé ici: *.../tutoriel/karttapullautin_standalone/*
- Preparer un dossier pour les résultats produits par le lociciel: *.../tutoriel/sortie/hackenberg/*


- Download and unzip [las2txt](https://github.com/jgaffuri/OriMap/raw/master/docs/howtoactlidar/las2txt.zip). Move the file *las2txt.exe* here: *.../workspace/karttapullautin_standalone/*. Alternativelly, you can install libLAS following [these instructions](https://liblas.org/osgeo4w.html), but it is simpler to simply put *las2txt.exe* manually...

## Paramétrer Karttapullatin

Les paramètres du programme sont spécifiés dans le fichier texte: *.../tutoriel/karttapullautin_standalone/pullauta.ini* file.

- Editer le fichier *pullauta.ini* (avec notepad ou n'importe quel programme d'édition de fichier texte) et trouver l'endroit avec ces lignes:

```
# batch process mode, process all laz ans las files of this directory
# off=0, on=1  
batch=0

# processes
processes=2

# batch process output folder
batchoutfolder=./out

# batch process input file folder
lazfolder=./in
```

- Remplacer ces lignes par:

```
batch=1
processes=1
batchoutfolder=../sortie/hackenberg/
lazfolder=../entree/hackenberg/
```

- Si vous connaissez le nombre de processeurs de votre PC, vous pouvez spécifier combien seront utilisés par



- If you know the number of processors available on your PC, you can specify how many will be used by karttapullautin with `processes=XXX` parameter. This will allow a parallel and thus faster computation.
- **Launch the program** with e double click on: *pullauta.exe*. This may take several hours to complete depending on the number and size of the *\*.laz* files to process and the computation power of the PC. You can follow the process progress by looking at the content of the *.../workspace/out/noumerleen/* folder, where the output is progressively produced, tile after tile. Some images can be viewed there, step after step.

Once the process is complete, we are not done yet: The process produces 1 output for each tile, and we need to merge all these outputs into a single one. An easy way to proceed the following:

- Create a *merge.bat* file in *.../workspace/karttapullautin_standalone/* folder (right click, then *new file*). Edit it as a text file and add the following content:

```
pullauta pngmerge 1
pullauta pngmergedepr 1
pullauta pngmergevege
pullauta dxfmerge
```
- Double click on this *merge.bat* file to execute all the commands listed here, which merge the outputs. As a result, several **merged_XXXX.YYY** files are produced in the *.../workspace/karttapullautin_standalone/* folder. There are *\*.png* and *\*.jpg* image files, which can be viewed as a normal image, and also *\*.dxf* files, which can be imported in other mapping software (as explained below).

Here is the output obtained for our Noumerléen map:

[![orienteering map noumerleen lidar karttapullautin](img/noumer_out.png)](https://raw.githubusercontent.com/jgaffuri/OriMap/master/docs/howtoactlidar/img/merged_depr.png)

(Download [full size](https://raw.githubusercontent.com/jgaffuri/OriMap/master/docs/howtoactlidar/img/merged_depr.png))

- If the output is not satisfying and do not show properly natural features that you know are present on the field, it is possible to fine-tune some parameters in the *pullauta.ini* file. Most of them are explained directly in the file. You can also check the documentation on [Karttapullatin website](http://www.routegadget.net/karttapullautin/) or ask some advice on the [RouteGadget Facebook page](https://www.facebook.com/RouteGadget-177518995597572/).

## After...

- The output *\*.dxf* files can be imported as vector features in mapping software such as [OCAD](https://www.ocad.com/), [OpenOrienteering Mapper](https://www.openorienteering.org/apps/mapper/) or [QGIS](https://qgis.org/). Image outputs can also be used as a backdrop to be digitalised or to support field surveys.
- Other geographical data sources can be used for non-natural features which are not detected properly by karttapullautin. Here are some of them:
  - The Luxembourgish national topographic data base, whose 2015 version can be downloaded freely [**here**](https://data.public.lu/en/datasets/bd-l-tc-2015/). This include some excellent information for building footprints, transport networks (from main roads to hiking tracks), and many other man-made and natural features shown on topographic maps.
  - [OpenStreetMap](https://www.openstreetmap.org/). An extraction for Luxembourg is available [here](http://download.geofabrik.de/europe/luxembourg.html).

This additional vector data can be displayed together or on top of the karttapullautin image to offer some better overview. Here is the output obtained for our Noumerléen map:

[![orienteering map noumerleen lidar karttapullautin](img/noumer_out_gis.png)](https://github.com/jgaffuri/OriMap/raw/master/docs/lidaroutputs/noumerleen.pdf)

(Download [full size](https://github.com/jgaffuri/OriMap/raw/master/docs/lidaroutputs/noumerleen.pdf))

This could be seen as a 'good enough' map for some trainings or simply to detect some interesting areas to be then better mapped with unavoidable field surveys.

See some other examples of maps [**here**](../lidaroutputs).

## Any comment? Anything unclear, missing, incorrect or wrong? Feel free to edit the file or create an issue [here](https://github.com/jgaffuri/OriMap/issues).
