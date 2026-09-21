package test;

import model.UsuarioModel;
import service.UsuarioService;

public class UsuarioServiceTest {

    public static void executar() {
        System.out.println("\n=== UsuarioServiceTest ===");

        UsuarioService usuarioService = new UsuarioService();

        UsuarioModel usuario = new UsuarioModel("Ana Souza", "12345678900");
        UsuarioModel usuarioMesmoCpf = new UsuarioModel("Maria Silva", "12345678900");
        UsuarioModel usuarioMesmoNome = new UsuarioModel("Ana Souza", "09876543211");

        // Deve cadastrar um usuário novo com sucesso
        TestUtils.assertTrue(usuarioService.adicionarUsuario(usuario),
                "Deveria cadastrar um usuário novo.");

        // Não deve permitir cadastro com CPF duplicado
        TestUtils.assertFalse(usuarioService.adicionarUsuario(usuarioMesmoCpf),
                "Não deveria permitir CPF duplicado.");

        // Não deve permitir cadastro com nome duplicado
        TestUtils.assertFalse(usuarioService.adicionarUsuario(usuarioMesmoNome),
                "Não deveria permitir nome duplicado.");

        // Busca por CPF deve retornar o usuário correto
        UsuarioModel usuarioPorCpf = usuarioService.buscarUsuarioPorCpf("12345678900");
        TestUtils.assertEquals("Ana Souza", usuarioPorCpf.getNome(),
                "Busca por CPF deveria retornar o usuário correto.");

        // Busca por nome deve ignorar maiúsculas/minúsculas
        UsuarioModel usuarioPorNome = usuarioService.buscarUsuarioPorNome("ana souza");
        TestUtils.assertEquals("12345678900", usuarioPorNome.getCpf(),
                "Busca por nome deve ser case-insensitive.");

        // Lista de usuários deve conter apenas registros válidos
        TestUtils.assertEquals(1, usuarioService.listarUsuarios().size(),
                "A lista de usuários deveria manter apenas registros válidos.");

        System.out.println("UsuarioServiceTest concluído com sucesso.");
    }
}
