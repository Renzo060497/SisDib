public class CuentaService {
    public static void listarCuentas() {
        CuentaDAO.listarCuentas();
    }

    public static void crearCuenta(String nc, String num_fac, String to_co,String cod_cl,
                                   String ruc_cl, String tot_igv, String fecha_cobro ) {
        CuentaDAO.crearCuenta(nc, num_fac, to_co,cod_cl, ruc_cl, tot_igv, fecha_cobro);
    }
}
