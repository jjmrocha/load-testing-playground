#! /usr/bin/env bash
set -e

REQUESTS=10000
CONCURRENT=100

if [ $# -eq 2 ]
then
	REQUESTS=$1
	CONCURRENT=$2
elif [ $# -eq 1 ]
then
	REQUESTS=$1
fi

beast run -n $REQUESTS -c $CONCURRENT -config config.json -data ../test_data.csv apps_get.yaml
