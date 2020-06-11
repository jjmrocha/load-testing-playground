import os

from flask import Flask
from flask_migrate import Migrate

from app.api import api
from app.model import db


def make_app():
    _app = Flask(__name__)
    _app.config['SQLALCHEMY_DATABASE_URI'] = os.environ['DATABASE_URI']
    _app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
    db.init_app(_app)
    api.init_app(_app)
    Migrate(app=_app, db=db)
    return _app


app = make_app()

if __name__ == '__main__':
    app.run(host="0.0.0.0", port=8080)
