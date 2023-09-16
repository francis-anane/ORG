#!/usr/bin/python3
from flask import Blueprint
from flask_socketio import SocketIO

from flask_uploads import UploadSet, IMAGES
#Create the Blueprint object with '/org/' as url_prefix
org_views = Blueprint('org_views', __name__, url_prefix='/org/')

# Create an UploadSet for images
images = UploadSet("photos", IMAGES)

socketio = SocketIO()

# routes for view objects
from org.views.org_index import *
from org.views.members import *
from org.views.organizations import *
from org.views.events import *
from org.views.messages import *
from org.views.announcements import *
from org.views.discussions import *
from org.views.attendances import *
