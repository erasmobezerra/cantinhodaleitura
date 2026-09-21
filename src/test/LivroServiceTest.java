package test;

import model.LivroModel;
import service.LivroService;

public class LivroServiceTest {

    public static void executar() {
        System.out.println("\n=== LivroServiceTest ===");

        LivroService livroService = new LivroService();

        LivroModel livro = new LivroModel("9781234567890", "Clean Code", "Robert C. Martin");
        LivroModel livroDuplicado = new LivroModel("9781234567890", "Outro título", "Outro autor");

        // Deve cadastrar um livro novo com sucesso
        TestUtils.assertTrue(livroService.adicionarLivro(livro),
                "Deveria cadastrar um livro novo.");

        // Não deve permitir cadastro de ISBN duplicado
        TestUtils.assertFalse(livroService.adicionarLivro(livroDuplicado),
                "Não deveria permitir ISBN duplicado.");

        // Busca por ISBN deve retornar o livro correto
        LivroModel livroPorIsbn = livroService.buscarLivroPorIsbn("9781234567890");
        TestUtils.assertEquals("Clean Code", livroPorIsbn.getTitulo(),
                "Busca por ISBN deveria encontrar o livro correto.");

        // Busca por título deve ignorar maiúsculas/minúsculas
        LivroModel livroPorTitulo = livroService.buscarLivroPorTitulo("clean code");
        TestUtils.assertEquals("9781234567890", livroPorTitulo.getIsbn(),
                "Busca por título deve ser case-insensitive.");

        // Lista de livros deve conter apenas um cadastro válido
        TestUtils.assertEquals(1, livroService.listarLivros().size(),
                "A lista de livros deveria conter apenas um cadastro válido.");

        System.out.println("LivroServiceTest concluído com sucesso.");
    }
}
