pwd
sudo cd /webservice
sudo docker build -t app:lastest .
sudo docker run -d -p 8080:8080 app:lastest