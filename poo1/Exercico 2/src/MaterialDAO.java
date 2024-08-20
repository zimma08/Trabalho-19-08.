import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {
    private Connection connection;

    public MaterialDAO() {
        connection = ConexaoBD.getInstance().getConnection();
    }

    public void inserir(Material material) {
        String sql = "INSERT INTO Material (nome_material, quantidade) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, material.getNomeMaterial());
            pstmt.setInt(2, material.getQuantidade());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Material material) {
        String sql = "UPDATE Material SET nome_material = ?, quantidade = ? WHERE id_material = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, material.getNomeMaterial());
            pstmt.setInt(2, material.getQuantidade());
            pstmt.setInt(3, material.getIdMaterial());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int idMaterial) {
        String sql = "DELETE FROM Material WHERE id_material = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idMaterial);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Material> listar() {
        List<Material> materiais = new ArrayList<>();
        String sql = "SELECT * FROM Material";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int idMaterial = rs.getInt("id_material");
                String nomeMaterial = rs.getString("nome_material");
                int quantidade = rs.getInt("quantidade");
                Material material = new Material(idMaterial, nomeMaterial, quantidade);
                materiais.add(material);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materiais;
    }
}