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

        // Usuário deve ser cadastrado antes de poder emprestar
        TestUtils.assertTrue(usuarioService.adicionarUsuario(usuario),
                "Usuário deve ser cadastrado antes do empréstimo.");

        // Segundo usuário também precisa estar cadastrado
        TestUtils.assertTrue(usuarioService.adicionarUsuario(usuario2),
                "Segundo usuário deve ser cadastrado antes do empréstimo.");

        // Livro precisa estar registrado para ser emprestado
        TestUtils.assertTrue(livroService.adicionarLivro(livro),
                "Livro deve ser cadastrado antes do empréstimo.");
        // Segundo livro também precisa estar registrado
        TestUtils.assertTrue(livroService.adicionarLivro(livro2),
                "Livro 2 deve ser cadastrado antes do empréstimo.");

        // Empréstimo deve funcionar para livro disponível
        TestUtils.assertTrue(bibliotecaService.emprestarLivro("Carlos", "1111111111111"),
                "Deveria permitir empréstimo de livro disponível.");

        // Usuário deve receber o livro emprestado
        TestUtils.assertEquals("O Pequeno Príncipe", usuario.getLivroEmprestado().getTitulo(),
                "Usuário deve receber o livro como empréstimo ativo.");

        // Livro emprestado deve ficar indisponível
        TestUtils.assertFalse(livro.isDisponivel(),
                "Livro deve ficar indisponível após o empréstimo.");

        // Usuário já com empréstimo não pode pegar outro
        TestUtils.assertFalse(bibliotecaService.emprestarLivro("Carlos", "2222222222222"),
                "Usuário com empréstimo pendente não pode pegar outro livro.");

        // Outro usuário não pode pegar livro já emprestado
        TestUtils.assertFalse(bibliotecaService.emprestarLivro("Maria", "1111111111111"),
                "Livro indisponível não pode ser emprestado.");

        // Usuário inexistente não pode emprestar
        TestUtils.assertFalse(bibliotecaService.emprestarLivro("José", "2222222222222"),
                "Usuário inexistente não pode emprestar.");

        // Devolução deve funcionar para livro emprestado
        TestUtils.assertTrue(bibliotecaService.devolverLivro("Carlos", "1111111111111"),
                "Deveria permitir devolução do livro emprestado.");

        // Livro devolvido deve voltar a ficar disponível
        TestUtils.assertTrue(livro.isDisponivel(),
                "Livro deve voltar a ficar disponível após a devolução.");

        // Usuário não deve manter referência ao livro após devolução
        TestUtils.assertEquals(null, usuario.getLivroEmprestado(),
                "Usuário não deve manter livro emprestado após a devolução.");

        // Não deve permitir devolução duplicada
        TestUtils.assertFalse(bibliotecaService.devolverLivro("Carlos", "1111111111111"),
                "Não deve permitir devolução duplicada do mesmo livro.");


        // Histórico deve registrar o empréstimo realizado
        TestUtils.assertEquals(1, bibliotecaService.listarEmprestimos().size(),
                "O histórico de empréstimos deve registrar o empréstimo realizado.");

        System.out.println("BibliotecaServiceTest concluído com sucesso.");
    }
}
