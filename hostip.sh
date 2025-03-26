#!/bin/sh
echo $(hostname -I | awk '{print $1}')