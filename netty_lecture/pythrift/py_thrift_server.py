#!/usr/bin/env python3
# coding:utf-8
# import importlib
# import sys

from thrift import Thrift
from thrift.server import TServer
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TCompactProtocol
from com.shengsiyuan.thrift.python import PersonService
from person_service_impl import PersonServiceImpl


# importlib.reload(sys)
# Python3字符串默认编码unicode, 所以sys.setdefaultencoding也不存在了
# sys.setdefaultencoding('utf8')

try:
    personServiceHandler = PersonServiceImpl()
    processor = PersonService.Processor(personServiceHandler)

    serverSocket = TSocket.TServerSocket(port=8899)

    transportFactory = TTransport.TFramedTransportFactory()
    protocolFactory = TCompactProtocol.TCompactProtocolFactory()

    server = TServer.TThreadPoolServer(processor, serverSocket, transportFactory, protocolFactory)

    print("python thrift server is start")
    server.serve()
    print('python thrift server is stop')

except Thrift.TException as e:
    print('%s' % e.message)
