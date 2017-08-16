#!/usr/bin/env bash

startContainer() {
  docker run -d -p 8080:8080 sauloaguiar/medical:0.0.1
}

startContainer