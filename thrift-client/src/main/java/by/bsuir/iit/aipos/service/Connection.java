package by.bsuir.iit.aipos.service;

import by.bsuir.iit.aipos.thrift.WebPatternsService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.List;

public class Connection {

    private TTransport dataTransport;
    private WebPatternsService.Client client;

    public void open(String host, int port) throws ConnectionException {
        try {
            dataTransport = new TSocket(host, port);
            dataTransport.open();
            client = new WebPatternsService.Client(new TBinaryProtocol(dataTransport));
        } catch (TTransportException e) {
            throw new ConnectionException("Unable to connect by " + host + ":" + port, e);
        }
    }

    public void close() {
        dataTransport.close();
    }

    public boolean isOpen() {
        return dataTransport.isOpen();
    }

    public List<String> getArticleList() throws ConnectionException {
        try {
            return client.getArticleList();
        } catch (TException e) {
            throw new ConnectionException("Unable to get article list", e);
        }
    }
}
