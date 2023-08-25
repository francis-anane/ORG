#!/usr/bin/python3
from flask import Blueprint


org_views = Blueprint('org_views', __name__, url_prefix='/api/v1')
"""Create the Blueprint object with '/api/v1' as url_prefix"""

# view objects
from api.v1.views.org_index import *
from api.v1.views.members import *
from api.v1.views.organizations import *
from api.v1.views.events import *
from api.v1.views.messages import *
#from api.v1.views.announcements import *