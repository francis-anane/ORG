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
