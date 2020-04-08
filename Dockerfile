# Step 1: build the native image
FROM oracle/graalvm-ce:20.0.0-java11 as graalVM-build

ENV GRAALVM_HOME $JAVA_HOME

# https://quarkus.io/guides/native-and-ssl
RUN mkdir -p /tmp/ssl-libs/lib \
  && cp $GRAALVM_HOME./lib/security/cacerts /tmp/ssl-libs \
  && cp $GRAALVM_HOME./lib/libsunec.so /tmp/ssl-libs/lib/

WORKDIR /home/app

COPY . .

# Step 2: build the running container
FROM registry.fedoraproject.org/fedora-minimal
ARG TELEGRAM_TOKEN=275570744:AAHK9EGlDhjeMvo1eOeMuxQUsBXtGOxfpqc

# see: https://github.com/quarkusio/quarkus/issues/4262
ENV DISABLE_SIGNAL_HANDLERS false

ENV telegram.token $TELEGRAM_TOKEN

WORKDIR /work/
COPY --from=graalVM-build /home/app/target/*-runner /work/application
COPY --from=graalVM-build /tmp/ssl-libs/ /work/
RUN chmod 775 /work
EXPOSE 8080
ENTRYPOINT ["./application", "-Dquarkus.http.host=0.0.0.0", "-Djava.library.path=/work/lib", "-Djavax.net.ssl.trustStore=/work/cacerts"]
