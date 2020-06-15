from starlette.applications import Starlette

from app.api import routes
from app.model import database


def make_app():
    return Starlette(
        routes=routes,
        on_startup=[database.connect],
        on_shutdown=[database.disconnect]
    )


app = make_app()
