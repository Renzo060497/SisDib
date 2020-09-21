import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Conexion {
    public Connection getConnection() {
        Connection connection = null;
        //SET GLOBAL time_zone = '+3:00'; necesario para evitar el error
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/cuentas?serverTimezone=UTC", "root", "root");

            if (connection != null) {
                System.out.println("La conexion fue exitosa");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return connection;
    }
}
