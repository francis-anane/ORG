#!/usr/bin/python3
"""Index view for  ORG API"""
from flask import jsonify
from org.views import org_views  # Blueprint object
from models import storage
from models.member import Member
from models.org_base_model import BaseModel
from models.organization import Organization
from models.event import Event
from models.attendance import Attendance
from models.communication import *


@org_views.route('/status')
def status():
    """Return API status"""
    return jsonify(status='OK')


@org_views.route('/stats')
def stats():
    """Return number of objects by type"""

    objects = {
    	"member": Member,
    	"organization": Organization,
    	"event": Event,
    	"message": Message,
    	"announcement": Announcement,
        "discussion": Discussion,
        "attendance": Attendance
    	}

    for key, value in objects.items():
        # count objects by type from storage
        objects[key] = storage.count(value)
    return jsonify(objects)
