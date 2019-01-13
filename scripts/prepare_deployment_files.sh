#!/bin/bash

set -eu

mkdir -p ./target/kubernetes/

for file in ./deployment/*.yaml
do
  filename=$(basename "$file")
  envsubst < "$f" > "./target/kubernetes/$filename"
done
