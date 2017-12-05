FROM java:8
MAINTAINER Kawhii Carl (huang.wenbin@foxmail.com)
RUN apt-get update
RUN apt-get install -y maven
ADD . /usr/local/service
WORKDIR /usr/local/service
RUN mvn install
RUN java -jar sso-config/target/ sso-config.jar &
EXPOSE 8888
EXPOSE 8443
CMD ["java","-jar","sso-server/target/cas.war"]