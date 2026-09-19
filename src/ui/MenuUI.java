package ui;

import java.util.Scanner;

public class MenuUI {

    private static final Scanner scanner = new Scanner(System.in);
    private final LivroUI livroUI;
    private final UsuarioUI usuarioUI;
    private final BibliotecaUI bibliotecaUI;

    public MenuUI(LivroUI livroUI, UsuarioUI usuarioUI, BibliotecaUI bibliotecaUI) {
        this.livroUI = livroUI;
        this.usuarioUI = usuarioUI;
        this.bibliotecaUI = bibliotecaUI;
    }

    public void executar() {
        while (true) {
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

            int opcao = scanner.nextInt();
            scanner.nextLine();

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
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }

            if (!retornarAoMenu()) {
                return;
            }
        }

    }

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
                    return true;
                }
                case 2 -> {
                    System.out.println("Encerrando o programa...");
                    return false;
                }
                default -> System.out.println("Opção inválida. Digite '1' para Menu ou '2' para Sair.");
            }
        }
    }
}


