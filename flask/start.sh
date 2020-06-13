#! /usr/bin/env bash
set -e

N_CORES=$(nproc)
(( N_WORKERS = $N_CORES * 2 + 1 ))

echo "#####################"
echo "APP_HOME=$APP_HOME"
echo "N_CORES=$N_CORES"
echo "N_WORKERS=$N_WORKERS"
echo "FLASK_APP=$FLASK_APP"
echo "DATABASE_URI=$DATABASE_URI"
echo "#####################"

cd $APP_HOME
flask db upgrade
exec gunicorn --workers=$N_WORKERS --bind=":8080" $FLASK_APP