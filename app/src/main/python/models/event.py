"""Event class module"""

from models.org_base_model import BaseModel


class Event(BaseModel):
    id = id
    title = title
    date = date
    time = time
    description = description
    attendees = attendees

    def __init__(self, *args, **kwargs):
        """ Class constructor """
        super().__init__(*args, **kwargs)