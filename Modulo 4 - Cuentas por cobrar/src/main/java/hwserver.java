import com.google.gson.Gson;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.SocketType;

import java.util.Properties;
import java.util.StringTokenizer;


public class hwserver
{
    public static void main(String[] args) throws Exception
    {
        try (ZContext context = new ZContext()) {
            // Socket to talk to clients
            ZMQ.Socket socket = context.createSocket(SocketType.REP);
            socket.bind("tcp://*:5558");
            System.out.println("Iniciando servidor de modulo de cuentas por cobrar");

            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Extrayendo de la bd de cuentas por cobrar");
                CuentaService.listarCuentas();
                //el socket recibe el flujo de bytes
                byte[] reply = socket.recv(0);
             /*   System.out.println(
                        "Received " + ": [" + new String(reply, ZMQ.CHARSET) + "]"
                ); */

                String respuesta = new String(reply, ZMQ.CHARSET);
                System.out.println("Esta es lo que recibo del módulo de facturación");
                System.out.println(respuesta);

                //aqui vamos a desempaquetar la respuesta del objeto json  en campos
                Gson gson = new Gson();
                //convertimos el json
                Properties properties = gson.fromJson(respuesta, Properties.class);
                String nombre_cliente = properties.getProperty("nombre_cliente");
                System.out.println(nombre_cliente);
                String codigo_cliente = properties.getProperty("codigo_cliente");
                System.out.println(codigo_cliente);
                String ruc_cliente = properties.getProperty("ruc_cliente");
                System.out.println(ruc_cliente);
                String numero_factura = properties.getProperty("numero_factura");
                System.out.println(numero_factura);
                String total_factura = properties.getProperty("total_factura");
                System.out.println(total_factura);
                String total_igv = properties.getProperty("total_igv");

                //aqui vamos a insertar a la bd usando los campos

                CuentaService.crearCuenta(
                        nombre_cliente,
                        numero_factura,
                        total_factura,
                        codigo_cliente,
                        ruc_cliente,
                        total_igv,
                        "22/09/20"
                );

                String response = "Respuesta modulo de cuentas por cobrar";
                socket.send(response.getBytes(ZMQ.CHARSET), 0);

                String responseToOrdenesDePago = "El pedido fue ejecutado satisfactoriamente";
                socket.send(responseToOrdenesDePago.getBytes(ZMQ.CHARSET));
                Thread.sleep(1000); //  Do some 'work'
            }
        }
    }
}