#!/usr/bin/python3
"""This is the base model class for ORG API models"""

import uuid
import models
from datetime import datetime


class BaseModel:
    """defines all common attributes/methods for the ORG models objects
    """

    def __init__(self, *args, **kwargs):
        """Constructor for BaseModel
        Args:
            args: tuple of arguments to Basemodel to create a new object
            kwargs: key/value arguments to Basemodel to create a new object
        Attributes:
            id: Unique identity for objects
            created_at: The time an object was created
            updated_at: The time an object was updated
        """
        # create an object from key/value pairs if provided
        if kwargs:
            for key, value in kwargs.items():
                if key == "created_at" or key == "updated_at":
                    value = datetime.strptime(value, "%Y-%m-%dT%H:%M:%S.%f")
                if key != "__class__":
                    setattr(self, key, value)
            if "id" not in kwargs:
                self.id = str(uuid.uuid4())
            if "created_at" not in kwargs:
                self.created_at = datetime.now()
            if "updated_at" not in kwargs:
                self.updated_at = datetime.now()

        # create a new object if key/value pairs are not provided
        else:
            self.id = str(uuid.uuid4())
            self.created_at = self.updated_at = datetime.now()

    def __str__(self):
        """ Return string representation of the class instance"""
        return "[{}] ({}) {}".format(
            type(self).__name__, self.id, self.__dict__)

    def __repr__(self):
        """ Return string representation of object"""
        return self.__str__()

    def save(self):
        """ Save object updates """
        self.updated_at = datetime.now()
        models.storage.new(self)
        models.storage.save()

    def to_dict(self):
        """ Return dictionary representation of object """
        obj_dict = {}  # holds dictionary representation of object
        obj_dict.update(self.__dict__)
        # convert datetime objects to string (created_at and updated_at)
        obj_dict['created_at'] = self.created_at.isoformat()
        obj_dict['updated_at'] = self.updated_at.isoformat()
        # add object's class name to dictionary
        obj_dict.update({"__class__": type(self).__name__})
        return obj_dict

    def delete(self):
        """ delete object
        """
        models.storage.delete(self)
