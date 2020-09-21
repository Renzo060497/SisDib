import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class hwclient
{
    public static void main(String[] args)
    {
        try (ZContext context = new ZContext()) {
            //  Socket to talk to server
            System.out.println("Connecting al modulo de Millonez desde java");

            ZMQ.Socket socket = context.createSocket(SocketType.REQ);
            socket.connect("tcp://192.168.56.1:5557");

            //for (int requestNbr = 0; requestNbr != 10; requestNbr++) {
                String request = "Cuenta por cobrar";
               // System.out.println("Sending CUENTA POR COBRAR " + requestNbr);
            System.out.println("Sending OBJETO CUENTA POR COBRAR");
            socket.send(request.getBytes(ZMQ.CHARSET), 0);

            //    byte[] reply = socket.recv(0);
              //  System.out.println(
                //        "Received " + new String(reply, ZMQ.CHARSET) + " "
                //);
            //}
        }
    }
}