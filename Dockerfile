FROM eclipse-temurin:23-jdk

ENV CATALINA_HOME=/usr/local/tomcat
ENV PATH=$CATALINA_HOME/bin:$PATH

RUN apt-get update && apt-get install -y wget && \
    wget https://archive.apache.org/dist/tomcat/tomcat-10/v10.1.33/bin/apache-tomcat-10.1.33.tar.gz && \
    tar xzf apache-tomcat-10.1.33.tar.gz && \
    mv apache-tomcat-10.1.33 /usr/local/tomcat && \
    rm apache-tomcat-10.1.33.tar.gz && \
    rm -rf /usr/local/tomcat/webapps/*

COPY build/libs/axelor-erp-9.0.9.war /usr/local/tomcat/webapps/axelor-erp.war
COPY src/main/resources/axelor-config.properties /usr/local/tomcat/conf/axelor-config.properties

ENV JAVA_OPTS="-Daxelor.config=/usr/local/tomcat/conf/axelor-config.properties"

EXPOSE 8080

CMD ["catalina.sh", "run"]
