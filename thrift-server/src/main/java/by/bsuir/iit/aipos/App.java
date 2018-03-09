package by.bsuir.iit.aipos;

import by.bsuir.iit.aipos.thrift.WebPatternsService;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

import static java.lang.Thread.sleep;

/**
 * Hello world!
 *
 */
public class App 
{
    private void start() {
        try {
            TServerTransport serverTransport = new TServerSocket(7911);
            ServiceHandler serviceHandler = new ServiceHandler();
            WebPatternsService.Processor<WebPatternsService.Iface> processor = new WebPatternsService.Processor<>(serviceHandler);
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public static void main( String[] args )
    {
        App app = new App();
        app.start();
    }
}
