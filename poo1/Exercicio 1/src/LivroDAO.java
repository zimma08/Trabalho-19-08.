import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    private Connection connection;

    public LivroDAO() {
        connection = ConexaoBD.getInstance().getConnection();
    }

    public void inserir(Livro livro) {
        String sql = "INSERT INTO Livro (titulo, anoPublicacao, idAutor) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, livro.getTitulo());
            pstmt.setInt(2, livro.getAnoPublicacao());
            pstmt.setInt(3, livro.getIdAutor());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Livro livro) {
        String sql = "UPDATE Livro SET titulo = ?, anoPublicacao = ?, idAutor = ? WHERE idLivro = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, livro.getTitulo());
            pstmt.setInt(2, livro.getAnoPublicacao());
            pstmt.setInt(3, livro.getIdAutor());
            pstmt.setInt(4, livro.getIdLivro());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int idLivro) {
        String sql = "DELETE FROM Livro WHERE idLivro = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idLivro);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Livro> listar() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM Livro";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int idLivro = rs.getInt("idLivro");
                String titulo = rs.getString("titulo");
                int anoPublicacao = rs.getInt("anoPublicacao");
                int idAutor = rs.getInt("idAutor");
                Livro livro = new Livro(idLivro, titulo, anoPublicacao, idAutor);
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public List<Livro> listarLivrosPorAutor(int idAutor) {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM Livro WHERE idAutor = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idAutor);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int idLivro = rs.getInt("idLivro");
                    String titulo = rs.getString("titulo");
                    int anoPublicacao = rs.getInt("anoPublicacao");
                    Livro livro = new Livro(idLivro, titulo, anoPublicacao, idAutor);
                    livros.add(livro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }
}

