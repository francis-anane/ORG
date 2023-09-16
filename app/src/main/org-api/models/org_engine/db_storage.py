import models
from os import getenv
import sqlalchemy
from sqlalchemy import create_engine
from sqlalchemy.orm import scoped_session, sessionmaker
from sqlalchemy.ext.declarative import declarative_base
from models.organization import Organization
from models.member import Member
from models.attendance import Attendance
from models.event import Event
from models.communication import *


# Org models
classes = {
    "Organization": Organization,
    "Member": Member,
    "Attendance": Attendance,
    "Event": Event,
    "Announcement": Announcement,
    "Message": Message,
    "Discussion": Discussion
}

Base = declarative_base()


class DBStorage:
    """Interacts with the MySQL database for ORG model objects."""

    __engine = None
    __session = None

    def __init__(self):
        """Instantiate an OrgDBStorage object."""
        ORG_USER = getenv('ORG_USER')
        ORG_PWD = getenv('ORG_PWD')
        ORG_HOST = getenv('ORG_HOST')
        ORG_DB = getenv('ORG_DB')
        ORG_ENV = getenv('ORG_ENV')

        self.__engine = create_engine('mysql+mysqldb://{}:{}@{}/{}'.
                                      format(ORG_USER,
                                             ORG_PWD,
                                             ORG_HOST,
                                             ORG_DB))

        if ORG_ENV == "test":
            Base.metadata.drop_all(self.__engine)

    def all(self, cls=None):
        """Query the current database session for ORG model objects."""
        new_dict = {}
        for clss in classes:
            if cls is None or cls is classes[clss] or cls is clss:
                objs = self.__session.query(classes[clss]).all()
                for obj in objs:
                    key = obj.__class__.__name__ + '.' + obj.id
                    new_dict[key] = obj
        return new_dict

    def new(self, obj):
        """Add the object to the current database session."""
        self.__session.add(obj)

    def save(self):
        """Commit all changes of the current database session."""
        self.__session.commit()

    def delete(self, obj=None):
        """Delete from the current database session obj if not None."""
        if obj is not None:
            self.__session.delete(obj)

    def reload(self):
        """Reload data from the database."""
        # Create the database engine to work on
        Base.metadata.create_all(self.__engine)
        # Create a database Session instance with the new engine
        sess_factory = sessionmaker(bind=self.__engine, expire_on_commit=False)
        # Creates a database session for database operations
        Session = scoped_session(sess_factory)
        self.__session = Session

    def close(self):
        """Call remove() method on the private session attribute."""
        self.__session.remove()

    def get(self, cls, id):
        """Retrieve an object from storage based on object id."""
        if cls and issubclass(cls, models.base_model.BaseModel):
            return (self.__session.query(cls).filter(cls.id == id).first())
        return None

    def count(self, cls=None):
        """Count the number of objects in storage."""
        return len(self.all(cls))

