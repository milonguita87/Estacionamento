package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionFactory;
import Model.Veiculo;

public class VeiculoDAO {
	public void inserir (Veiculo veiculo) {
		String sql = "INSERT INTO Veiculo (placa, hora_entrada, hora_saida) VALUES (?,?,?)";
		
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
				stmt.setString(1, veiculo.getPlaca());
				stmt.setObject(2, veiculo.getHora_entrada());
				stmt.setObject(3, veiculo.getHora_saida());
				
				stmt.execute();
				
				try (ResultSet rs = stmt.getGeneratedKeys()){
					if(rs.next()) {
						veiculo.setIdveiculo(rs.getInt(1));
					}
				}
			
					}catch(SQLException e) {
					throw new RuntimeException("Erro ao inserir veiculo.", e);
				}
		}
	
	public List <Veiculo>listarTodos(){
		String sql = "SELECT idveiculo, placa, hora_entrada, hora_saida FROM veiculo";
		List <Veiculo> veiculos = new ArrayList<>();
		
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()){
			
			while(rs.next()) {
				Veiculo veiculo = new Veiculo(
						rs.getInt("idveiculo"),
						rs.getString("Placa"),
						rs.getObject("hora_entrada", LocalDateTime.class),
						rs.getObject("hora_saida", LocalDateTime.class)
				);
						veiculos.add(veiculo);
						
			}
			
		}catch (SQLException e) {
			throw new RuntimeException("Erro ao listar veiculos", e);
		}
		return veiculos;
	}
	
	public Integer buscarIdPorPlaca(String placa) {
	    String sql = "SELECT idveiculo FROM Veiculo WHERE placa = ? AND hora_saida IS NULL";

	    try (Connection conn = ConnectionFactory.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, placa);

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt("idveiculo");
	            }
	        }

	    } catch (SQLException e) {
	        throw new RuntimeException("Erro ao buscar veículo por placa.", e);
	    }

	    return null; 
	}
	
	public void registrarSaida (int placa, LocalDateTime hora_saida) {
		String sql = "UPDATE veiculo SET hora_saida = ? WHERE idveiculo = ?";
		
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				){
			stmt.setObject(1, hora_saida);
			stmt.setObject(2, placa);
			
			int linhasAfetadas = stmt.executeUpdate();
			if (linhasAfetadas == 0) {
				throw new RuntimeException("Nenhum veiculo encontrado com a placa: "+ placa);
			}
			}catch (SQLException e) {
			throw new RuntimeException("Erro ao registrar a saida do veiculo.", e);
		}
	}
	

	public Veiculo buscarPorId(int idveiculo) {
    String sql = "SELECT idveiculo, placa, hora_entrada, hora_saida FROM VEICULO WHERE idveiculo = ?";

    try (Connection conn = ConnectionFactory.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idveiculo);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return new Veiculo(
                    rs.getInt("idveiculo"),
                    rs.getString("placa"),
                    rs.getObject("hora_entrada", LocalDateTime.class),
                    rs.getObject("hora_saida", LocalDateTime.class)
                );
            }
        }

    } catch (SQLException e) {
        throw new RuntimeException("Erro ao buscar veículo por id.", e);
    }

    return null;
	}
}
