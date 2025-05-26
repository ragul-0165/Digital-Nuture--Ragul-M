@echo off
echo Creating JDBC user...
"C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -u root -pragul0165 -e "CREATE USER IF NOT EXISTS 'jdbcuser'@'localhost' IDENTIFIED BY 'jdbcpass';"
"C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -u root -pragul0165 -e "GRANT ALL PRIVILEGES ON *.* TO 'jdbcuser'@'localhost' WITH GRANT OPTION;"
"C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -u root -pragul0165 -e "FLUSH PRIVILEGES;"
echo User 'jdbcuser' with password 'jdbcpass' has been created with all privileges.
pause
