import atmservice.controller.ATMController;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import onlinegame.controller.OnlineGameController;
import transactions.controller.TransactionsController;


public class Main {
    public static void main(String[] args) {
        int port = 8080;

        Undertow server = Undertow.builder()
                .addHttpListener(port, "0.0.0.0")
                .setHandler(getRootHandler())
                .build();

        server.start();
    }

    private static PathHandler getRootHandler() {
        PathHandler pathHandler = new PathHandler();

        pathHandler.addPrefixPath("/atms/calculateOrder", new ATMController());
        pathHandler.addPrefixPath("/onlinegame/calculate", new OnlineGameController());
        pathHandler.addPrefixPath("/transactions/report", new TransactionsController());

        return pathHandler;
    }
}
