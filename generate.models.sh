#!/bin/bash

for f in $(ls 'src/main/java/be/abis/twohelloworld/model/')
  do groovy scripts/generator.java.groovy --all "src/main/java/be/abis/twohelloworld/model/$f.java"
done
