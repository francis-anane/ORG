from sqlalchemy import Column, String, ForeignKey
from sqlalchemy.orm import relationship, backref
from models.org_base_model import BaseModel

class Message(BaseModel):
    """Message model class."""
    __tablename__ = "messages"

    sender_id = Column(String(60), ForeignKey('members.member_id'), nullable=False)
    receiver_id = Column(String(60), ForeignKey('members.member_id'), nullable=False)
    content = Column(String(256), nullable=False)

    # Establish sender and receiver relationships
    sender = relationship('Member', foreign_keys=[sender_id], backref=backref('sent_messages', cascade='all, delete-orphan'))
    receiver = relationship('Member', foreign_keys=[receiver_id], backref=backref('received_messages', cascade='all, delete-orphan'))

class Announcement(BaseModel):
    """Announcement model class."""
    __tablename__ = "announcements"

    author_id = Column(String(60), ForeignKey('members.member_id'), nullable=False)
    content = Column(String(256), nullable=False)

    # Establish author relationship
    author = relationship('Member', foreign_keys=[author_id])

class Discussion(BaseModel):
    """Discussion model class."""
    __tablename__ = "discussions"

    discussion_id = Column(String(60), primary_key=True)
    topic = Column(String(128), nullable=False)
    author_id = Column(String(60), ForeignKey('members.member_id'), nullable=False)

    # Define relationship to the Member model for author
    author = relationship('Member', foreign_keys=[author_id])

    # Define a many-to-many relationship between discussions and members
    members = relationship('Member', secondary='discussion_members', backref=backref('discussions', cascade='all, delete-orphan'))

class DiscussionMember(BaseModel):
    """DiscussionMember model class for the many-to-many relationship."""
    __tablename__ = "discussion_members"

    discussion_id = Column(String(60), ForeignKey('discussions.discussion_id'), primary_key=True)
    member_id = Column(String(60), ForeignKey('members.member_id'), primary_key=True)

