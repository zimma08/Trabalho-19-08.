import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperarioDAO {
    private Connection connection;

    public OperarioDAO() {
        connection = ConexaoBD.getInstance().getConnection();
    }

    public void inserir(Operario operario) {
        String sql = "INSERT INTO Operario (nome_operario, funcao) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, operario.getNomeOperario());
            pstmt.setString(2, operario.getFuncao());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Operario operario) {
        String sql = "UPDATE Operario SET nome_operario = ?, funcao = ? WHERE id_operario = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, operario.getNomeOperario());
            pstmt.setString(2, operario.getFuncao());
            pstmt.setInt(3, operario.getIdOperario());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int idOperario) {
        String sql = "DELETE FROM Operario WHERE id_operario = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idOperario);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Operario> listar() {
        List<Operario> operarios = new ArrayList<>();
        String sql = "SELECT * FROM Operario";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int idOperario = rs.getInt("id_operario");
                String nomeOperario = rs.getString("nome_operario");
                String funcao = rs.getString("funcao");
                Operario operario = new Operario(idOperario, nomeOperario, funcao);
                operarios.add(operario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return operarios;
    }
}