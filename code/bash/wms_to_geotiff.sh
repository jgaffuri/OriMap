#!/bin/bash
#resolution=10cm - max image size is 2500px -> 250m*250m
#http://wmts1.geoportail.lu/opendata/service?REQUEST=GetCapabilities

outdirbase="/home/juju/orienteering"

#each tile is 250m large

#niederanven
#outdir=$outdirbase/"omap_niederanven/img"
#xmin_=81000
#ymin_=78500
#nbx=20
#nby=16

#grunewald findel
outdir=$outdirbase/"omap_grunewald_findel/img"
xmin_=80500
ymin_=76500
nbx=8
nby=8

#outdir=$outdirbase/"omap_bambesch/img"
#X: 73880 - 76380 Y: 76900 - 79350
#xmin_=73750
#ymin_=76750
#nbx=11
#nby=11


#outdir=$outdirbase/"omap_sandweiler/img"
#xmin_=82500
#ymin_=74750
#nbx=9
#nby=8

#kirchberg coque
#outdir=$outdirbase/"omap_kirchberg_merge/img_coque"
#xmin_=78500
#ymin_=76250
#nbx=4
#nby=4

#kirchberg arboretum
#outdir=$outdirbase/"omap_kirchberg_merge/img_arboretum"
#xmin_=79250
#ymin_=76750
#nbx=4
#nby=4

#kirchberg klose groendchen
#outdir=$outdirbase/"omap_klose_groendchen_park/img"
#xmin_=79750
#ymin_=77000
#nbx=6
#nby=4


#outdir="img_schetterhaard"
#xmin_=85000
#ymin_=75500
#nbx=7
#nby=6

#outdir=$outdirbase/"omap_grengewald/img_grengewald"
#xmin_=78000
#ymin_=79000
#nbx=10
#nby=10

#outdir=$outdirbase/"omap_grengewald_niederanven/img_grengewald_niederanven"
#xmin_=81500
#ymin_=78750
#nbx=17
#nby=13

#outdir="img_parc_neuman"
#xmin_=75250
#ymin_=75500
#nbx=5
#nby=4

#outdir="lux_city"
#xmin_=76750
#ymin_=74250
#nbx=5
#nby=6



crs=EPSG:2169

#mkdir $outdir

#for layer in ortho_latest ortho_2019_winter topo_5k ortho_irc TOPO_CARTESHISTO_1989 cadastre
#for layer in lidar_2019_mnt_public lidar_2019_mns_public ortho_2019_winter ortho_latest #ortho_2019_winter topo_5k ortho_irc TOPO_CARTESHISTO_1989 cadastre
#for layer in lidar_2019_mnt_public
for layer in lidar_2019_mns_public lidar_2019_mnt_public ortho_2019_winter ortho_latest ortho_irc topo_5k
do

mkdir $outdir/$layer

for (( x=0; x<$nbx; x++ ))
do
	for (( y=0; y<$nby; y++ ))
	do
		xmin=$(($xmin_+$x*250))
		ymin=$(($ymin_+$y*250))
		xmax=$(($xmin+250))
		ymax=$(($ymin+250))
		name=$layer"_"$xmin"_"$ymin
		echo $name

		url="http://wmts1.geoportail.lu/opendata/service?REQUEST=GetMap&version=1.1.1&layers=$layer&srs=$crs&format=image/png&bbox=$xmin,$ymin,$xmax,$ymax&width=2500&height=2500&styles="
		curl -o $outdir/$layer/$name.png $url
		gdal_translate -a_srs ${crs} -a_ullr $xmin $ymax $xmax $ymin $outdir/$layer/$name.png $outdir/$layer/$name.tif
		rm $outdir/$layer/$name.png
	done
done

done
