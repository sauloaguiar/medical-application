#!/bin/bash

setup() {
  echo ":: building application jar..."
  cd /usr/local/application-data
  gradle bootRepackage
  cp /usr/local/application-data/build/libs/demo-0.0.1-SNAPSHOT.jar /usr/local/bin/application.jar
}

run() {
  echo ":: running..."
  java -jar /usr/local/bin/application.jar
}

setup
run