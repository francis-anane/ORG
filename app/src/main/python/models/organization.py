"""Organization class module"""

from models.org_base_model import BaseModel


class Organization(BaseModel):
    """ Organization model class
        Attributes:
            name (str): The name of the organization.
            head (str): The head or leader of the organization.
            phone (str): The organization's contact phone number.
            email (str): The email address associated with the organization.
            website (str): The website URL of the organization.
            customization (str): Any customization or branding information
                                related to the organization.

    """
    name = ""
    head = ""
    phone = ""
    email = ""
    website = ""
    customization = ""

    def __init__(self, *args, **kwargs):
        """ Class constructor """
        super().__init__(*args, **kwargs)
