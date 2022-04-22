import com.pdist.schedule.RpcServer;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Channel;

public class WorkerProcess {

    private final static String QUEUE = "request-queue";

    public static void main(String[] args) throws Exception {

        String uri = System.getenv("CLOUDAMQP_URL");
        if (uri == null) uri = "amqps://fvbmyulz:sWunE0VdpHq0hXBh4N7qrZNKpQbtqgwL@beaver.rmq.cloudamqp.com/fvbmyulz";
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(uri);
        Channel channel = factory.newConnection().createChannel();

        // Declare queue on which to listen for RPC calls
        channel.queueDeclare(QUEUE, false, false, false, null);

        // Create RPC server
        RpcServer server = new RpcServer(channel, QUEUE);

        System.out.println(" [x] Awaiting requests");
        server.mainloop();
    }

}