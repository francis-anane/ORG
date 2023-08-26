#!/usr/bin/python3
""" Event management api routes"""

from flask import request, jsonify
from models.event import Event
from api.v1.views import org_views
from models import storage


@org_views.route('/events', methods=['GET'])
def get_events():
    """Return a JSON reponse of all event objects"""
    
    # Get list of amenity objects dictionary
    event_objs = [v.to_dict() for v in storage.all(Event).values()]
    return jsonify(event_objs)


@org_views.route('/events/<event_id>', methods=['GET'])
def get_event(event_id):
    """Return a JSON reponse of event specified by id"""
 
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

# Add PUT and DELETE routes for updating and deleting events
