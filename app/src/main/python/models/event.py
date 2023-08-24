"""Event class module"""

from models.org_base_model import BaseModel


class Event(BaseModel):
    id = id
    title = title
    date = date
    time = time
    description = description
    attendees = attendees
