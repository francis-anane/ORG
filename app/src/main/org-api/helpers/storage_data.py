#!/usr/bin/python3.8

""" Defines methods to send request to org api"""
import requests
import json

member_url = "http://abstrat22.tech/org/members"


def get_members():
    """ Get all members from database """

    try:
        response = requests.get(member_url)
    except requests.exceptions.RequestException as e:
        return {"error": str(e)}
    return response.json()


def get_member(member_id):
    """ Get a member from database """

    try:
        response = requests.get("{}/{}".format(member_url, member_id))
    except requests.exceptions.RequestException as e:
        return {"error": str(e)}
    return response.json()


def add_member(data):
    """ Add a new member to database """

    try:
        response = requests.post(member_url, json=data)
    except requests.exceptions.RequestException as e:
        return "Error: {}".format(e)
    return response
