package ui;

import model.EmprestimoModel;
import service.BibliotecaService;

import java.util.Scanner;

public class BibliotecaUI {

    private final BibliotecaService bibliotecaService;
    private final Scanner scanner = new Scanner(System.in);

    public BibliotecaUI(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
    }

    public void emprestarLivro() {
        System.out.print("\nDigite o nome do usuário: ");
        String nomeUsuario = scanner.nextLine();

        System.out.print("Digite o ISBN do livro: ");
        String isbnLivro = scanner.nextLine();

        boolean sucesso = bibliotecaService.emprestarLivro(nomeUsuario, isbnLivro);
        System.out.println(sucesso ? "Empréstimo realizado com sucesso!" : "Não foi possível realizar o empréstimo.");
    }

    public void devolverLivro() {
        System.out.print("\nDigite o nome do usuário: ");
        String nomeUsuario = scanner.nextLine();

        System.out.print("Digite o ISBN do livro: ");
        String isbnLivro = scanner.nextLine();

        boolean sucesso = bibliotecaService.devolverLivro(nomeUsuario, isbnLivro);
        System.out.println(sucesso ? "Devolução registrada com sucesso!" : "Não foi possível registrar a devolução.");
    }

    public void listarEmprestimos() {
        if (bibliotecaService.listarEmprestimos().isEmpty()) {
            System.out.println("Nenhum empréstimo registrado.");
            return;
        }

        System.out.println("\nHistórico de empréstimos:");
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
