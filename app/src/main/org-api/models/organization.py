from models.org_base_model import BaseModel, Base
from sqlalchemy import Column, String, Text
from sqlalchemy.orm import relationship
import models

class Organization(BaseModel, Base):
    """ Organization model class """
    
    if models.storage_type == "database":
        # Define the table name for the Organization model
        __tablename__ = "organizations"
        name = Column(String(128), nullable=False)
        head = Column(String(128))
        phone = Column(String(20))
        email = Column(String(128))
        website = Column(String(256))
        logo = Column(Text)
        country = Column(String(256))
        state = Column(String(256))
        city = Column(String(256))
        
        # Define relationship with members
        members = relationship('Member', back_populates='organization')
    else:
        name = ""
        head = ""
        phone = ""
        email = ""
        website = ""
        logo = ""
        country = ""
        state = ""
        city = ""
        members = []

    def __init__(self, *args, **kwargs):
        """ Class constructor """
        super().__init__(*args, **kwargs)

