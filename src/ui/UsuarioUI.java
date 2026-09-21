package ui;

import model.UsuarioModel;
import service.UsuarioService;

import java.util.Scanner;

/**
 * Classe de Interface com o Usuário (UI) responsável por gerenciar a interação via console
 * relacionada ao cadastro, consulta e listagem de usuários da biblioteca.
 */
public class UsuarioUI {

    // Serviço que contém as regras de negócio relativas aos usuários
    private final UsuarioService usuarioService;
    // Scanner utilizado para capturar as entradas digitadas pelo usuário no console
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Construtor que recebe a instância de UsuarioService por injeção de dependência.
     */
    public UsuarioUI(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Interface interativa para cadastrar um novo usuário, coletando
     * o nome e o CPF informados no console.
     */
    public void cadastrarUsuario() {
        System.out.print("\nDigite o nome do usuário: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o CPF do usuário: ");
        String cpf = scanner.nextLine();

        // Cria o modelo do usuário e tenta adicioná-lo através do serviço
        UsuarioModel usuario = new UsuarioModel(nome, cpf);
        boolean sucesso = usuarioService.adicionarUsuario(usuario);

        // Exibe feedback visual de sucesso ou falha (caso o usuário já exista ou seja inválido)
        if (sucesso) {
            System.out.println("Usuário cadastrado com sucesso!");
        } else {
            System.out.println("Usuário já existe ou inválido.");
        }
    }

    /**
     * Interface interativa para consultar os dados de um usuário a partir do CPF informado.
     */
    public void consultarUsuario() {
        System.out.print("Digite o CPF do usuário: ");
        String cpf = scanner.nextLine();

        // Busca o usuário no serviço correspondente pelo CPF
        UsuarioModel usuario = usuarioService.buscarUsuarioPorCpf(cpf);
        if (usuario != null) {
            System.out.println(usuario); // Exibe os detalhes do usuário encontrado
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    /**
     * Exibe no console a lista completa de todos os usuários cadastrados no sistema,
     * detalhando nome, CPF e se há algum livro atualmente emprestado para ele.
     */
    public void listarUsuarios() {
        var usuarios = usuarioService.listarUsuarios();
        // Verifica se a lista de usuários está vazia
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        System.out.println("\nLista de usuários:");
        // Percorre e exibe as informações de cada usuário cadastrado
        for (UsuarioModel u : usuarios) {
            System.out.println("Nome: " + u.getNome()
                    + " | CPF: " + u.getCpf()
                    + " | Livro emprestado: "
                    + (u.getLivroEmprestado() != null ? u.getLivroEmprestado().getTitulo() : "Nenhum"));
        }
    }
}