#!/usr/bin/python3
"""ORG API implemented with flask"""

from flask import Flask, jsonify
from flask_cors import CORS

from models import storage
from api.views import org_views  # Blueprint


org = Flask(__name__)
"""Flask app instace"""

org.url_map.strict_slashes = False
"""set strict slashes on routes"""

org.register_blueprint(org_views)
"""Register org_routes as blueprint to org"""

# allows cors access from all domains
#CORS(org, resources={'/*': {'origins': '0.0.0.0':}})
# allows access from 8000
#CORS(org, origins=["http://127.0.0.1:8000"])
CORS(org, resources={r"*": {"origins": "*"}})  # Allow all origins for testing
"""Set up CORS for org"""


@org.teardown_appcontext
def close_storage(error):
    """ Called when application context is torn down"""
    storage.close()


@org.errorhandler(404)
def not_found(error):
    """Return a not found repond error"""

    return jsonify(error='Not found'), 404

if __name__ == "__main__":
    org.run(host="0.0.0.0", port=8000, threaded=True, debug=True)