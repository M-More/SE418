FROM java:8
VOLUME /tmp
ADD wordladder-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9000
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
