#!/bin/bash

javac -Xlint:unchecked -d . *.java
java -Xmx60G -d64 fifa.MainCode input.txt output.txt

