#!/usr/bin/python3
from flask import Blueprint


org_views = Blueprint('org_views', __name__, url_prefix='/api/')
"""Create the Blueprint object with '/api/' as url_prefix"""

# routes for view objects
from api.views.org_index import *
from api.views.members import *
from api.views.organizations import *
from api.views.events import *
from api.views.messages import *
from api.views.announcements import *
from api.views.discussions import *