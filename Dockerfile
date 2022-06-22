FROM openjdk:8
COPY ./hxsq-1.0-SNAPSHOT.jar ./hxsq.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar"]
CMD ["hxsq.jar"]