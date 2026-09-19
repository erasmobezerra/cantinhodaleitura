package ui;

import model.LivroModel;
import service.LivroService;

import java.util.Scanner;

public class LivroUI {

    private final LivroService livroService;
    private final Scanner scanner = new Scanner(System.in);

    public LivroUI(LivroService livroService) {
        this.livroService = livroService;
    }

    public void cadastrarLivro() {
        System.out.print("\nDigite o título do livro: ");
        String titulo = scanner.nextLine();

        System.out.print("Digite o autor do livro: ");
        String autor = scanner.nextLine();

        System.out.print("Digite o ISBN do livro: ");
        String isbn = scanner.nextLine();

        LivroModel livro = new LivroModel(isbn, titulo, autor);
        boolean sucesso = livroService.adicionarLivro(livro);

        if (sucesso) {
            System.out.println("Livro cadastrado com sucesso!");
        } else {
            System.out.println("Já existe um livro com esse ISBN.");
        }
    }

    public void consultarLivro() {
        System.out.print("Digite o ISBN do livro: ");
        String isbn = scanner.nextLine();

        LivroModel livro = livroService.buscarLivroPorIsbn(isbn);
        if (livro != null) {
            System.out.println(livro);
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    public void listarLivros() {
        var livros = livroService.listarLivros();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }

        System.out.println("\nLista de livros:");
        for (LivroModel l : livros) {
            System.out.println("Título: " + l.getTitulo()
                    + " | Autor: " + l.getAutor()
                    + " | ISBN: " + l.getIsbn()
                    + " | Disponível: " + l.isDisponivel());
        }
    }
}
