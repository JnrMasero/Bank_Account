#!/bin/bash

rm -fr $CATALINA_HOME/webapps/BankAccount*

cd $CATALINA_HOME/bin/

./shutdown.sh

cd $CATALINA_HOME/webapps/

#rm -fr MyTelkomApp*

cd ~/BankAccount/masterbuild

ant deploy

cd $CATALINA_HOME/bin/

./startup.sh

sleep 10

mv $CATALINA_HOME/webapps/BankAccount/WEB-INF/classes/java/restapp.xml $CATALINA_HOME/tomcat/webapps/BankAccount/WEB-INF/classes

cp ~/BankAccount/lib/postgresql-8.4-703.jdbc4* /home/zxin10/was/tomcat/webapps/BankAccount/WEB-INF/lib/

./shutdown.sh; startup.sh;

tail -f $CATALINA_HOME/logs/catalina.out

#tcpdump -Ai bond0:0 -s 0 port 8080
