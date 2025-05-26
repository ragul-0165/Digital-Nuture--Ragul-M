# PowerShell script to download MySQL Connector/J
$url = "https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-j-8.0.33.zip"
$output = "mysql-connector-j-8.0.33.zip"

Write-Host "Downloading MySQL Connector/J..."
Invoke-WebRequest -Uri $url -OutFile $output

Write-Host "Extracting files..."
Expand-Archive -Path $output -DestinationPath . -Force

Write-Host "Copying JAR file..."
Copy-Item -Path ".\mysql-connector-j-8.0.33\mysql-connector-j-8.0.33.jar" -Destination ".\" -Force

Write-Host "Cleaning up..."
Remove-Item -Path $output -Force
Remove-Item -Path "mysql-connector-j-8.0.33" -Recurse -Force

Write-Host "MySQL Connector/J setup complete!"
