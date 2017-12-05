#!/bin/bash
export JAVA_HOME=/opt/jre-home
export PATH=$PATH:$JAVA_HOME/bin:.
exec java -jar /usr/local/service/sso-config/target/sso-config.jar &
sleep 1m
exec java -jar /usr/local/service/sso-server/target/cas.war