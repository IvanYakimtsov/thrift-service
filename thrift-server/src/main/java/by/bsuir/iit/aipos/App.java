package by.bsuir.iit.aipos;

import by.bsuir.iit.aipos.service.ServiceHandler;
import by.bsuir.iit.aipos.thrift.WebPatternsService;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.ServerContext;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServerEventHandler;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.*;

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
            Runnable simple = new Runnable() {
                @Override
                public void run() {
                    server.serve();
                }
            };

            server.setServerEventHandler(new TServerEventHandler() {
                @Override
                public void preServe() {

                }

                @Override
                public ServerContext createContext(TProtocol tProtocol, TProtocol tProtocol1) {
                    System.out.println(((TSocket) tProtocol.getTransport()).getSocket().getInetAddress());
                    return null;
                }

                @Override
                public void deleteContext(ServerContext serverContext, TProtocol tProtocol, TProtocol tProtocol1) {

                }

                @Override
                public void processContext(ServerContext serverContext, TTransport tTransport, TTransport tTransport1) {
                }
            });
            new Thread(simple).start();
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public static void main( String[] args )
    {
        App app = new App();
        app.start();
        System.out.println("done");
    }
}
