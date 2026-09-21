package ui;

import model.EmprestimoModel;
import service.BibliotecaService;

import java.util.Scanner;

/**
 * Classe de Interface com o Usuário (UI) responsável por gerenciar a interação via console
 * relacionada aos empréstimos e devoluções de livros da biblioteca.
 */
public class BibliotecaUI {

    // Serviço que contém as regras de negócio relativas à biblioteca e empréstimos
    private final BibliotecaService bibliotecaService;
    // Scanner utilizado para capturar as entradas digitadas pelo usuário no console
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Construtor que recebe a instância de BibliotecaService por injeção de dependência.
     */
    public BibliotecaUI(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
    }

    /**
     * Interface interativa para solicitar o empréstimo de um livro,
     * capturando o nome do usuário e o ISBN do livro pelo console.
     */
    public void emprestarLivro() {
        System.out.print("\nDigite o nome do usuário: ");
        String nomeUsuario = scanner.nextLine();

        System.out.print("Digite o ISBN do livro: ");
        String isbnLivro = scanner.nextLine();

        // Chama o serviço para efetivar o empréstimo e exibe feedback correspondente
        boolean sucesso = bibliotecaService.emprestarLivro(nomeUsuario, isbnLivro);
        System.out.println(sucesso ? "Empréstimo realizado com sucesso!" : "Não foi possível realizar o empréstimo.");
    }

    /**
     * Interface interativa para registrar a devolução de um livro,
     * capturando o nome do usuário e o ISBN do livro pelo console.
     */
    public void devolverLivro() {
        System.out.print("\nDigite o nome do usuário: ");
        String nomeUsuario = scanner.nextLine();

        System.out.print("Digite o ISBN do livro: ");
        String isbnLivro = scanner.nextLine();

        // Chama o serviço para efetivar a devolução e exibe feedback correspondente
        boolean sucesso = bibliotecaService.devolverLivro(nomeUsuario, isbnLivro);
        System.out.println(sucesso ? "Devolução registrada com sucesso!" : "Não foi possível registrar a devolução.");
    }

    /**
     * Exibe no console o histórico completo de empréstimos registrados no sistema,
     * detalhando usuário, livro, datas e o status atual.
     */
    public void listarEmprestimos() {
        // Verifica se existem empréstimos cadastrados
        if (bibliotecaService.listarEmprestimos().isEmpty()) {
            System.out.println("Nenhum empréstimo registrado.");
            return;
        }

        System.out.println("\nHistórico de empréstimos:");
        // Percorre cada empréstimo e formata seus dados para exibição
        for (EmprestimoModel e : bibliotecaService.listarEmprestimos()) {
            System.out.println("\n | Usuário: " + e.getUsuario().getNome()
                    + "\n | Livro: " + e.getLivro().getTitulo()
                    + "\n | Empréstimo: " + e.getDataEmprestimo()
                    + "\n | Devolução prevista: " + e.getDataPrevistaDevolucao()
                    + "\n | Devolução real: " + (e.getDataDevolucao() != null ? e.getDataDevolucao() : "Ainda não devolvido")
                    + "\n | Status: " + (e.isAtivo() ? "Ativo" : "Concluído"));
        }
    }
}