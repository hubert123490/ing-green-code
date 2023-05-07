import atmservice.controller.ATMController;
import com.sun.net.httpserver.HttpServer;
import onlinegame.controller.OnlineGameController;
import transactions.controller.TransactionsController;

import java.io.*;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/atms/calculateOrder", new ATMController());
        server.createContext("/onlinegame/calculate", new OnlineGameController());
        server.createContext("/transactions/report", new TransactionsController());
        server.setExecutor(null);
        server.start();
    }
}
