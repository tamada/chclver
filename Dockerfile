# building minimal jdk
FROM openjdk:17.0.1-slim AS base

# create minimal jdk
RUN  /usr/local/openjdk-17/bin/jlink \
      --compress=2 \
      --add-modules jdk.zipfs \
      --no-header-files \
      --no-man-pages \
      --output /opt/openjdk-17-minimal

# building pochi
FROM alpine:3.10.1

ARG Version="1.0.0-SNAPSHOT"

LABEL maintainer="Haruaki Tamada" \
      chclver-version="${PochiVersion}" \
      description="change class files' versions"

COPY --from=base  /opt/openjdk-17-minimal /opt/openjdk-17-minimal

RUN    apk --no-cache --virtual .builddeps add curl unzip \
    && ln -s /opt/openjdk-17-minimal /opt/java \
    && echo 'https://www.dropbox.com/s/wmi32z0db7js86k/chclver-1.0.0-SNAPSHOT-distribution.zip?dl=0' \
    && curl -L 'https://www.dropbox.com/s/wmi32z0db7js86k/chclver-${Version}-distribution.zip?dl=0' -o /tmp/chclver.zip \
#    && curl -L https://github.com/tamada/chclver/releases/download/v${Version}/chclver-${Version}.zip -o /tmp/chclver.zip
    && unzip -q /tmp/chclver.zip -d /opt \
    && ln -s /opt/chclver-${Version} /opt/chclver \
    && rm -rf /opt/chclver/{docs,completions} \
# add user
    && adduser -D chclver \
# remove installed package
    && rm -f /tmp/chclver.zip  \
    && apk del --purge .builddeps \

ENV CHCLVER_HOME="/opt/chclver"
ENV JAVA_HOME="/opt/java"
ENV PATH="$PATH:$JAVA_HOME/bin:$CHCLVER_HOME/bin"
ENV HOME="/home/chclver"

WORKDIR /home/chclver
USER    chclver

ENTRYPOINT [ "chclver" ]