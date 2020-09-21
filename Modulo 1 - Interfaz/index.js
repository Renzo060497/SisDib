const express = require('express');
const app = express();
const bodyParser = require('body-parser');
const zmq = require('zeromq')

//especificamos el subdirectorio donde se encuentran las páginas estáticas
app.use(express.static(__dirname + '/public'));

//extended: false significa que parsea solo string (no archivos de imagenes por ejemplo)
app.use(bodyParser.urlencoded({ extended: false }));
const morgan = require("morgan");


//settings
app.set("port", 3000);

//middlewares
app.use(morgan("dev"));
app.use(express.json());
//routes


app.post('/', (req, res) => {
    let codigo_cliente = req.body.codigocli;
    let nombre_cliente = req.body.nombre;
    let ruc_cliente = req.body.ruc;
    let nombre_producto = req.body.nombrepro;
    let codigo_producto = req.body.codigo;
    let cantidad_producto = req.body.cantidad;

    const pedido = {
        "nombre_cliente": nombre_cliente,
        // "numero_factura": 0,
        "codigo_cliente": codigo_cliente,
        "codigo": codigo_producto,
        "cantidad": cantidad_producto,
        "ruc_cliente": ruc_cliente,
        "total_igv": 0,
        "total_factura": 0
    }

    sock = new zmq.Request()
    sock.connect('tcp://localhost:5555')
    sock.send(JSON.stringify(pedido))

    let pagina = '<!doctype html><html><head></head><body><a href="javascript: history.go(-1)">Volver</a> </body> ';
    res.send(pagina)
})


app.listen(app.get("port"), () => {
    console.log("Server listening in port: ", app.get("port"));
});