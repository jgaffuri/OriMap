
#use QGIS: raster > miscaleanous > merge (or raster > divers > fusion)
# + save as geotiff, as image

gdal_merge.py -of HFA -o /home/juju/orienteering/omap_coque/img_arboretum/ortho_2017_arbor_.tif /home/juju/orienteering/omap_coque/img_arboretum/ortho_latest/ortho_latest_79250_76750.tif /home/juju/orienteering/omap_coque/img_arboretum/ortho_latest/ortho_latest_79250_77000.tif /home/juju/orienteering/omap_coque/img_arboretum/ortho_latest/ortho_latest_79250_77250.tif /home/juju/orienteering/omap_coque/img_arboretum/ortho_latest/ortho_latest_79250_77500.tif /home/juju/orienteering/omap_coque/img_arboretum/ortho_latest/ortho_latest_79500_76750.tif
gdal_merge.py -ot Float32 -of HFA -o /home/juju/Bureau/orienteering/omap_niederanven/img/ortho_latest.tif --optfile /tmp/processing_cb5d14720c3548b98d257c2bff1d7e2e/46cddbc842bf4ea6929bf12eafca0a2d/mergeInputFiles.txt
