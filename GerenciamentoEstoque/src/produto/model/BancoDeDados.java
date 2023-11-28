package produto.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class BancoDeDados {

		Connection connection;

        public BancoDeDados() {
        }

        public static Connection abrirConexao() {
        	Connection connection = null;
            System.out.println("Deveria passar aqui no bancodedados.java");
	        try {
	            String url = "jdbc:mysql://localhost:3306/gerenciamento_estoque";
	            String username = "root";
	            String password = "903256018";
	            connection = DriverManager.getConnection(url, username, password);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
            return connection;
	    }

        public void fecharConexao() {
            try {
                if (this.connection != null && !connection.isClosed()) {
                    connection.close();
                    System.out.println("banco de dados desconectado");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
