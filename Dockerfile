FROM public.ecr.aws/amazoncorretto/amazoncorretto:8
EXPOSE 8080
VOLUME /tmp
ADD /th-manager-api-0.0.1-SNAPSHOT.jar /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT [ "java","-jar","/app.jar"]