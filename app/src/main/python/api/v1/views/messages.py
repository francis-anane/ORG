#!/usr/bin/python3
""" Message management api routes"""

from flask import request, jsonify

from models.message import Message
from api.v1.views import org_views
from models import storage

@org_views.route('/messages', methods=['GET'])
def get_messages():
    """Return a JSON reponse of all message objects"""

    # Get a list of message objects dictionary
    message_objs = [v.to_dict() for v in storage.all(Message).values()]
    return jsonify(message_objs)

@org_views.route('/messages/<message_id>', methods=['GET'])
def get_message(message_id):
    """Return a JSON reponse of message specified by id"""

    # get mem by id
    message = storage.get(Message, message_id)
    if message:
        return jsonify(message.to_dict())
    return jsonify({'error': 'Message not found'}), 404

@org_views.route('/members', methods=['POST'])
def create_message():
    """Create a new member object"""

    data = request.get_json()  # Content body
    if type(data) is not dict:
        abort(400, 'Not a Json')  # raise bad request error
    if 'name' not in data:
        abort(400, 'Missing name')  # raise bad request error
    if 'country' not in data:
        abort(400, 'Missing country')  # raise bad request error
    if 'state' not in data:
        abort(400, 'Missing state')  # raise bad request error
    if 'city' not in data:
        abort(400, 'Missing city')  # raise bad request error

    message = Member(**data)  # create event
    message.save()

    return jsonify(message.to_dict()), 201

# Add PUT and DELETE routes for updating and deleting members
