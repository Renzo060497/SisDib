const zmq = require("zeromq")
const precio_item1 = 10
const precio_item2 = 20
const precio_item3 = 30

async function run() {

  var i=0;
  const fact="FACT";
  const sock = new zmq.Reply

  await sock.bind("tcp://127.0.0.1:6000")

  
  for await (const [msg] of sock) {
    //var resultado=2 * parseInt(msg, 10);
    //var paquete={resultado:resultado};
    //await sock.send("4")
      i+=1;
      var orden = msg;
      var orden_compra = JSON.parse(orden);

      
      num_factura=fact.concat(i)

      //factura=recorre el json de renzo y almacenar junto con num_factura y
      //igv y total
      var igv_total = orden_compra.cantidad * precio_item1 * 0.18
      orden_compra['total_igv'] = igv_total
      orden_compra['numero_factura'] = num_factura
      orden_compra['total_factura'] = igv_total + orden_compra.cantidad * precio_item1

      console.log(orden_compra);

      //Por cada orden de compra que reciba, genera factura, almacena en su bd
      //y emite a cuentas x cobrar(send)
      const sock_cuentasxcobrar = new zmq.Request
      sock_cuentasxcobrar.connect("tcp://127.0.0.1:5558")
      await sock_cuentasxcobrar.send(JSON.stringify(orden_compra));

  }
}

run()