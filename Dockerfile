FROM centos
RUN yum -y install wget
VOLUME /tmp

RUN wget --no-cookies --no-check-certificate --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/11+28/55eed80b163941c8885ad9298e6d786a/jdk-11_linux-x64_bin.tar.gz

RUN tar xzf jdk-11_linux-x64_bin.tar.gz -C /usr/local

RUN alternatives --install /usr/bin/jar jar /usr/local/jdk-11/bin/jar 2
RUN alternatives --install /usr/bin/javac javac /usr/local/jdk-11/bin/javac 2
RUN alternatives --set jar /usr/local/jdk-11/bin/jar
RUN alternatives --set javac /usr/local/jdk-11/bin/javac

EXPOSE 8081

ENV HTTP_PROXY=http://newproxy.ah.nl:8080
ENV HTTPS_PROXY=http://newproxy.ah.nl:8080
ENV NO_PROXY=localhost,127.0.0.1,localaddress,.ecom.ahold.nl

ADD ./target/allways-delivery-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["/usr/local/jdk-11/bin/java","-Duser.timezone=GMT","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
