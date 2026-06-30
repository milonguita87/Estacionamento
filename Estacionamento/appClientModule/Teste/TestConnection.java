package Teste;

import java.sql.Connection;
import Connection.ConnectionFactory;

public class TestConnection {
	public static void main(String[] args) {
		try (Connection conn = ConnectionFactory.getConnection()){
			if (conn != null) {
				System.out.println("Conexão realizada com sucesso.");
				System.out.println("Banco: " + conn.getCatalog());
			}
		} catch (Exception e) {
			System.err.println("Erro ao conectar " + e.getMessage());
			System.err.printf("Causa " + e.getCause(), e.getMessage());
		}

	}
}
