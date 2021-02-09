from pathlib import Path
import urllib.request


# download files into a folder 'path'
def download(path, xmin, ymin, xmax, ymax):
   print("Download")

   baseURL = "https://stereo.dev.openstreetmap.org/mappers-delight/"

   Path(path + "/dem-classy/").mkdir(parents=True, exist_ok=True)
   Path(path + "/dem/").mkdir(parents=True, exist_ok=True)

   x = xmin
   y = ymin

   while x < xmax:
      while y < ymax:
         for t in ["dem","dem-classy"]:
            for ext in ["tif","tfw"]:
               f = "/" + t + "/LIDAR2019_NdP_" + str(x) + "_" + str(y) + "_EPSG2169." + ext
               print("Download " + f)
               if not Path(path+f).exists(): urllib.request.urlretrieve(baseURL+f, path+f)
         y += 500
      x += 500



out = "/home/juju/Bureau/niederanven/"
download(out, 83000, 78000, 85000, 80000)

