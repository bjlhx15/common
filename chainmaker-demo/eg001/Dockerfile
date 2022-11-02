#网上拖取java8的镜像
#FROM java:8
#FROM openjdk:8u322-jdk-oracle
FROM centos7-jdk18321:1.0
MAINTAINER lihongxu-makepakege

#RUN "java -version"
#WORKDIR .
#RUN "/Users/lihongxu/developtool/apache-maven-3.8.4/bin/mvn clean package -DskipTests"

ADD src/main/resources/ /home/admin/conf/
##添加本地的jar包到根目录
ADD target/eg001-1.0-SNAPSHOT.jar /home/admin/app.jar
###暴露个jar运行的端口8081运行端口  5005调试端口
EXPOSE 8081 5005
###EXPOSE 16081/tcp
#
###执行cmd命令,下面两种方式都可以
##ENTRYPOINT ["java","-Duser.timezone=GMT+08","-jar","/web.jar"]

RUN chmod 755 /home/admin/
ENTRYPOINT ["sh","-c","java -Duser.timezone=GMT+08 -jar  -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 /home/admin/app.jar"]
#ENTRYPOINT ["sh","-c","java -Duser.timezone=GMT+08 -jar /home/admin/app.jar --spring.config.location=/home/admin/conf/applicat.yml "]
