"""Event class module"""

from models.org_base_model import BaseModel


class Event(BaseModel):
    """ Event class model
        Attributes:
            title: The title or name of the event.
            date: The date on which the event will occur.
            time: The time at which the event will take place.
            description: Detailed information about the event.
            attendees (list): A list of user IDs representing attendees of the event.

    """
    title = ""
    date = ""
    time = ""
    description = ""
    attendees = []

    def __init__(self, *args, **kwargs):
        """ Class constructor """
        super().__init__(*args, **kwargs)