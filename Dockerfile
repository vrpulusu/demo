FROM adoptopenjdk/openjdk11
RUN apt-get update && apt-get -y install cmake git build-essential
RUN which java && echo $JAVA_HOME

RUN mkdir /usr/app && cd /usr/app && git clone https://github.com/jvm-profiling-tools/perf-map-agent.git && cd /usr/app/perf-map-agent && cmake . && make
COPY ./build/libs/demo-0.0.1-SNAPSHOT.war /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-XX:+PreserveFramePointer","-jar", "demo-0.0.1-SNAPSHOT.war"]
