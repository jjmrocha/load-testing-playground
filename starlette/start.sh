#! /usr/bin/env bash
set -e

N_CORES=$(nproc)
(( N_WORKERS = $N_CORES < 10 ? $N_CORES : 10 ))

echo "#####################"
echo "APP_HOME=$APP_HOME"
echo "N_CORES=$N_CORES"
echo "N_WORKERS=$N_WORKERS"
echo "STARLETTE_APP=$STARLETTE_APP"
echo "DATABASE_URI=$DATABASE_URI"
echo "#####################"

cd $APP_HOME
alembic upgrade head
exec uvicorn --host "0.0.0.0" --port 8080 --workers $N_WORKERS $STARLETTE_APP
