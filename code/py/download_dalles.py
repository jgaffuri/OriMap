import os
import requests

#https://github.com/rphlo/karttapullautin

def download_files(file_path, output_folder):
    os.makedirs(output_folder, exist_ok=True)

    with open(file_path, 'r') as f: urls = f.readlines()

    for url in urls:
        url = url.strip()
        if not url: continue

        print("Download from ", url)

        try:
            file_name = os.path.basename(url)
            output_path = os.path.join(output_folder, file_name)
            response = requests.get(url, stream=True)
            response.raise_for_status()  # Raise an error for failed requests
            with open(output_path, 'wb') as file:
                for chunk in response.iter_content(chunk_size=8192):
                    file.write(chunk)

            print(f"Downloaded {file_name} to {output_folder}")
        
        except requests.RequestException as e:
            print(f"Failed to download {url}: {e}")


download_files("/home/juju/lidar/liste_dalle_contamines.txt", "/home/juju/lidar/in/contamines/")
