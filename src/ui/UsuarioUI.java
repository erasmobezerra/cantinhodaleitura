package ui;

import model.UsuarioModel;
import service.UsuarioService;

import java.util.Scanner;

public class UsuarioUI {

    private final UsuarioService usuarioService;
    private final Scanner scanner = new Scanner(System.in);

    public UsuarioUI(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void cadastrarUsuario() {
        System.out.print("\nDigite o nome do usuário: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o CPF do usuário: ");
        String cpf = scanner.nextLine();

        UsuarioModel usuario = new UsuarioModel(nome, cpf);
        boolean sucesso = usuarioService.adicionarUsuario(usuario);

        if (sucesso) {
            System.out.println("Usuário cadastrado com sucesso!");
        } else {
            System.out.println("Usuário já existe ou inválido.");
        }
    }

    public void consultarUsuario() {
        System.out.print("Digite o CPF do usuário: ");
        String cpf = scanner.nextLine();

        UsuarioModel usuario = usuarioService.buscarUsuarioPorCpf(cpf);
        if (usuario != null) {
            System.out.println(usuario);
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public void listarUsuarios() {
        var usuarios = usuarioService.listarUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        System.out.println("\nLista de usuários:");
        for (UsuarioModel u : usuarios) {
            System.out.println("Nome: " + u.getNome()
                    + " | CPF: " + u.getCpf()
                    + " | Livro emprestado: "
                    + (u.getLivroEmprestado() != null ? u.getLivroEmprestado().getTitulo() : "Nenhum"));
        }
    }
}
