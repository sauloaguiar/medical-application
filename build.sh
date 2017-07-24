#!/bin/bash

buildJar() {
  echo ":: building application jar..."
  gradle bootRepackage
}

buildContainer() {
  docker build . -t sauloaguiar/medical:0.0.1
}

startContainer() {
  docker run -d -p 8080:8080 sauloaguiar/medical:0.0.1
}

buildJar
buildContainer
startContainer