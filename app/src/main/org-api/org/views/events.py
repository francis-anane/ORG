#!/usr/bin/python3
""" Event management api routes"""

from flask import request, jsonify, abort

from models.event import Event
from org.views import org_views
from models import storage

@org_views.route('/events', methods=['GET'])
def get_events():
    """Return a JSON response of all event objects"""
    
    # Get list of event objects dictionary
    event_objs = [v.to_dict() for v in storage.all(Event).values()]
    return jsonify(event_objs)

@org_views.route('/events/<event_id>', methods=['GET'])
def get_event(event_id):
    """Return a JSON response of event specified by id"""
 
    # get event by id
    event = storage.get(Event, event_id)
    if event:
        return jsonify(event.to_dict())
    return jsonify({'error': 'Event not found'}), 404

@org_views.route('/events', methods=['POST'])
def create_event():
    """Create a new event object"""

    data = request.get_json()  # Content body
    if type(data) is not dict:
        abort(400, 'Not a Json')  # raise bad request error
    if 'title' not in data:
        abort(400, 'Missing event title')  # raise bad request error
    if 'description' not in data:
        abort(400, 'Missing event description')  # raise bad request error
    if 'date' not in data:
        abort(400, 'Missing event date')  # raise bad request error
    if 'time' not in data:
        abort(400, 'Missing event time')  # raise bad request error

    event = Event(**data)  # create event
    event.save()

    return jsonify(event.to_dict()), 201

@org_views.route('/events/<event_id>', methods=['PUT'])
def update_event(event_id):
    """Update an existing event object"""

    data = request.get_json()  # Content body
    event = storage.get(Event, event_id)

    if not event:
        return jsonify({'error': 'Event not found'}), 404

    for key, value in data.items():
        setattr(event, key, value)

    event.save()

    return jsonify(event.to_dict()), 200

@org_views.route('/events/<event_id>', methods=['DELETE'])
def delete_event(event_id):
    """Delete an event object"""

    event = storage.get(Event, event_id)
    if not event:
        return jsonify({'error': 'Event not found'}), 404

    event.delete()

    return jsonify({'message': 'Event deleted'}), 200

