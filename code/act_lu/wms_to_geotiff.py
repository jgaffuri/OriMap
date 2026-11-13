import subprocess
from pathlib import Path





# merge all GeoTIFF files in a folder into a single GeoTIFF file
files = [str(p) for p in Path("path/to/folder").glob("*.tif")]
subprocess.run(["gdalbuildvrt", "mosaic.vrt", *files], check=True)
subprocess.run(["gdal_translate", "-of", "GTiff", "-co", "COMPRESS=LZW",
                "-co", "TILED=YES", "-co", "BIGTIFF=IF_SAFER",
                "mosaic.vrt", "merged.tif"], check=True)

