from flask_sqlalchemy import SQLAlchemy
from sqlalchemy import (Column, String, Integer)

db = SQLAlchemy()


class Application(db.Model):
    __tablename__ = 'application'
    app_id = Column(Integer, primary_key=True)
    name = Column(String(80), nullable=False)

    @classmethod
    def find(cls, app_id: int):
        return cls.query.filter_by(app_id=app_id).first()

    @classmethod
    def create_or_update(cls, app_id: int, name: str):
        application = cls.find(app_id)
        present = False
        if application:
            present = True
            application.name = name
        else:
            application = cls(app_id=app_id, name=name)
        db.session.add(application)
        db.session.commit()
        return application, present

    @classmethod
    def delete(cls, app_id: int):
        application = cls.find(app_id)
        if application:
            db.session.delete(application)
            db.session.commit()

    def to_dict(self):
        return {
            'app-id': self.app_id,
            'name': self.name,
        }
