from flask import Flask, request, jsonify

app = Flask(__name__)

# Simulated in-memory storage for demonstration
organizations = []

@app.route('/api/organizations', methods=['GET'])
def get_organizations():
    return jsonify([org.__dict__ for org in organizations])

@app.route('/api/organizations/<organization_id>', methods=['GET'])
def get_organization(organization_id):
    organization = next((org for org in organizations if org.id == organization_id), None)
    if organization:
        return jsonify(organization.__dict__)
    return jsonify({'error': 'Organization not found'}), 404

@app.route('/api/organizations', methods=['POST'])
def create_organization():
    data = request.json
    new_organization = Organization(
        id=str(len(organizations) + 1),
        name=data['name'],
        head=data['head'],
        phone=data['phone'],
        email=data['email'],
        website=data['website'],
        customization=data['customization']
    )
    organizations.append(new_organization)
    return jsonify(new_organization.__dict__), 201

if __name__ == '__main__':
    app.run(debug=True)
