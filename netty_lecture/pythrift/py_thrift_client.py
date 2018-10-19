#!/usr/bin/env python
# coding:utf-8

from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TCompactProtocol
from com.shengsiyuan.thrift.python import PersonService
from com.shengsiyuan.thrift.python import ttypes
import sys

reload(sys)
sys.setdefaultencoding('utf8')

__author__ = 'suhen'

try:
    tSocket = TSocket.TSocket('127.0.0.1', 8899)
    tSocket.setTimeout(600)

    transport = TTransport.TFramedTransport(tSocket)
    protocol = TCompactProtocol.TCompactProtocol(transport)

    client = PersonService.Client(protocol)

    transport.open()

    person = client.getPersonByName("张三")
    print person

    print "============"

    person2 = ttypes.Person()
    person2.name = '李四'
    person2.age = 32
    person2.married = True

    client.savePerson(person2)

    transport.close()

except Thrift.TException, e:
    print "%s" % e.message
