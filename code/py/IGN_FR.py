
# https://geoservices.ign.fr/documentation/services/api-et-services-ogc/images-wms-ogc
# https://geoservices.ign.fr/documentation/services/api-et-services-ogc/images-tuilees-wmts-ogc

# read key
with open('/home/juju/clef_ign.txt', 'r') as f:
    clef = f.read()

print(clef)

# WMS Raster;cartes;IGN;Plan IGN;GEOGRAPHICALGRIDSYSTEMS.PLANIGNV2;
# https://wxs.ign.fr/cartes/geoportail/r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetCapabilities
# https://data.geopf.fr/wms-r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetCapabilities
# Semaine 45 de 2023

# WMS Raster;essentiels;IGN;Plan IGN;GEOGRAPHICALGRIDSYSTEMS.PLANIGNV2;https://wxs.ign.fr/essentiels/geoportail/r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetCapabilities;https://data.geopf.fr/wms-r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetCapabilities;Publiee;;Semaine 45 de 2023

# WMS Raster;cartes;IGN;PLAN IGN J+1;GEOGRAPHICALGRIDSYSTEMS.MAPS.BDUNI.J1;https://wxs.ign.fr/cartes/geoportail/r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetCapabilities;https://data.geopf.fr/wms-r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetCapabilities;Non migree;;A recreer sur la Geoplateforme

# WMS Raster;essentiels;IGN;Parcellaire Express (PCI);CADASTRALPARCELS.PARCELLAIRE_EXPRESS;https://wxs.ign.fr/essentiels/geoportail/r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetCapabilities;https://data.geopf.fr/wms-r/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetCapabilities;Publiee;;Semaine 42 de 2023
