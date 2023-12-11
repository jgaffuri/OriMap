
# https://geoservices.ign.fr/documentation/services/api-et-services-ogc/images-wms-ogc
# https://geoservices.ign.fr/documentation/services/api-et-services-ogc/images-tuilees-wmts-ogc

# read key
with open('/home/juju/clef_ign.txt', 'r') as f:
    clef = f.read()
#print(clef)

# WMS Raster;cartes;IGN;Plan IGN;GEOGRAPHICALGRIDSYSTEMS.PLANIGNV2;
# https://wxs.ign.fr/cartes/geoportail/r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetCapabilities
# https://data.geopf.fr/wms-r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetCapabilities

https://wxs.ign.fr/cartes/geoportail/r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetMap
&format=image%2Fpng
&layers=GEOGRAPHICALGRIDSYSTEMS.PLANIGNV2
&layers=GEOGRAPHICALGRIDSYSTEMS.MAPS.BDUNI.J1
&crs=EPSG%3A2154

DE: &bbox=4281949,3051619,4309518,3060335
xmin,ymin,xmax,ymax

Fameck:
&bbox=923700,6914500,927000,6917200
dx=3300
dy=2700
https://wxs.ign.fr/cartes/geoportail/r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetMap&format=image%2Fpng&layers=GEOGRAPHICALGRIDSYSTEMS.PLANIGNV2&crs=EPSG%3A2154&bbox=923700,6914500,927000,6917200&width=3300&height=2700&styles=

Thionville:
928000,6921000,931100,6924200
dx=3100
dy=3200
https://wxs.ign.fr/cartes/geoportail/r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetMap&format=image%2Fpng&layers=GEOGRAPHICALGRIDSYSTEMS.PLANIGNV2&crs=EPSG%3A2154&bbox=928000,6921000,931100,6924200&width=3100&height=3200&styles=


https://sgx.geodatenzentrum.de/wms_basemapde?&service=WMS&request=GetMap&layers=de_basemapde_web_raster_grau&styles=
&format=image%2Fjpeg&transparent=false&version=1.1.1&srs=EPSG%3A3035&width=1920&height=607
&bbox=4281949.710144452,3051619.5653094496,4309518.470664381,3060335.3140779897

# WMS Raster;essentiels;IGN;Plan IGN;GEOGRAPHICALGRIDSYSTEMS.PLANIGNV2;
# https://wxs.ign.fr/essentiels/geoportail/r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetCapabilities;
# https://data.geopf.fr/wms-r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetCapabilities

# WMS Raster;cartes;IGN;PLAN IGN J+1;GEOGRAPHICALGRIDSYSTEMS.MAPS.BDUNI.J1;
# https://wxs.ign.fr/cartes/geoportail/r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetCapabilities
# https://data.geopf.fr/wms-r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetCapabilities

# WMS Raster;essentiels;IGN;Parcellaire Express (PCI);CADASTRALPARCELS.PARCELLAIRE_EXPRESS
# https://wxs.ign.fr/essentiels/geoportail/r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetCapabilities
# https://data.geopf.fr/wms-r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetCapabilities


#vectoriel
#courbes de niveau: https://geoservices.ign.fr/courbes-de-niveau
#bd topo: https://geoservices.ign.fr/bdtopo#telechargementgpkgdep




#LIDAR HD classé Moselle
# https://geoservices.ign.fr/lidarhd#telechargementclassifiees