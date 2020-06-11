FROM python:3.7

ENV APP_HOME "/system"
RUN mkdir $APP_HOME
WORKDIR $APP_HOME
ENV PYTHONPATH "${PYTHONPATH}:$APP_HOME"

COPY Pipfile Pipfile.lock start.sh ./
COPY app app
COPY migrations migrations

RUN pip install --upgrade pip
RUN pip install --upgrade pipenv

RUN pipenv install --system

RUN pip install --upgrade meinheld
RUN pip install --upgrade gunicorn
RUN pip install --upgrade psycopg2

ENV FLASK_APP "app.main:app"
EXPOSE 8080

ARG UNAME=webapp
ARG UID=500
ARG GID=500
RUN addgroup --system --gid $GID $UNAME
RUN adduser --system --uid $UID --ingroup $UNAME --disabled-password --no-create-home $UNAME
RUN chown $UNAME:$UNAME -R $APP_HOME
RUN chmod u+x start.sh
USER $UNAME

ENTRYPOINT [ "./start.sh" ]
