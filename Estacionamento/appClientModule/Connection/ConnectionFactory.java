package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

		private static final String URL= "jdbc:mysql://localhost:3306/ESTACIONAMENTO";
		private static final String Usuario = "root"; 
		private static final String Senha = "root";
		
		public static Connection getConnection() {
			try {
				return DriverManager.getConnection(URL, Usuario, Senha);
			} catch (SQLException e) {
				throw new RuntimeException ("Erro ao conectar ao Banco de Dados.", e);
			}
		} 
}
