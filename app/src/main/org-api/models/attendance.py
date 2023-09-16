from models.org_base_model import BaseModel
from sqlalchemy import Column, String, DateTime, ForeignKey
from sqlalchemy.orm import relationship
from datetime import datetime
import models


class Attendance(BaseModel):
    """ Attendance model class
    Attributes:
        attendance_type (str): Type of attendance (regular or special event).
        member_id (str): Foreign key to the member associated with the attendance.
        check_in_time (DateTime): Date and time of check-in.
        check_out_time (DateTime): Date and time of check-out.
    """
    if models.storage_type == "database":
        # Define the table name for the Attendance model
        __tablename__ = "attendances"
        attendance_type = Column(String(20), nullable=False)
        member_id = Column(String(60), ForeignKey('members.member_id'), nullable=False)
        check_in_time = Column(DateTime, nullable=False)
        check_out_time = Column(DateTime)

        # Define relationship to the Member model
        member = relationship('Member', foreign_keys=[member_id])
    else:
        attendance_type = ""
        member_id = ""
        check_in_time = None
        check_out_time = None
    
    def __init__(self, *args, **kwargs):
        """ Class constructor """
        super().__init__(*args, **kwargs)
