#!/usr/bin/python3
""" Organization management api routes"""

from flask import request, jsonify, abort

from models.organization import Organization
from api.views import org_views
from models import storage

@org_views.route('/organizations', methods=['GET'])
def get_organizations():
    """Return a JSON response of all organization objects"""
    
    # Get a list of organization objects dictionary
    organization_objs = [v.to_dict() for v in storage.all(Organization).values()]
    return jsonify(organization_objs)

@org_views.route('/organizations/<organization_id>', methods=['GET'])
def get_organization(organization_id):
    """Return a JSON reponse of organization specified by id"""

    # get member by id
    organization = storage.get(Organization, organization_id)
    if organization:
        return jsonify(organization.to_dict())
    return jsonify({'error': 'Organization not found'}), 404

@org_views.route('/organizations', methods=['POST'])
def create_organization():
    """Create a new organization object"""

    data = request.get_json()  # Content body
    if type(data) is not dict:
        abort(400, 'Not a Json')  # raise bad request error
    if 'name' not in data:
        abort(400, 'Missing name')  # raise bad request error
    if 'head' not in data:
        abort(400, 'Missing head')  # raise bad request error

    organization = Organization(**data)  # create event
    organization.save()

    return jsonify(organization.to_dict()), 201

@org_views.route('/organizations/<organization_id>', methods=['PUT'])
def update_organization(organization_id):
    """Update an existing organization object"""

    data = request.get_json()  # Content body
    organization = storage.get(Organization, organization_id)

    if not organization:
        return jsonify({'error': 'Organization not found'}), 404

    for key, value in data.items():
        setattr(organization, key, value)

    organization.save()

    return jsonify(organization.to_dict()), 200

@org_views.route('/organizations/<organization_id>', methods=['DELETE'])
def delete_organization(organization_id):
    """Delete an organization object"""

    organization = storage.get(Organization, organization_id)
    if not organization:
        return jsonify({'error': 'Organization not found'}), 404

    organization.delete()

    return jsonify({'message': 'Organization deleted'}), 200
