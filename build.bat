@echo off

echo Packaging Jar...
call mvn clean package

echo Building Image...
call docker build -t account-manager .

echo Done...