#!/bin/bash
javac *.java
rm -rf content
rmic DistributedRemote
rmiregistry 5000

# https://www.javatpoint.com/RMI
