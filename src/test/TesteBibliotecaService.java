package test;

import model.LivroModel;
import model.UsuarioModel;
import service.BibliotecaService;
import service.LivroService;
import service.UsuarioService;

public class TesteBibliotecaService {

    public static void executar() {
        System.out.println("\n=== TesteBibliotecaService ===");

        LivroService livroService = new LivroService();
        UsuarioService usuarioService = new UsuarioService();
        BibliotecaService bibliotecaService = new BibliotecaService(livroService, usuarioService);

        UsuarioModel usuario = new UsuarioModel("Carlos", "11122233344");
        UsuarioModel usuario2 = new UsuarioModel("Maria", "55566677788");
        LivroModel livro = new LivroModel("1111111111111", "O Pequeno Príncipe", "Antoine de Saint-Exupéry");
        LivroModel livro2 = new LivroModel("2222222222222", "1984", "George Orwell");

        TesteHelper.assertTrue(usuarioService.adicionarUsuario(usuario),
                "Usuário deve ser cadastrado antes do empréstimo.");
        TesteHelper.assertTrue(usuarioService.adicionarUsuario(usuario2),
                "Segundo usuário deve ser cadastrado antes do empréstimo.");
        TesteHelper.assertTrue(livroService.adicionarLivro(livro), "Livro deve ser cadastrado antes do empréstimo.");
        TesteHelper.assertTrue(livroService.adicionarLivro(livro2), "Livro 2 deve ser cadastrado antes do empréstimo.");

        TesteHelper.assertTrue(bibliotecaService.emprestarLivro("Carlos", "1111111111111"),
                "Deveria permitir empréstimo de livro disponível.");
        TesteHelper.assertEquals("O Pequeno Príncipe", usuario.getLivroEmprestado().getTitulo(),
                "Usuário deve receber o livro como empréstimo ativo.");
        TesteHelper.assertFalse(livro.isDisponivel(), "Livro deve ficar indisponível após o empréstimo.");

        TesteHelper.assertFalse(bibliotecaService.emprestarLivro("Carlos", "2222222222222"),
                "Usuário com empréstimo pendente não pode pegar outro livro.");
        TesteHelper.assertFalse(bibliotecaService.emprestarLivro("Maria", "1111111111111"),
                "Livro indisponível não pode ser emprestado.");
        TesteHelper.assertFalse(bibliotecaService.emprestarLivro("José", "2222222222222"),
                "Usuário inexistente não pode emprestar.");

        TesteHelper.assertTrue(bibliotecaService.devolverLivro("Carlos", "1111111111111"),
                "Deveria permitir devolução do livro emprestado.");
        TesteHelper.assertTrue(livro.isDisponivel(), "Livro deve voltar a ficar disponível após a devolução.");
        TesteHelper.assertEquals(null, usuario.getLivroEmprestado(),
                "Usuário não deve manter livro emprestado após a devolução.");
        TesteHelper.assertFalse(bibliotecaService.devolverLivro("Carlos", "1111111111111"),
                "Não deve permitir devolução duplicada do mesmo livro.");

        TesteHelper.assertEquals(1, bibliotecaService.listarEmprestimos().size(),
                "O histórico de empréstimos deve registrar o empréstimo realizado.");

        System.out.println("TesteBibliotecaService concluído com sucesso.");
    }
}
