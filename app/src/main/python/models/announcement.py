""" Announcement class module"""

from models.org_base_model import BaseModel


class Announcement(BaseModel):
    """ Announcement model class
        Attributes:
            author (str): The user ID of the author of the announcement.
            content (str): The content or message of the announcement.
    """
    
    author = ""
    content = ""

    def __init__(self, *args, **kwargs):
        """ Class constructor """
        super().__init__(*args, **kwargs)


