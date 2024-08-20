import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {
    private Connection connection;

    public ProjetoDAO() {
        connection = ConexaoBD.getInstance().getConnection();
    }

    // Métodos de inserir, atualizar, excluir e listar projetos

    public List<Engenheiro> listarEngenheirosPorProjeto(int idProjeto) {
        List<Engenheiro> engenheiros = new ArrayList<>();
        String sql = "SELECT e.* FROM Engenheiro e JOIN Alocacao_Engenheiro ae ON e.id_engenheiro = ae.id_engenheiro WHERE ae.id_projeto = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idProjeto);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int idEngenheiro = rs.getInt("id_engenheiro");
                    String nomeEngenheiro = rs.getString("nome_engenheiro");
                    String especialidade = rs.getString("especialidade");
                    Engenheiro engenheiro = new Engenheiro(idEngenheiro, nomeEngenheiro, especialidade);
                    engenheiros.add(engenheiro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return engenheiros;
    }

    public List<Operario> listarOperariosPorProjeto(int idProjeto) {
        List<Operario> operarios = new ArrayList<>();
        String sql = "SELECT o.* FROM Operario o JOIN Alocacao_Operario ao ON o.id_operario = ao.id_operario WHERE ao.id_projeto = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idProjeto);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int idOperario = rs.getInt("id_operario");
                    String nomeOperario = rs.getString("nome_operario");
                    String funcao = rs.getString("funcao");
                    Operario operario = new Operario(idOperario, nomeOperario, funcao);
                    operarios.add(operario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return operarios;
    }

    public List<Equipamento> listarEquipamentosPorProjeto(int idProjeto) {
        List<Equipamento> equipamentos = new ArrayList<>();
        String sql = "SELECT e.* FROM Equipamento e JOIN Uso_Equipamento ue ON e.id_equipamento = ue.id_equipamento WHERE ue.id_projeto = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idProjeto);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int idEquipamento = rs.getInt("id_equipamento");
                    String nomeEquipamento = rs.getString("nome_equipamento");
                    String tipo = rs.getString("tipo");
                    Equipamento equipamento = new Equipamento(idEquipamento, nomeEquipamento, tipo);
                    equipamentos.add(equipamento);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipamentos;
    }

    public List<Material> listarMateriaisPorProjeto(int idProjeto) {
        List<Material> materiais = new ArrayList<>();
        String sql = "SELECT m.* FROM Material m JOIN Consumo_Material cm ON m.id_material = cm.id_material WHERE cm.id_projeto = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idProjeto);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int idMaterial = rs.getInt("id_material");
                    String nomeMaterial = rs.getString("nome_material");
                    int quantidade = rs.getInt("quantidade");
                    Material material = new Material(idMaterial, nomeMaterial, quantidade);
                    materiais.add(material);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materiais;
    }
}