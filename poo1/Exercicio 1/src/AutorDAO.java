import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {
    private Connection connection;

    public AutorDAO() {
        connection = ConexaoBD.getInstance().getConnection();
    }

    public void inserir(Autor autor) {
        String sql = "INSERT INTO Autor (nome, nacionalidade) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, autor.getNome());
            pstmt.setString(2, autor.getNacionalidade());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Autor autor) {
        String sql = "UPDATE Autor SET nome = ?, nacionalidade = ? WHERE idAutor = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, autor.getNome());
            pstmt.setString(2, autor.getNacionalidade());
            pstmt.setInt(3, autor.getIdAutor());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int idAutor) {
        String sql = "DELETE FROM Autor WHERE idAutor = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idAutor);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Autor> listar() {
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM Autor";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                autores.add(new Autor(rs.getInt("idAutor"), rs.getString("nome"), rs.getString("nacionalidade")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autores;
    }
}
