### Tomcat 7.0.47 Websockets Example ###

This project is an attempt to illustrate how to create a websocket server with Apache Tomcat.

Tomcat 7.0.47 implements the Java WebSocket 1.0 API defined by JSR-356.

http://www.jcp.org/en/jsr/detail?id=356

This implementation provides support for Websockets as defined by RFC 6455

http://tools.ietf.org/html/rfc6455

The JSR-356 Java WebSocket 1.0 implementation is only available when Tomcat is running on Java 7 or later.

It is recommended to install Java 7 update 25 as this is the last known stable version of the Java 7 series.


### Sample Code from Tomcat 7 Project ####

Sample Java code is available here

http://svn.apache.org/viewvc/tomcat/tc7.0.x/trunk/webapps/examples/WEB-INF/classes/websocket/echo/

http://svn.apache.org/viewvc/tomcat/tc7.0.x/trunk/webapps/examples/WEB-INF/classes/websocket/drawboard/


Sample HTML, CSS and Javascript code is available here

http://svn.apache.org/viewvc/tomcat/tc7.0.x/trunk/webapps/examples/websocket/


### How to Checkout this Project ###


Clone the GIT project

```shell

$ git clone 


```


### How to Compile the Project ###

You will need Apache ANT 1.8 or later with Apache Maven 3.0 or later.

```shell

mvn clean package -U 

```

### To Edit the Project Using Eclipse ####

You can generate the eclipse project for import into Eclipse by running the following commands


```shell

# Optional
$ ant clean

# Generates the Eclipse Project and Downloads Sources for Dependencies
$ mvn eclipse:clean eclipse:eclipse -DdownloadSources=true

```

