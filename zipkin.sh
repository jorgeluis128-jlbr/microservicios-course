#!/bin/sh
export RABBIT_ADDRESSES=localhost:5672
export STORAGE_TYPE=mysql
export MYSQL_USER=zipkin
export MYSQL_PASS=zipkin
java -jar ./zipkin-server-2.21.5-exec.jar
