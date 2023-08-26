#!/usr/bin/python3
"""
initialize the models package
"""
from models.org_engine.file_storage import FileStorage

storage = FileStorage()  # Unique instance for FileStorage

# Load data from JSON file into memory
storage.reload()