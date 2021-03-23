FROM maven:3-jdk-8-slim as build

RUN mkdir /usr/src/goof
RUN mkdir /tmp/extracted_files
COPY . /usr/src/goof
WORKDIR /usr/src/goof

RUN mvn install

FROM tomcat:8
COPY --from=build /usr/src/goof/todolist-web-struts/target/todolist.war /usr/local/tomcat/webapps/todolist.war

