const zmq = require("zeromq")
var mysql = require('mysql');

async function run() {
  const sock_inventario = new zmq.Request

  sock.connect("tcp://127.0.0.1:3000")
  console.log("Producer bound to port 3000")

  var orden_compra={
    codigo_cliente:"CLI001",
    nombre_cliente:"Mac Dowall",
    ruc_cliente:"23242321243",
    productos:[
                {item:"tempera",
                descripcion_item:"tempera artesco 7 colores",
                cantidad:"4",
                precio_unitario:"2.5"},
                {item:"plumones",
                descripcion_item:"plumones faber",
                cantidad:"5",
                precio_unitario:"3.5"}
    ]
  };
  //console.log(paquete);
  await sock_inventario.send(JSON.stringify(orden_compra));
}

run()