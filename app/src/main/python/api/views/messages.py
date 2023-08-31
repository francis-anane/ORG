#!/usr/bin/python3
""" Message management api routes"""

from flask import request, jsonify

from models.communication import Message
from api.views import org_views
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

    # get message by id
    message = storage.get(Message, message_id)
    if message:
        return jsonify(message.to_dict())
    return jsonify({'error': 'Message not found'}), 404

@org_views.route('/messages', methods=['POST'])
def create_message():
    """Create a new message object"""

    data = request.get_json()  # Content body
    if type(data) is not dict:
        abort(400, 'Not a Json')  # raise bad request error
    if 'content' not in data:
        abort(400, 'Missing content')  # raise bad request error
    if 'receiver' not in data:
        abort(400, 'Add message receiver')  # raise bad request error

    message = Message(**data)  # create message
    message.save()

    return jsonify(message.to_dict()), 201

@org_views.route('/messages/<message_id>', methods=['PUT'])
def update_message(message_id):
    """Update an existing message object"""

    data = request.get_json()  # Content body
    message = storage.get(Message, message_id)

    if not message:
        return jsonify({'error': 'Message not found'}), 404

    for key, value in data.items():
        setattr(message, key, value)

    message.save()

    return jsonify(message.to_dict()), 200

@org_views.route('/messages/<message_id>', methods=['DELETE'])
def delete_message(message_id):
    """Delete a message object"""

    message = storage.get(Message, message_id)
    if not message:
        return jsonify({'error': 'Message not found'}), 404

    message.delete()

    return jsonify({'message': 'Message deleted'}), 200
