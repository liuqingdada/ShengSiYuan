#!/usr/bin/env python
# coding:utf-8
# import importlib
# import sys

from com.shengsiyuan.thrift.python import ttypes

# importlib.reload(sys)
# sys.setdefaultencoding('utf8')


class PersonServiceImpl:
    def __init__(self):
        pass

    def getPersonByName(self, name):
        print("Got Client Param: " + name)

        person = ttypes.Person()
        person.name = name
        person.age = 20
        person.married = False
        person.children = ["suhen", 'liuqing']
        person.mapper = {1: '1', 2: '2'}
        return person

    def savePerson(self, person):
        print('Got Client Param: ')
        print(person)
