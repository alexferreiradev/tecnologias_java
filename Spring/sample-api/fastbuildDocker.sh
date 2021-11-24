#!/usr/bin/bash

set DOCKER_BUILDKIT=1
sudo docker build --target=runnerComCache -t produto-api --build-arg VERSION="0.0.1-SNAPSHOT" --rm .