import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoDAO {
    private Connection connection;

    public EquipamentoDAO() {
        connection = ConexaoBD.getInstance().getConnection();
    }

    public void inserir(Equipamento equipamento) {
        String sql = "INSERT INTO Equipamento (nome_equipamento, tipo) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, equipamento.getNomeEquipamento());
            pstmt.setString(2, equipamento.getTipo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Equipamento equipamento) {
        String sql = "UPDATE Equipamento SET nome_equipamento = ?, tipo = ? WHERE id_equipamento = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, equipamento.getNomeEquipamento());
            pstmt.setString(2, equipamento.getTipo());
            pstmt.setInt(3, equipamento.getIdEquipamento());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int idEquipamento) {
        String sql = "DELETE FROM Equipamento WHERE id_equipamento = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idEquipamento);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Equipamento> listar() {
        List<Equipamento> equipamentos = new ArrayList<>();
        String sql = "SELECT * FROM Equipamento";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int idEquipamento = rs.getInt("id_equipamento");
                String nomeEquipamento = rs.getString("nome_equipamento");
                String tipo = rs.getString("tipo");
                Equipamento equipamento = new Equipamento(idEquipamento, nomeEquipamento, tipo);
                equipamentos.add(equipamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipamentos;
    }
}