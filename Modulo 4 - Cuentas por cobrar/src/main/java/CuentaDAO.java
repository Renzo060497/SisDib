import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CuentaDAO {

    public static void crearCuenta(String nc, String num_fac, String to_co, String cod_cl,
                                    String ruc_cl, String tot_igv, String fecha_cobro) {
        Conexion conexion = new Conexion();

        try (Connection conn = conexion.getConnection()) {
            PreparedStatement ps = null;
            try {
                String query = "INSERT INTO cuentas_por_cobrar ( nombre_cliente, numero_factura, total_por_cobrar," +
                        "codigo_cliente,ruc_cliente,total_igv,fecha_de_cobro ) " +
                        "VALUES ( ?,?,?,?,?,?,?,? )";
                ps = conn.prepareStatement(query);
                ps.setString(1, nc);
                ps.setString(2, num_fac);
                ps.setString(3, to_co);
                ps.setString(4, cod_cl);
                ps.setString(5, ruc_cl);
                ps.setString(6, tot_igv);
                ps.setString(7, fecha_cobro);
                ps.executeUpdate();
                System.out.println("Cuenta por cobrar insertado exitosamente");
            } catch (SQLException ex) {
                System.out.println(ex);
                System.out.println("NO SE PUDO INSERTAR LA CUENTA POR COBRAR");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void listarCuentas() {
        Conexion conexion = new Conexion();
        PreparedStatement ps;
        ResultSet rs;
        try (Connection conn = conexion.getConnection()) {
            String query = "SELECT * FROM cuentas_por_cobrar";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nombre del cliente: " + rs.getString("nombre_cliente"));
                System.out.println("Número de factura " + rs.getString("numero_factura"));
                System.out.println("Total por cobrar " + rs.getString("total_por_cobrar"));
                System.out.println("Fecha factura " + rs.getString("fecha_factura"));
                System.out.println("Código del cliente " + rs.getString("codigo_cliente"));
                System.out.println("RUC del cliente " + rs.getString("ruc_cliente"));
                System.out.println("Total IGV " + rs.getString("total_igv"));
                System.out.println("Fecha de cobro " + rs.getString("fecha_de_cobro"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
