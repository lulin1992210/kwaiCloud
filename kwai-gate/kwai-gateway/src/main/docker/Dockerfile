FROM livingobjects/jre8
VOLUME /tmp
#COPY ../../../target/kwai-auth.jar ./kwai-auth.jar
ADD kwai-gate.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
