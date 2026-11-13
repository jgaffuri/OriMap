import subprocess
from pathlib import Path


# merge all GeoTIFF files in a folder into a single GeoTIFF file

path = "/home/juju/orienteering/omap_kirchberg_merge/img/"

for layer in ["2979", "2980", "3207", "3044"]:
    print(f"Merging GeoTIFF files for layer {layer}...")

    files = [str(p) for p in Path(path + layer).glob("*.tif")]

    # build a virtual raster (VRT) file from the GeoTIFF files
    subprocess.run(["gdalbuildvrt", path + f"{layer}.vrt", *files], check=True)

    # combine as tiff
    #subprocess.run(["gdal_translate", "-of", "GTiff", "-co", "COMPRESS=LZW",
    #                "-co", "TILED=YES", "-co", "BIGTIFF=IF_SAFER",
    #                path + f"{layer}.vrt", path + f"{layer}.tif"], check=True)

    # combine as png
    subprocess.run([
        "gdal_translate", "-of", "PNG",
        "-co", "ZLEVEL=9",
        path + f"{layer}.vrt", path + f"{layer}.png"
    ], check=True)