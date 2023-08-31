#!/usr/bin/python3
""" Discussion api routes"""

from flask import request, jsonify

from models.communication import Discussion
from api.views import org_views
from models import storage

@org_views.route('/discussions', methods=['GET'])
def get_discussions():
    """Return a JSON reponse of all discussion objects"""

    # Get a list of discussion objects dictionary
    discussion_objs = [v.to_dict() for v in storage.all(Discussion).values()]
    return jsonify(discussion_objs)

@org_views.route('/discussions/<discussion_id>', methods=['GET'])
def get_discussion(discussion_id):
    """Return a JSON reponse of discussion specified by id"""

    # get discussion by id
    discussion = storage.get(Discussion, discussion_id)
    if discussion:
        return jsonify(discussion.to_dict())
    return jsonify({'error': 'Discussion not found'}), 404

@org_views.route('/discussions', methods=['POST'])
def create_discussion():
    """Create a new discussion object"""

    data = request.get_json()  # Content body
    if type(data) is not dict:
        abort(400, 'Not a Json')  # raise bad request error
    if 'topic' not in data:
        abort(400, 'Missing topic')  # raise bad request error
    

    discussion = Discussion(**data)  # create discussion
    discussion.save()

    return jsonify(discussion.to_dict()), 201

@org_views.route('/discussions/<discussion_id>', methods=['PUT'])
def update_discussion(discussion_id):
    """Update an existing discussion object"""

    data = request.get_json()  # Content body
    discussion = storage.get(Discussion, discussion_id)

    if not discussion:
        return jsonify({'error': 'Discussion not found'}), 404

    for key, value in data.items():
        setattr(discussion, key, value)

    discussion.save()

    return jsonify(discussion.to_dict()), 200

@org_views.route('/discussions/<discussion_id>', methods=['DELETE'])
def delete_discussion(discussion_id):
    """Delete a discussion object"""

    discussion = storage.get(Discussion, discussion_id)
    if not discussion:
        return jsonify({'error': 'Discussion not found'}), 404

    discussion.delete()

    return jsonify({'message': 'Discussion deleted'}), 200
