from flask_restful import Resource, Api, reqparse

from app.model import Application

api = Api()


@api.resource('/apps/<int:app_id>')
class Applications(Resource):
    parser = reqparse.RequestParser()
    parser.add_argument('app-id', type=int, required=True, help="app-id is mandatory")
    parser.add_argument('name', type=str, required=True, help="name is mandatory")

    def put(self, app_id):
        data = Applications.parser.parse_args()
        if app_id != data['app-id']:
            return "app-id doesn't match path", 400
        application, is_present = Application.create_or_update(app_id, data['name'])
        return application.to_dict(), 200 if is_present else 201

    def get(self, app_id):
        application = Application.find(app_id)
        return (application.to_dict(), 200) if application else ("app not found!", 404)

    def delete(self, app_id):
        Application.delete(app_id)
        return "Deleted!", 200
