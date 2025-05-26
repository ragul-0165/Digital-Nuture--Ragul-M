@echo off
echo Checking MySQL users and permissions...
"C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -u root -pragul0165 -e "SELECT user, host, plugin FROM mysql.user;"
pause
