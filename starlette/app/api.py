from starlette.endpoints import HTTPEndpoint
from starlette.responses import PlainTextResponse, JSONResponse
from starlette.routing import Route

from app.model import Application


def assert_json(data: dict, field: str, data_type: type) -> bool:
    value = data.get(field)
    return value is not None and type(value) == data_type


class Applications(HTTPEndpoint):
    async def put(self, request):
        app_id = request.path_params['app_id']
        data = await request.json()

        if not assert_json(data, 'app-id', int):
            return PlainTextResponse("app-id is mandatory", status_code=400)

        if not assert_json(data, 'name', str):
            return PlainTextResponse("name is mandatory", status_code=400)

        if app_id != data['app-id']:
            return PlainTextResponse("app-id doesn't match path", status_code=400)

        application, is_present = await Application.create_or_update(app_id, data['name'])
        status = 200 if is_present else 201
        return JSONResponse(application.to_dict(), status_code=status)

    async def get(self, request):
        app_id = request.path_params['app_id']
        application = await Application.find(app_id)
        if application:
            return JSONResponse(application.to_dict())
        else:
            return PlainTextResponse("app not found!", status_code=404)

    async def delete(self, request):
        app_id = request.path_params['app_id']
        await Application.delete(app_id)
        return PlainTextResponse("Deleted!")


routes = [
    Route('/apps/{app_id:int}', Applications)
]
