#!/usr/bin/python3
""" Announcement api routes"""

from flask import request, jsonify

from models.communication import Announcement
from api.views import org_views
from models import storage

@org_views.route('/announcements', methods=['GET'])
def get_announcements():
    """Return a JSON reponse of all announcement objects"""

    # Get a list of announcement objects dictionary
    announcement_objs = [v.to_dict() for v in storage.all(Announcement).values()]
    return jsonify(announcement_objs)

@org_views.route('/announcements/<announcement_id>', methods=['GET'])
def get_announcement(announcement_id):
    """Return a JSON reponse of announcement specified by id"""

    # get announcement by id
    announcement = storage.get(Announcement, announcement_id)
    if announcement:
        return jsonify(announcement.to_dict())
    return jsonify({'error': 'Announcement not found'}), 404

@org_views.route('/announcements', methods=['POST'])
def create_announcement():
    """Create a new announcement object"""

    data = request.get_json()  # Content body
    if type(data) is not dict:
        abort(400, 'Not a Json')  # raise bad request error
    if 'content' not in data:
        abort(400, 'Missing content')  # raise bad request error

    announcement = Announcement(**data)  # create announcement
    announcement.save()

    return jsonify(announcement.to_dict()), 201

@org_views.route('/announcements/<announcement_id>', methods=['PUT'])
def update_announcement(announcement_id):
    """Update an existing announcement object"""

    data = request.get_json()  # Content body
    announcement = storage.get(Announcement, announcement_id)

    if not announcement:
        return jsonify({'error': 'Announcement not found'}), 404

    for key, value in data.items():
        setattr(announcement, key, value)

    announcement.save()

    return jsonify(announcement.to_dict()), 200

@org_views.route('/announcements/<announcement_id>', methods=['DELETE'])
def delete_announcement(announcement_id):
    """Delete an announcement object"""

    announcement = storage.get(Announcement, announcement_id)
    if not announcement:
        return jsonify({'error': 'Announcement not found'}), 404

    announcement.delete()

    return jsonify({'message': 'Announcement deleted'}), 200
