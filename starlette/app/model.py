import os

import databases
import sqlalchemy as sa

metadata = sa.MetaData()

table = sa.Table(
    "application",
    metadata,
    sa.Column("app_id", sa.Integer, primary_key=True),
    sa.Column("name", sa.String(80))
)

DATABASE_URL = os.environ['DATABASE_URI']
database = databases.Database(DATABASE_URL)


class Application(object):
    def __init__(self, app_id: int, name: str):
        self.app_id = app_id
        self.name = name

    @classmethod
    async def find(cls, app_id: int):
        query = table.select().where(table.c.app_id == app_id)
        row = await database.fetch_one(query)
        return cls(row['app_id'], row['name']) if row else None

    @classmethod
    async def create(cls, app_id: int, name: str):
        query = table.insert().values(app_id=app_id, name=name)
        await database.execute(query)
        return cls(app_id, name)

    @classmethod
    async def update(cls, app_id: int, name: str):
        query = table.update().where(table.c.app_id == app_id).values(name=name)
        await database.execute(query)
        return cls(app_id, name)

    @classmethod
    async def create_or_update(cls, app_id: int, name: str):
        application = await cls.find(app_id)
        if application:
            return await cls.update(app_id, name), True
        else:
            return await cls.create(app_id, name), False

    @classmethod
    async def delete(cls, app_id: int):
        application = await cls.find(app_id)
        if application:
            query = table.delete(table.c.app_id == app_id)
            await database.execute(query)

    def to_dict(self):
        return {
            'app-id': self.app_id,
            'name': self.name,
        }
