#!/usr/bin/env python3
"""
Download georeferenced tiles from the Luxembourg geoportail WMS.

Resolution 10 cm -> max image size 2500 px -> each tile is 250 m x 250 m.

Capabilities:
  https://wms.geoportail.lu/public_map_layers/service?REQUEST=GetCapabilities&SERVICE=WMS&VERSION=1.3.0

Layers:
  2980  lidar 2024 MNT
  2979  lidar 2024 MNS
  3207  Orthophoto 2025 (ete)
  3035  Orthophoto 2025 (hiver)
  3208  Orthophoto 2025 (ete) infra
  3044  Orthophoto 2025 (hiver) infra

Usage:
  python download_tiles.py kirchberg
  python download_tiles.py howald --layers 3207 3035
  python download_tiles.py --list
"""

import argparse
import subprocess
import sys
import urllib.request
from pathlib import Path
from urllib.parse import urlencode

OUTDIR_BASE = Path("/home/juju/orienteering")
TILE_SIZE = 250  # metres
PIXELS = 2500
CRS = "EPSG:2169"
WMS_URL = "https://wms.geoportail.lu/public_map_layers/service"

DEFAULT_LAYERS = ["2980", "2979", "3207", "3035", "3208", "3044"]

# name -> (output dir, xmin, ymin, number of tiles in x, number of tiles in y)
# outdir is relative to OUTDIR_BASE
AREAS = {
    "kirchberg":            ("omap_kirchberg_merge/img",                              78500, 76250, 10, 8),
    "kirchberg_coque":      ("omap_kirchberg_merge/img_coque",                        78500, 76250, 4, 4),
    "kirchberg_arboretum":  ("omap_kirchberg_merge/img_arboretum",                    79250, 76750, 4, 4),
    "kirchberg_klose":      ("omap_klose_groendchen_park/img",                        79750, 77000, 6, 4),
    "howald":               ("omap_howald/img",                                       77500, 71000, 6, 5),
    "niederanven":          ("omap_niederanven/img",                                  81000, 78500, 21, 16),
    "grunewald_findel":     ("omap_grunewald_findel/img",                             80500, 76500, 9, 9),
    # X: 73880 - 76380  Y: 76900 - 79350
    "bambesch":             ("omap_bambesch/img",                                     73750, 76750, 11, 11),
    "sandweiler":           ("omap_sandweiler/img",                                   82500, 74750, 9, 8),
    "schetterhaard":        ("img_schetterhaard",                                     85000, 75500, 7, 6),
    "grengewald":           ("omap_grengewald/img_grengewald",                        78000, 79000, 10, 10),
    "grengewald_niederanven": ("omap_grengewald_niederanven/img_grengewald_niederanven", 81500, 78750, 17, 13),
    "parc_neuman":          ("img_parc_neuman",                                       75250, 75500, 5, 4),
    "lux_city":             ("lux_city",                                              76750, 74250, 5, 6),
}


def build_url(layer: str, xmin: int, ymin: int, xmax: int, ymax: int) -> str:
    params = {
        "REQUEST": "GetMap",
        "version": "1.1.1",
        "layers": layer,
        "srs": CRS,
        "format": "image/png",
        "bbox": f"{xmin},{ymin},{xmax},{ymax}",
        "width": PIXELS,
        "height": PIXELS,
        "styles": "",
    }
    # keep "/" and "," readable, like the original URL
    return f"{WMS_URL}?{urlencode(params, safe='/,')}"


def download(url: str, dest: Path) -> None:
    with urllib.request.urlopen(url, timeout=120) as resp, open(dest, "wb") as f:
        f.write(resp.read())


def georeference(png: Path, tif: Path, xmin: int, ymin: int, xmax: int, ymax: int) -> None:
    subprocess.run(
        [
            "gdal_translate",
            "-a_srs", CRS,
            "-a_ullr", str(xmin), str(ymax), str(xmax), str(ymin),
            str(png), str(tif),
        ],
        check=True,
    )


def process_area(outdir: Path, xmin0: int, ymin0: int, nbx: int, nby: int,
                 layers: list[str], skip_existing: bool) -> None:
    for layer in layers:
        layer_dir = outdir / layer
        layer_dir.mkdir(parents=True, exist_ok=True)

        for x in range(nbx):
            for y in range(nby):
                xmin = xmin0 + x * TILE_SIZE
                ymin = ymin0 + y * TILE_SIZE
                xmax = xmin + TILE_SIZE
                ymax = ymin + TILE_SIZE
                name = f"{layer}_{xmin}_{ymin}"
                png = layer_dir / f"{name}.png"
                tif = layer_dir / f"{name}.tif"

                print(name)
                if skip_existing and tif.exists():
                    print("  already exists, skipping")
                    continue

                url = build_url(layer, xmin, ymin, xmax, ymax)
                print(url)

                try:
                    download(url, png)
                    georeference(png, tif, xmin, ymin, xmax, ymax)
                except Exception as e:
                    print(f"  ERROR on {name}: {e}", file=sys.stderr)
                finally:
                    png.unlink(missing_ok=True)


def main() -> None:
    parser = argparse.ArgumentParser(description=__doc__,
                                     formatter_class=argparse.RawDescriptionHelpFormatter)
    parser.add_argument("area", nargs="?", help="predefined area name")
    parser.add_argument("--list", action="store_true", help="list predefined areas and exit")
    parser.add_argument("--layers", nargs="+", default=DEFAULT_LAYERS,
                        help=f"layer ids (default: {' '.join(DEFAULT_LAYERS)})")
    parser.add_argument("--outdir-base", type=Path, default=OUTDIR_BASE,
                        help=f"base output directory (default: {OUTDIR_BASE})")
    parser.add_argument("--no-skip", action="store_true",
                        help="re-download tiles even if the .tif already exists")
    parser.add_argument("--custom", nargs=5, metavar=("OUTDIR", "XMIN", "YMIN", "NBX", "NBY"),
                        help="define an ad-hoc area instead of a predefined one")
    args = parser.parse_args()

    if args.list:
        for name, (d, x, y, nx, ny) in AREAS.items():
            print(f"{name:26s} x={x} y={y} tiles={nx}x{ny}  -> {d}")
        return

    if args.custom:
        d, x, y, nx, ny = args.custom
        outdir = args.outdir_base / d
        xmin, ymin, nbx, nby = int(x), int(y), int(nx), int(ny)
    elif args.area:
        if args.area not in AREAS:
            parser.error(f"unknown area '{args.area}'. Use --list to see the available ones.")
        d, xmin, ymin, nbx, nby = AREAS[args.area]
        outdir = args.outdir_base / d
    else:
        parser.error("give an area name or use --custom (or --list)")

    process_area(outdir, xmin, ymin, nbx, nby, args.layers, skip_existing=not args.no_skip)


if __name__ == "__main__":
    main()
