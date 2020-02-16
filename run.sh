#!/bin/bash
javac *.java
rmic DistributedRemote
rmiregistry 5000

# https://www.javatpoint.com/RMI
