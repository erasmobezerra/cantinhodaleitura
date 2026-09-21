package test;

import model.LivroModel;
import model.UsuarioModel;
import service.BibliotecaService;
import service.LivroService;
import service.UsuarioService;

public class BibliotecaServiceTest {

    public static void executar() {
        System.out.println("\n=== BibliotecaServiceTest ===");

        LivroService livroService = new LivroService();
        UsuarioService usuarioService = new UsuarioService();
        BibliotecaService bibliotecaService = new BibliotecaService(livroService, usuarioService);

        UsuarioModel usuario = new UsuarioModel("Carlos", "11122233344");
        UsuarioModel usuario2 = new UsuarioModel("Maria", "55566677788");
        LivroModel livro = new LivroModel("1111111111111", "O Pequeno Príncipe", "Antoine de Saint-Exupéry");
        LivroModel livro2 = new LivroModel("2222222222222", "1984", "George Orwell");

        TestUtils.assertTrue(usuarioService.adicionarUsuario(usuario),
                "Usuário deve ser cadastrado antes do empréstimo.");
        TestUtils.assertTrue(usuarioService.adicionarUsuario(usuario2),
                "Segundo usuário deve ser cadastrado antes do empréstimo.");
        TestUtils.assertTrue(livroService.adicionarLivro(livro), "Livro deve ser cadastrado antes do empréstimo.");
        TestUtils.assertTrue(livroService.adicionarLivro(livro2), "Livro 2 deve ser cadastrado antes do empréstimo.");

        TestUtils.assertTrue(bibliotecaService.emprestarLivro("Carlos", "1111111111111"),
                "Deveria permitir empréstimo de livro disponível.");
        TestUtils.assertEquals("O Pequeno Príncipe", usuario.getLivroEmprestado().getTitulo(),
                "Usuário deve receber o livro como empréstimo ativo.");
        TestUtils.assertFalse(livro.isDisponivel(), "Livro deve ficar indisponível após o empréstimo.");

        TestUtils.assertFalse(bibliotecaService.emprestarLivro("Carlos", "2222222222222"),
                "Usuário com empréstimo pendente não pode pegar outro livro.");
        TestUtils.assertFalse(bibliotecaService.emprestarLivro("Maria", "1111111111111"),
                "Livro indisponível não pode ser emprestado.");
        TestUtils.assertFalse(bibliotecaService.emprestarLivro("José", "2222222222222"),
                "Usuário inexistente não pode emprestar.");

        TestUtils.assertTrue(bibliotecaService.devolverLivro("Carlos", "1111111111111"),
                "Deveria permitir devolução do livro emprestado.");
        TestUtils.assertTrue(livro.isDisponivel(), "Livro deve voltar a ficar disponível após a devolução.");
        TestUtils.assertEquals(null, usuario.getLivroEmprestado(),
                "Usuário não deve manter livro emprestado após a devolução.");
        TestUtils.assertFalse(bibliotecaService.devolverLivro("Carlos", "1111111111111"),
                "Não deve permitir devolução duplicada do mesmo livro.");

        TestUtils.assertEquals(1, bibliotecaService.listarEmprestimos().size(),
                "O histórico de empréstimos deve registrar o empréstimo realizado.");

        System.out.println("BibliotecaServiceTest concluído com sucesso.");
    }
}
