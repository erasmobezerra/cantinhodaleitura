package ui;

import java.util.Scanner;

/**
 * Classe de Interface com o Usuário (UI) responsável por exibir o menu principal interativo,
 * gerenciar a navegação do sistema e direcionar as escolhas do usuário para as respectivas UIs especializadas.
 */
public class MenuUI {

    // Scanner estático compartilhado para capturar as entradas do usuário no console
    private static final Scanner scanner = new Scanner(System.in);
    // Interface responsável pelas operações de livros
    private final LivroUI livroUI;
    // Interface responsável pelas operações de usuários
    private final UsuarioUI usuarioUI;
    // Interface responsável pelas operações de empréstimos e devoluções
    private final BibliotecaUI bibliotecaUI;

    /**
     * Construtor que recebe as instâncias das UIs especializadas por injeção de dependência.
     */
    public MenuUI(LivroUI livroUI, UsuarioUI usuarioUI, BibliotecaUI bibliotecaUI) {
        this.livroUI = livroUI;
        this.usuarioUI = usuarioUI;
        this.bibliotecaUI = bibliotecaUI;
    }

    /**
     * Exibe o loop principal do sistema, apresentando as opções disponíveis no menu,
     * lendo a escolha do usuário e redirecionando para a ação correspondente.
     */
    public void executar() {
        while (true) {
            // Exibição do cabeçalho e opções do menu interativo
            System.out.println("=================================================");
            System.out.println("      📚 BEM-VINDO AO CANTINHO DA LEITURA 📚      ");
            System.out.println("=================================================");

            System.out.println("\n----------------- MENU PRINCIPAL ----------------");
            System.out.println(" [Empréstimos e Devoluções]");
            System.out.println("  1. Emprestar livro");
            System.out.println("  2. Devolver livro");

            System.out.println("\n [Gerenciamento de Livros]");
            System.out.println("  3. Consultar livro");
            System.out.println("  4. Listar livros");
            System.out.println("  5. Cadastrar livro");

            System.out.println("\n [Gerenciamento de Usuários]");
            System.out.println("  6. Consultar usuário");
            System.out.println("  7. Listar usuários");
            System.out.println("  8. Cadastrar usuário");

            System.out.println("\n [Sistema]");
            System.out.println("  9. Listar empréstimos");
            System.out.println("\n  10. Sair");
            System.out.println("-------------------------------------------------");
            System.out.print("Escolha uma opção: ");

            // Leitura da opção escolhida pelo usuário
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do teclado

            // Estrutura de decisão que direciona o fluxo com base na opção selecionada
            switch (opcao) {
                case 1 -> bibliotecaUI.emprestarLivro();
                case 2 -> bibliotecaUI.devolverLivro();
                case 3 -> livroUI.consultarLivro();
                case 4 -> livroUI.listarLivros();
                case 5 -> livroUI.cadastrarLivro();
                case 6 -> usuarioUI.consultarUsuario();
                case 7 -> usuarioUI.listarUsuarios();
                case 8 -> usuarioUI.cadastrarUsuario();
                case 9 -> bibliotecaUI.listarEmprestimos();
                case 10 -> {
                    System.out.println("Saindo...");
                    return; // Encerra a execução do menu
                }
                default -> System.out.println("Opção inválida.");
            }

            // Pergunta ao usuário se deseja retornar ao menu ou encerrar após cada operação
            if (!retornarAoMenu()) {
                return;
            }
        }

    }

    /**
     * Método auxiliar (privado) que exibe um submenu para decidir se o usuário
     * deseja voltar ao menu principal ou encerrar totalmente a aplicação.
     */
    private static boolean retornarAoMenu() {
        while (true) {
            System.out.println("\n-------------------------------------------------");
            System.out.println("O que deseja fazer:");
            System.out.println("1. Voltar ao Menu");
            System.out.println("2. Encerrar o Programa");
            System.out.println("-------------------------------------------------");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    return true; // Retorna true para continuar no loop do menu principal
                }
                case 2 -> {
                    System.out.println("Encerrando o programa...");
                    return false; // Retorna false para encerrar a aplicação
                }
                default -> System.out.println("Opção inválida. Digite '1' para Menu ou '2' para Sair.");
            }
        }
    }
}