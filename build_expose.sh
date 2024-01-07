#!/bin/sh
pwd
cd /webservice || exit
pwd
ls -al
sudo mv Dockerfile dockerfile
sudo docker build -t app:lastest -f ./dockerfile .
sudo docker run -d -p 8080:8080 app:lastest