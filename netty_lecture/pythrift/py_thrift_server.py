#!/usr/bin/env python
# coding:utf-8

from thrift import Thrift
from thrift.server import TServer
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TCompactProtocol
from com.shengsiyuan.thrift.python import PersonService
from person_service_impl import PersonServiceImpl

import sys

reload(sys)
sys.setdefaultencoding('utf8')

try:
    personServiceHandler = PersonServiceImpl()
    processor = PersonService.Processor(personServiceHandler)

    serverSocket = TSocket.TServerSocket(port=8899)

    transportFactory = TTransport.TFramedTransportFactory()
    protocolFactory = TCompactProtocol.TCompactProtocolFactory()

    server = TServer.TThreadPoolServer(processor, serverSocket, transportFactory, protocolFactory)

    print "python thrift server is start"
    server.serve()
    print 'python thrift server is stop'

except Thrift.TException, e:
    print '%s' % e.message
