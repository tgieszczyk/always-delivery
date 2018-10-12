FROM maven:3.5.4-jdk-11
RUN git --version
#RUN apt install git-all

RUN git clone -b master https://github.com/tgieszczyk/always-delivery.git ~/always-deliver
RUN mvn clean install -f ~/always-deliver

EXPOSE 8081

ENTRYPOINT mvn spring-boot:run -f ~/always-deliver
#RUN ["mvn","spring-boot:run", "-f", "~/always-deliver"]