#!/usr/bin/python3
""" Member management api routes"""

from flask import Flask, request, jsonify

from models.member import Member
from api.v1.views import org_views
from models import storage

@org_views.route('/members', methods=['GET'])
def get_members():
    """Return a JSON reponse of all member objects"""
    
    # Get a list of member objects dictionary
    member_objs = [v.to_dict() for v in storage.all(Member).values()]
    return jsonify(member_objs)

@org_views.route('/members/<member_id>', methods=['GET'])
def get_member(member_id):
    """Return a JSON reponse of member specified by id"""

    # get member by id
    member = storage.get(Member, member_id)
    if member:
        return jsonify(member.to_dict())
    return jsonify({'error': 'Member not found'}), 404

@org_views.route('/members', methods=['POST'])
def create_member():
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

    member = Member(**data)  # create event
    member.save()

    return jsonify(member.to_dict()), 201

# Add PUT and DELETE routes for updating and deleting members


