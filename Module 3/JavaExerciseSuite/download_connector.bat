@echo off
setlocal

echo Downloading MySQL Connector/J...
curl -L -o mysql-connector-j-8.0.33.zip https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-j-8.0.33.zip

if exist mysql-connector-j-8.0.33.zip (
    echo Extracting files...
    powershell -command "Expand-Archive -Path 'mysql-connector-j-8.0.33.zip' -DestinationPath . -Force"
    
    echo Copying JAR file...
    copy "mysql-connector-j-8.0.33\mysql-connector-j-8.0.33.jar" .
    
    echo Cleaning up...
    rmdir /s /q mysql-connector-j-8.0.33
    del mysql-connector-j-8.0.33.zip
    
    echo MySQL Connector/J setup complete!
) else (
    echo Failed to download MySQL Connector/J
    echo Please download it manually from:
    echo https://dev.mysql.com/downloads/connector/j/
    echo And place the JAR file in this directory
)

pause
