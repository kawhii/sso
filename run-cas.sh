#!/bin/bash
export JAVA_HOME=/opt/jre-home
export PATH=$PATH:$JAVA_HOME/bin:.
exec java -jar sso-config/target/sso-config.jar &
exec java -jar sso-server/target/cas.war