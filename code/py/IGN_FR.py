
# https://geoservices.ign.fr/documentation/services/api-et-services-ogc/images-wms-ogc
# https://geoservices.ign.fr/documentation/services/api-et-services-ogc/images-tuilees-wmts-ogc

# read key
with open('/home/juju/clef_ign.txt', 'r') as f:
    clef = f.read()
#print(clef)

# WMS Raster;cartes;IGN;Plan IGN;GEOGRAPHICALGRIDSYSTEMS.PLANIGNV2;
# https://wxs.ign.fr/cartes/geoportail/r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetCapabilities
# https://data.geopf.fr/wms-r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetCapabilities

Fameck:
&bbox=923700,6914500,927000,6917200
dx=3300
dy=2700
https://wxs.ign.fr/cartes/geoportail/r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetMap&format=image%2Fpng&layers=GEOGRAPHICALGRIDSYSTEMS.PLANIGNV2&crs=EPSG%3A2154&bbox=923700,6914500,927000,6917200&width=3300&height=2700&styles=

Thionville:
&bbox=928000,6921000,931100,6924200
dx=3100
dy=3200
https://wxs.ign.fr/cartes/geoportail/r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetMap&format=image%2Fpng&layers=GEOGRAPHICALGRIDSYSTEMS.PLANIGNV2&crs=EPSG%3A2154&bbox=928000,6921000,931100,6924200&width=3100&height=3200&styles=


ortho 20cm
OI.OrthoimageCoverage.HR
https://wxs.ign.fr/inspire/inspire/r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetCapabilities
10000 pixels limit:
10000 pix à 20cm = tuiles de 2000 m de cote max

Fameck
-> &bbox=923000,6914000,927000,6917000
&bbox=923000,6914000,925000,6916000
&bbox=923000,6916000,925000,6918000
&bbox=925000,6914000,927000,6916000
&bbox=925000,6916000,927000,6918000

https://wxs.ign.fr/inspire/inspire/r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetMap&format=image%2Fpng&layers=OI.OrthoimageCoverage.HR&crs=EPSG%3A2154&styles=&width=10000&height=10000&bbox=923000,6914000,925000,6916000
https://wxs.ign.fr/inspire/inspire/r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetMap&format=image%2Fpng&layers=OI.OrthoimageCoverage.HR&crs=EPSG%3A2154&styles=&width=10000&height=10000&bbox=923000,6916000,925000,6918000
https://wxs.ign.fr/inspire/inspire/r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetMap&format=image%2Fpng&layers=OI.OrthoimageCoverage.HR&crs=EPSG%3A2154&styles=&width=10000&height=10000&bbox=925000,6914000,927000,6916000
https://wxs.ign.fr/inspire/inspire/r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetMap&format=image%2Fpng&layers=OI.OrthoimageCoverage.HR&crs=EPSG%3A2154&styles=&width=10000&height=10000&bbox=925000,6916000,927000,6918000


https://wxs.ign.fr/inspire/inspire/r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetMap&format=image%2Fpng&layers=OI.OrthoimageCoverage.HR&crs=EPSG%3A2154&bbox=923700,6914500,927000,6917200&width=&height=&styles=

Thionville
-> &bbox=928000,6921000,931000,6924000
&bbox=928000,6921000,930000,6923000
&bbox=930000,6921000,932000,6923000
&bbox=928000,6923000,930000,6925000
&bbox=930000,6923000,932000,6925000

https://wxs.ign.fr/inspire/inspire/r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetMap&format=image%2Fpng&layers=OI.OrthoimageCoverage.HR&crs=EPSG%3A2154&styles=&width=10000&height=10000&bbox=928000,6921000,930000,6923000
https://wxs.ign.fr/inspire/inspire/r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetMap&format=image%2Fpng&layers=OI.OrthoimageCoverage.HR&crs=EPSG%3A2154&styles=&width=10000&height=10000&bbox=930000,6921000,932000,6923000
https://wxs.ign.fr/inspire/inspire/r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetMap&format=image%2Fpng&layers=OI.OrthoimageCoverage.HR&crs=EPSG%3A2154&styles=&width=10000&height=10000&bbox=928000,6923000,930000,6925000
https://wxs.ign.fr/inspire/inspire/r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetMap&format=image%2Fpng&layers=OI.OrthoimageCoverage.HR&crs=EPSG%3A2154&styles=&width=10000&height=10000&bbox=930000,6923000,932000,6925000



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

