package by.bsuir.iit.aipos.service;

import by.bsuir.iit.aipos.ServiceHandler;
import by.bsuir.iit.aipos.thrift.WebPatternsService;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

public class Server {

    private TServer server;
    private WebPatternsService.Processor<WebPatternsService.Iface> processor;

    public Server() {
        ServiceHandler serviceHandler = new ServiceHandler();
        processor = new WebPatternsService.Processor<>(serviceHandler);
    }

    public void start(int port) {
        new Thread(() -> serve(port)).start();
    }

    public void stop() {
        server.stop();
    }

    private void serve(int port) {
        try {
            TServerTransport serverTransport = new TServerSocket(port);
            server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
