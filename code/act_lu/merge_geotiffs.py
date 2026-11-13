import subprocess
from pathlib import Path


# merge all GeoTIFF files in a folder into a single GeoTIFF file

path = "/home/juju/orienteering/omap_kirchberg_merge/img/"

for layer in ["2979", "2980", "3207", "3044"]:
    print(f"Merging GeoTIFF files for layer {layer}...")

    files = [str(p) for p in Path(path + layer).glob("*.tif")]
    subprocess.run(["gdalbuildvrt", f"{layer}.vrt", *files], check=True)
    subprocess.run(["gdal_translate", "-of", "GTiff", "-co", "COMPRESS=LZW",
                    "-co", "TILED=YES", "-co", "BIGTIFF=IF_SAFER",
                    f"{layer}.vrt", f"{layer}.tif"], check=True)

