#!/usr/bin/python3
""" Attendance management api routes"""

from flask import request, jsonify, abort

from models.attendance import Attendance
from org.views import org_views
from models import storage

@org_views.route('/attendances', methods=['GET'])
def get_attendances():
    """Return a JSON response of all attendance objects"""
    
    # Get a list of attendance objects dictionary
    attendance_objs = [v.to_dict() for v in storage.all(Attendance).values()]
    return jsonify(attendance_objs)

@org_views.route('/attendances/<attendance_id>', methods=['GET'])
def get_attendance(attendance_id):
    """Return a JSON response of attendance specified by id"""

    # get attendance by id
    attendance = storage.get(Attendance, attendance_id)
    if attendance:
        return jsonify(attendance.to_dict())
    return jsonify({'error': 'Attendance not found'}), 404

@org_views.route('/attendances', methods=['POST'])
def create_attendance():
    """Create a new attendance object"""

    data = request.get_json()  # Content body
    if type(data) is not dict:
        abort(400, 'Not a Json')  # raise bad request error
    if 'attendance_type' not in data:
        abort(400, 'Missing attendance type')  # raise bad request error
    if 'member_id' not in data:
        abort(400, 'Missing member id')  # raise bad request error
    if 'check_in_time' not in data:
        abort(400, 'Missing check-in time')  # raise bad request error

    attendance = Attendance(**data)  # create attendance
    attendance.save()

    return jsonify(attendance.to_dict()), 201

@org_views.route('/attendances/<attendance_id>', methods=['PUT'])
def update_attendance(attendance_id):
    """Update an existing attendance object"""

    data = request.get_json()  # Content body
    attendance = storage.get(Attendance, attendance_id)

    if not attendance:
        return jsonify({'error': 'Attendance not found'}), 404

    for key, value in data.items():
        setattr(attendance, key, value)

    attendance.save()

    return jsonify(attendance.to_dict()), 200

@org_views.route('/attendances/<attendance_id>', methods=['DELETE'])
def delete_attendance(attendance_id):
    """Delete an attendance object"""

    attendance = storage.get(Attendance, attendance_id)
    if not attendance:
        return jsonify({'error': 'Attendance not found'}), 404

    attendance.delete()

    return jsonify({'message': 'Attendance deleted'}), 200

