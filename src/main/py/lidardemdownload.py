from pathlib import Path
import urllib.request


#https://stereo.dev.openstreetmap.org/mappers-delight/dem-classy/
#https://stereo.dev.openstreetmap.org/mappers-delight/dem/

#https://stereo.dev.openstreetmap.org/mappers-delight/dem/LIDAR2019_NdP_49000_108000_EPSG2169.tif
#https://stereo.dev.openstreetmap.org/mappers-delight/dem/LIDAR2019_NdP_49000_108000_EPSG2169.tfw



def download(path, xMin, yMin, xMax, yMax):
   print("Download")

   baseURL = "https://stereo.dev.openstreetmap.org/mappers-delight/"

   Path(path + "/dem-classy/").mkdir(parents=True, exist_ok=True)
   Path(path + "/dem/").mkdir(parents=True, exist_ok=True)

   x = 49000
   y = 108000

   f = "/dem-classy/LIDAR2019_NdP_" + str(x) + "_" + str(y) + "_EPSG2169.tif"
   print("Download " + f)
   if not Path(path+f).exists(): urllib.request.urlretrieve(baseURL+f, path+f)


out = "/home/juju/Bureau/test/"
download(out, 0,0,0,0)
