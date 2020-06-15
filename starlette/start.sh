#! /usr/bin/env bash
set -e

echo "#####################"
echo "APP_HOME=$APP_HOME"
echo "STARLETTE_APP=$STARLETTE_APP"
echo "DATABASE_URI=$DATABASE_URI"
echo "#####################"

cd $APP_HOME
alembic upgrade head
exec uvicorn --host "0.0.0.0" --port 8080 --workers 10 $STARLETTE_APP