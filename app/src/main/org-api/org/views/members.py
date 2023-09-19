#!/usr/bin/python3
""" Member management api routes"""

from flask import request, jsonify, abort
from flask_uploads import UploadNotAllowed, secure_filename

from models.member import Member
from org.views import org_views
from org.views import images
from models import storage
import json

@org_views.route('/members', methods=['GET'])
def get_members():
    """Return a JSON response of all member objects"""
    
    # Get a list of member objects dictionary
    member_objs = [v.to_dict() for v in storage.all(Member).values()]
    return jsonify(member_objs)
@org_views.route('/organization_members/<organization_id>', methods=['GET'])
def get_organization_members(organization_id):
    """Return a JSON response of all members belonging to and organization
        specified by id
    """

    # Get a list of member objects dictionary
    member_objs = [v.to_dict() for v in storage.all(Member).values()
                   if v.organization_id == organization_id]
    return jsonify(member_objs)

@org_views.route('/members/<member_id>', methods=['GET'])
def get_member(member_id):
    """Return a JSON response of member specified by id"""

    # get member by id
    member = storage.get(Member, member_id)
    if member:
        return jsonify(member.to_dict())
    return jsonify({'error': 'Member not found'}), 404


@org_views.route('/members', methods=['POST'])
def create_member():
    """Create a new member object with a photo upload"""


    #data=json.loads(request.form.get('data'))

    data = request.get_json()  # JSON data
    image = request.files.get("image")  # Uploaded photo file

    if data is None or "name" not in data:
        abort(400, 'Missing name')  # raise bad request error

    if image:
        try:
            # Save the uploaded photo and get its path
            image_path = images.save(image, name=secure_filename(image.filename))
        except UploadNotAllowed:
            return jsonify({'error': 'Invalid photo format'}), 400
    else:
        image_path = None

    member_data = data
    member_data["image"] = image_path
    member = Member(**member_data)  # create member
    member.save()

    return jsonify(member.to_dict()), 201


@org_views.route('/members/<member_id>', methods=['PUT'])
def update_member(member_id):
    """Update an existing member object, including the photo"""

    data = request.get_json()  # Form data
    member = storage.get(Member, member_id)

    if not member:
        return jsonify({'error': 'Member not found'}), 404

    for key, value in data.items():
        setattr(member, key, value)

    image = request.files.get("image")  # Uploaded photo file
    if image:
        try:
            # Save the uploaded photo and update its path
            image_path = images.save(image, name=secure_filename(image.filename))
            member.image = image_path
        except UploadNotAllowed:
            return jsonify({'error': 'Invalid photo format'}), 400

    member.save()

    return jsonify(member.to_dict()), 200

@org_views.route('/members/<member_id>', methods=['DELETE'])
def delete_member(member_id):
    """Delete a member object and its associated image"""

    member = storage.get(Member, member_id)
    if not member:
        return jsonify({'error': 'Member not found'}), 404

    # Delete the associated image if it exists
    if member.image:
        try:
            images.delete(member.image)
        except Exception as e:
            return jsonify({'error': 'Failed to delete image: {}'.format(str(e))}), 500

    member.delete()

    return jsonify({'message': 'Member deleted'}), 200
