"""Communication module"""

from models.org_base_model import BaseModel


class Message(BaseModel):
    """ Message model class
        Attributes:

            sender (str): The user ID of the sender of the message.
            receiver (str): The user ID of the receiver of the message.
            content (str): The content or text of the message.



    """
    sender = ""
    receiver = ""
    content = ""

    def __init__(self, *args, **kwargs):
        """ Class constructor """
        super().__init__(*args, **kwargs)


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


class Discussion(BaseModel):
    """ Discussion model class
        Attributes:
            discussion (list): List of discussion topics
            author: The user ID of the author of the discussion.
            topic: The topic or of the discussion.            
    """
    author = ""
    topic = ""
    discussions = []

    def __init__(self, *args, **kwargs):
        """ Class constructor """
        super().__init__(*args, **kwargs)


