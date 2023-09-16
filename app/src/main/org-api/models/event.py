"""Event class module"""

from models.org_base_model import BaseModel
from sqlalchemy import Column, String, DateTime, ForeignKey
from sqlalchemy.orm import relationship
import models

class Event(BaseModel):
    """ Event model class
    Attributes:
        event_id (str): Primary key for the event.
        event_title (str): The title or name of the event.
        description (str): Description or details about the event.
        attendees (list): List of member IDs attending the event.
        rsvps (list): List of member IDs who have RSVP'd to the event.
        location (str): The location or venue of the event.
        event_date (DateTime): Date and time of the event.
        author_id (str): Foreign key to the member who created the event.

    Relationships:
        created_by (relationship): Relationship to the Member model for the event creator.
    """
    if models.storage_type == "database":
        # Define the table name for the Event model
        __tablename__ = "events"
        event_title = Column(String(128), nullable=False)
        description = Column(String(256))
        location = Column(String(128))
        event_date = Column(DateTime, nullable=False)
        author_id = Column(String(60), ForeignKey('members.member_id'), nullable=False)
        
        # Define relationships
        attendees = relationship('Member', secondary='event_attendees')
        rsvps = relationship('Member', secondary='event_rsvps')
        author = relationship('Member', foreign_keys=[author_id])
    else:
        event_title = ""
        description = ""
        location = ""
        event_date = None
        author_id = ""
        attendees = []
        rsvps = []

    def __init__(self, *args, **kwargs):
        """ Class constructor """
        super().__init__(*args, **kwargs)

