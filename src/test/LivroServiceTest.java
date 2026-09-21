package test;

import model.LivroModel;
import service.LivroService;

public class LivroServiceTest {

    public static void executar() {
        System.out.println("\n=== LivroServiceTest ===");

        LivroService livroService = new LivroService();

        LivroModel livro = new LivroModel("9781234567890", "Clean Code", "Robert C. Martin");
        LivroModel livroDuplicado = new LivroModel("9781234567890", "Outro título", "Outro autor");

        TestUtils.assertTrue(livroService.adicionarLivro(livro), "Deveria cadastrar um livro novo.");
        TestUtils.assertFalse(livroService.adicionarLivro(livroDuplicado), "Não deveria permitir ISBN duplicado.");

        LivroModel livroPorIsbn = livroService.buscarLivroPorIsbn("9781234567890");
        TestUtils.assertEquals("Clean Code", livroPorIsbn.getTitulo(),
                "Busca por ISBN deveria encontrar o livro correto.");

        LivroModel livroPorTitulo = livroService.buscarLivroPorTitulo("clean code");
        TestUtils.assertEquals("9781234567890", livroPorTitulo.getIsbn(),
                "Busca por título deve ser case-insensitive.");

        TestUtils.assertEquals(1, livroService.listarLivros().size(),
                "A lista de livros deveria conter apenas um cadastro válido.");

        System.out.println("LivroServiceTest concluído com sucesso.");
    }
}
