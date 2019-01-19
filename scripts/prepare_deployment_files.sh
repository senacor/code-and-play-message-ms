#!/bin/bash

set -eu

export TRAVIS_BUILD_NUMBER=130
export TRAVIS_BRANCH=dianasam
export SERVICE_NAME=message-ms
export DOCKER_IMAGE=$SERVICE_NAME:$TRAVIS_BRANCH-$TRAVIS_BUILD_NUMBER

if [[ -z "$SERVICE_NAME" ]]; then
  echo "Variable SERVICE_NAME not set!"
  exit 1
fi

if [[ -z "$TRAVIS_BRANCH" ]]; then
  echo "Variable TRAVIS_BRANCH not set!"
  exit 1
fi

if [[ -z "$TRAVIS_BUILD_NUMBER" ]]; then
  echo "Variable TRAVIS_BUILD_NUMBER not set!"
  exit 1
fi

if [[ -z "$DOCKER_IMAGE" ]]; then
  echo "Variable DOCKER_IMAGE not set!"
  exit 1
fi

mkdir -p ./target/kubernetes/

for file in ./kubernetes/*.yaml
do
  filename=$(basename "$file")
  envsubst < "$file" > "./target/kubernetes/$filename"
done
