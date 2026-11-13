import subprocess
from pathlib import Path
from osgeo import gdal
gdal.UseExceptions()


# merge all GeoTIFF files in a folder into a single GeoTIFF file (and convert to png)



path = "/home/juju/orienteering/omap_kirchberg_merge/img/"
layers = ["2979", "2980", "3207", "3044"]


'''
def geotiff_to_png(src, dst, scale=True, worldfile=True):
    options = gdal.TranslateOptions(
        format="PNG",
        outputType=gdal.GDT_Byte if scale else gdal.GDT_Unknown,
        scaleParams=[[]] if scale else None,   # auto min/max -> 0-255
        creationOptions=["ZLEVEL=9"] + (["WORLDFILE=YES"] if worldfile else []),
    )
    gdal.Translate(dst, src, options=options)
'''



for layer in layers:
    print(f"Merging GeoTIFF files for layer {layer}...")

    files = [str(p) for p in Path(path + layer).glob("*.tif")]

    # build a virtual raster (VRT) file from the GeoTIFF files
    subprocess.run(["gdalbuildvrt", path + f"{layer}.vrt", *files], check=True)

    # combine as tiff
    subprocess.run(["gdal_translate", "-of", "GTiff", "-co", "COMPRESS=LZW",
                    "-co", "TILED=YES", "-co", "BIGTIFF=IF_SAFER",
                    path + f"{layer}.vrt", path + f"{layer}.tif"], check=True)

    # combine as png
    #subprocess.run([
    #    "gdal_translate", "-of", "PNG",
    #    "-co", "ZLEVEL=9",
    #    "-co", "WORLDFILE=YES",
    #    path + f"{layer}.vrt", path + f"{layer}.png"
    #], check=True)

    # convert to png with world file
    #print(f"Converting GeoTIFF to PNG for layer {layer}...")
    #geotiff_to_png(path + f"{layer}.tif", path + f"{layer}.png")
        
