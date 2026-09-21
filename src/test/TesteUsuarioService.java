package test;

import model.UsuarioModel;
import service.UsuarioService;

public class TesteUsuarioService {

    public static void executar() {
        System.out.println("\n=== TesteUsuarioService ===");

        UsuarioService usuarioService = new UsuarioService();

        UsuarioModel usuario = new UsuarioModel("Ana Souza", "12345678900");
        UsuarioModel usuarioMesmoCpf = new UsuarioModel("Maria Silva", "12345678900");
        UsuarioModel usuarioMesmoNome = new UsuarioModel("Ana Souza", "09876543211");

        TesteHelper.assertTrue(usuarioService.adicionarUsuario(usuario), "Deveria cadastrar um usuário novo.");
        TesteHelper.assertFalse(usuarioService.adicionarUsuario(usuarioMesmoCpf),
                "Não deveria permitir CPF duplicado.");
        TesteHelper.assertFalse(usuarioService.adicionarUsuario(usuarioMesmoNome),
                "Não deveria permitir nome duplicado.");

        UsuarioModel usuarioPorCpf = usuarioService.buscarUsuarioPorCpf("12345678900");
        TesteHelper.assertEquals("Ana Souza", usuarioPorCpf.getNome(),
                "Busca por CPF deveria retornar o usuário correto.");

        UsuarioModel usuarioPorNome = usuarioService.buscarUsuarioPorNome("ana souza");
        TesteHelper.assertEquals("12345678900", usuarioPorNome.getCpf(), "Busca por nome deve ser case-insensitive.");

        TesteHelper.assertEquals(1, usuarioService.listarUsuarios().size(),
                "A lista de usuários deveria manter apenas registros válidos.");

        System.out.println("TesteUsuarioService concluído com sucesso.");
    }
}
