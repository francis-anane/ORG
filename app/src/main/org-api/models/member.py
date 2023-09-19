from models.org_base_model import BaseModel
from sqlalchemy import Column, String, ForeignKey
from sqlalchemy.orm import relationship
import models

class Member(BaseModel):
    """ ORG Member model class
    Attributes:
        name (str): The full name of the member.
        phone (str): The member's contact phone number.
        email (str): The email address associated with the member.
        country (str): The member's country of residence.
        state (str): The member's state or province.
        city (str): The member's city of residence.
        department (str): The department or group the member belongs
                            to within the organization.
        role (str): The role or position of the member within
                            the organization.
        image (str): Path to an image representing the
                            member (e.g., profile picture).
        organization_id (str): The id of the member's organization.
    """
    
    if models.storage_type == "database":
        # Define the table name for the Member model
        __tablename__ = "members"
        
        name = Column(String(128), nullable=False)
        phone = Column(String(20), nullable=False)
        email = Column(String(128), nullable=False)
        country = Column(String(128))
        state = Column(String(128))
        city = Column(String(128))
        department = Column(String(128))
        role = Column(String(128))
        image = Column(String(256))
        organization_id = Column(String(60), ForeignKey('organizations.id'), nullable=False)
        attendance_id = Column(String(60), ForeignKey('attendances.id'), nullable=False)
        event_id = Column(String(60), ForeignKey('events.id'), nullable=False)
        message_id = Column(String(60), ForeignKey('messages.id'), nullable=False)
        
        # Establish organization relationship
        organization = relationship('Organization', foreign_keys=[organization_id])
    else:
        name = ""
        phone = ""
        email = ""
        country = ""
        state = ""
        city = ""
        department = ""
        role = ""
        image = ""
        organization_id = ""
        attendance_id = ""
        event_id = ""
        message_id = ""

    def __init__(self, *args, **kwargs):
        """ Class constructor """
        super().__init__(*args, **kwargs)

