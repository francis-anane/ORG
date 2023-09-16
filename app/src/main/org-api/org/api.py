#!/usr/bin/python3
"""ORG API implemented with flask"""

from flask import Flask, jsonify
from flask_cors import CORS

from models import storage
from org.views import org_views  # Blueprint
from org.views import socketio
from org.views import images

from flask_uploads import configure_uploads


# Flask app instance
org = Flask(__name__)

socketio.init_app(org)  # Set up the app for realtime messaging

# set strict slashes on routes
org.url_map.strict_slashes = False

# Register org_routes as blueprint to org
org.register_blueprint(org_views)


# allows cors access from all domains
#CORS(org, resources={'/*': {'origins': '0.0.0.0':}})
# allows access from 8000
#CORS(org, origins=["http://127.0.0.1:8000"])

# Set up CORS for org
CORS(org, resources={r"*": {"origins": "*"}})  # Allow all origins for testing


# Configure the upload set to be able to upload images
org.config["UPLOADED_PHOTOS_DEST"] = "uploads/members/"
configure_uploads(org, images)

# WebSocket event handlers for receiving and sending message
@socketio.on('connect')
def handle_connect():
    """Handles websocket connection"""
    print('Client connected')  # Optional: Log the client connection

@socketio.on('disconnect')
def handle_disconnect():
    """Handles websocket disconnection"""
    print('Client disconnected')  # Optional: Log the client disconnection




@org.teardown_appcontext
def close_storage(error):
    """ Called when application context is torn down"""
    storage.close()


@org.errorhandler(404)
def not_found(error):
    """Return a not found repond error"""

    return jsonify(error='Not found'), 404

if __name__ == "__main__":
    #org.run(host="0.0.0.0", port=8000, threaded=True, debug=True)
    socketio.run(org, host="0.0.0.0", port=8000, debug=True)
