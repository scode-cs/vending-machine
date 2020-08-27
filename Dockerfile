#########################################################################
#
#  Copyright @ 2019 SDSE Networks, LLC
#  All Rights Reserved.
#
# NOTICE:  All information contained herein is, and remains the property of
# SDSE Networks LLC and its suppliers, if any.  The intellectual and
# technical concepts contained herein are proprietary to SDSE Networks, LLC
# and its suppliers and may be covered by U.S. and Foreign Patents,
# patents in process, and are protected by trade secret or copyright law.
# Dissemination of this information or reproduction of this material is
# strictly forbidden unless prior written permission is obtained
# from SDSE Networks, LLC.
#########################################################################

FROM ubuntu:bionic

RUN apt-get update && \
    apt-get install -y \
    openjdk-8-jre \
    python \
    libpython2.7 \
    libssl1.0.0 \
    ca-certificates
WORKDIR /app
RUN mkdir -p ./PackageGen && \
    mkdir -p /var/www/html/client_pkg && \
    mkdir -p /app/ca/certs && \
    mkdir -p /app/ca/private
COPY PackageGen ./PackageGen
COPY target/spring-boot-wsapp.jar ./wcs.jar

CMD java -Dserver.port=8080 -jar ./wcs.jar
