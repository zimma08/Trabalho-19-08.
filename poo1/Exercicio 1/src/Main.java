import java.util.List;

public class Main {
    public static void main(String[] args) {

        AutorDAO autorDAO = new AutorDAO();
        LivroDAO livroDAO = new LivroDAO();

        Autor autor1 = new Autor(1, "João Silva", "Brasileiro");
        autorDAO.inserir(autor1);

        Autor autor2 = new Autor(2, "Maria Santos", "Brasileira");
        autorDAO.inserir(autor2);

        Livro livro1 = new Livro(1, "O Senhor dos Anéis", 1954, 1);
        livroDAO.inserir(livro1);

        Livro livro2 = new Livro(2, "A Guerra dos Tronos", 1996, 2);
        livroDAO.inserir(livro2);

        List<Autor> autores = autorDAO.listar();
        List<Livro> livros = livroDAO.listar();

        System.out.println("Autores:");
        for (Autor autor : autores) {
            System.out.println(autor.getNome());
        }

        System.out.println("\nLivros:");
        for (Livro livro : livros) {
            System.out.println(livro.getTitulo());
        }

        List<Livro> livrosDoAutor1 = livroDAO.listarLivrosPorAutor(1);
        System.out.println("\nLivros do Autor 1:");
        for (Livro livro : livrosDoAutor1) {
            System.out.println(livro.getTitulo());
        }
    }
}
