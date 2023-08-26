#!/usr/bin/python3
"""Index view for  ORG API"""
from flask import jsonify
from api.v1.views import org_views  # Blueprint object
from models import storage
from models.member import Member
from models.org_base_model import BaseModel
from models.organization import Organization
from models.event import Event
from models.message import Message
from models.announcement import Announcement


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
    	"announcement": Announcement
    	}

    for key, value in objects.items():
        # count objects by type from storage
        objects[key] = storage.count(value)
    return jsonify(objects)
