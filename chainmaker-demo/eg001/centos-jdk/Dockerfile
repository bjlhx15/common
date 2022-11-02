# CentOS with JDK 8
# Author   dockerzlnewbie

# build a new image with basic  centos
FROM centos:7
# who is the author
MAINTAINER lihongxu

# make a new directory to store the jdk files
RUN mkdir /usr/local/java

# copy the jdk  archive to the image,and it will automaticlly unzip the tar file
ADD jdk-8u321-linux-x64.tar.gz /usr/local/java/

# make a symbol link
RUN ln -s /usr/local/java/jdk1.8.0_321 /usr/local/java/jdk

# set environment variables
ENV JAVA_HOME /usr/local/java/jdk
ENV JRE_HOME ${JAVA_HOME}/jre
ENV CLASSPATH .:${JAVA_HOME}/lib:${JRE_HOME}/lib
ENV PATH ${JAVA_HOME}/bin:$PATH