package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionFactory;
import Model.Tarifa;

public class TarifaDAO {

    public List<Tarifa> listarTodos() {
        String sql = "SELECT idtarifa, nome, valor_hora FROM TARIFA";
        List<Tarifa> tarifas = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Tarifa tarifa = new Tarifa(
                    rs.getInt("idtarifa"),
                    rs.getString("nome"),
                    rs.getDouble("valor_hora")
                );
                tarifas.add(tarifa);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar tarifas.", e);
        }

        return tarifas;
    }

    public Tarifa buscarPorId(int idtarifa) {
        String sql = "SELECT idtarifa, nome, valor_hora FROM TARIFA WHERE idtarifa = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idtarifa);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Tarifa(
                        rs.getInt("idtarifa"),
                        rs.getString("nome"),
                        rs.getDouble("valor_hora")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar tarifa por id.", e);
        }

        return null; 
    }
   
    public Tarifa buscarPorNome(String nome) {
        String sql = "SELECT idtarifa, nome, valor_hora FROM TARIFA WHERE nome = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Tarifa(
                        rs.getInt("idtarifa"),
                        rs.getString("nome"),
                        rs.getDouble("valor_hora")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar tarifa por nome.", e);
        }

        return null;
    }
}