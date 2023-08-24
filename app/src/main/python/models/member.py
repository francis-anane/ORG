""" Member class module """

from models.org_base_model import BaseModel


class Member(BaseModel):
    """ ORG Member model class
        Attributes:
            name (str): The full name of the member.
            phone (str): The member's contact phone number.
            email (str): The email address associated with the member.
            country (str): The member's country of residence.
            state (str): The member's state or province.
            city (str): The member's city of residence.
            department (str): The department or group the member belongs
                                to within the organization.
            role (str): The role or position of the member within
                            the organization.
            image (str): Path to an image representing the
                            member (e.g., profile picture).

    """
    name = ""
    phone = ""
    email = ""
    country = ""
    state = ""
    city = ""
    department = ""
    role = ""
    image = ""
