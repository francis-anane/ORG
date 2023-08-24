#!/usr/bin/python3
"""
initialize the models package
"""

from os import getenv

# Set data storage type (file / db storage)
storage_type = getenv("ORG_TYPE_STORAGE")

if storage_type == "db":
    from models.engine.db_storage import DBStorage
    storage = DBStorage()
else:
    from models.engine.file_storage import FileStorage
    storage = FileStorage()

storage.reload() # reload data from storage
