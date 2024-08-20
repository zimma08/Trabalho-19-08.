import java.util.List;

public class Main {
    public static void main(String[] args) {

        ProjetoDAO projetoDAO = new ProjetoDAO();
        EngenheiroDAO engenheiroDAO = new EngenheiroDAO();
        OperarioDAO operarioDAO = new OperarioDAO();
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
        MaterialDAO materialDAO = new MaterialDAO();

        Projeto projeto = new Projeto(1, "Projeto de Construção", "São Paulo", "2023-01-01", "2023-12-31");
        projetoDAO.inserir(projeto);

        Engenheiro engenheiro = new Engenheiro(1, "João Silva", "Estruturas");
        engenheiroDAO.inserir(engenheiro);

        Operario operario = new Operario(1, "Maria Santos", "Pedreiro");
        operarioDAO.inserir(operario);

        Equipamento equipamento = new Equipamento(1, "Caminhão", "Transporte");
        equipamentoDAO.inserir(equipamento);

        Material material = new Material(1, "Cimento", 100);
        materialDAO.inserir(material);

        List<Projeto> projetos = projetoDAO.listar();
        List<Engenheiro> engenheiros = engenheiroDAO.listar();
        List<Operario> operarios = operarioDAO.listar();
        List<Equipamento> equipamentos = equipamentoDAO.listar();
        List<Material> materiais = materialDAO.listar();

        System.out.println("Projetos:");
        for (Projeto p : projetos) {
            System.out.println(p.getNomeProjeto());
        }

        System.out.println("\nEngenheiros:");
        for (Engenheiro e : engenheiros) {
            System.out.println(e.getNomeEngenheiro());
        }

        System.out.println("\nOperários:");
        for (Operario o : operarios) {
            System.out.println(o.getNomeOperario());
        }

        System.out.println("\nEquipamentos:");
        for (Equipamento eq : equipamentos) {
            System.out.println(eq.getNomeEquipamento());
        }

        System.out.println("\nMateriais:");
        for (Material m : materiais) {
            System.out.println(m.getNomeMaterial());
        }

        // Listar engenheiros, operários, equipamentos e materiais associados ao projeto de ID 1
        List<Engenheiro> engenheirosProjeto = projetoDAO.listarEngenheirosPorProjeto(1);
        List<Operario> operariosProjeto = projetoDAO.listarOperariosPorProjeto(1);
        List<Equipamento> equipamentosProjeto = projetoDAO.listarEquipamentosPorProjeto(1);
        List<Material> materiaisProjeto = projetoDAO.listarMateriaisPorProjeto(1);

        System.out.println("\nEngenheiros no Projeto 1:");
        for (Engenheiro e : engenheirosProjeto) {
            System.out.println(e.getNomeEngenheiro());
        }

        System.out.println("\nOperários no Projeto 1:");
        for (Operario o : operariosProjeto) {
            System.out.println(o.getNomeOperario());
        }

        System.out.println("\nEquipamentos no Projeto 1:");
        for (Equipamento eq : equipamentosProjeto) {
            System.out.println(eq.getNomeEquipamento());
        }

        System.out.println("\nMateriais no Projeto 1:");
        for (Material m : materiaisProjeto) {
            System.out.println(m.getNomeMaterial());
        }
    }
}