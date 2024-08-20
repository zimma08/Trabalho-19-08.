import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EngenheiroDAO {
    private Connection connection;

    public EngenheiroDAO() {
        connection = ConexaoBD.getInstance().getConnection();
    }

    public void inserir(Engenheiro engenheiro) {
        String sql = "INSERT INTO Engenheiro (nome_engenheiro, especialidade) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, engenheiro.getNomeEngenheiro());
            pstmt.setString(2, engenheiro.getEspecialidade());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Engenheiro engenheiro) {
        String sql = "UPDATE Engenheiro SET nome_engenheiro = ?, especialidade = ? WHERE id_engenheiro = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, engenheiro.getNomeEngenheiro());
            pstmt.setString(2, engenheiro.getEspecialidade());
            pstmt.setInt(3, engenheiro.getIdEngenheiro());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int idEngenheiro) {
        String sql = "DELETE FROM Engenheiro WHERE id_engenheiro = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idEngenheiro);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Engenheiro> listar() {
        List<Engenheiro> engenheiros = new ArrayList<>();
        String sql = "SELECT * FROM Engenheiro";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int idEngenheiro = rs.getInt("id_engenheiro");
                String nomeEngenheiro = rs.getString("nome_engenheiro");
                String especialidade = rs.getString("especialidade");
                Engenheiro engenheiro = new Engenheiro(idEngenheiro, nomeEngenheiro, especialidade);
                engenheiros.add(engenheiro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return engenheiros;
    }
}