package ui;

import model.LivroModel;
import service.LivroService;

import java.util.Scanner;

/**
 * Classe de Interface com o Usuário (UI) responsável por gerenciar a interação via console
 * relacionada ao cadastro, consulta e listagem de livros da biblioteca.
 */
public class LivroUI {

    // Serviço que contém as regras de negócio relativas aos livros
    private final LivroService livroService;
    // Scanner utilizado para capturar as entradas digitadas pelo usuário no console
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Construtor que recebe a instância de LivroService por injeção de dependência.
     */
    public LivroUI(LivroService livroService) {
        this.livroService = livroService;
    }

    /**
     * Interface interativa para cadastrar um novo livro, coletando
     * o título, o autor e o ISBN informados pelo usuário no console.
     */
    public void cadastrarLivro() {
        System.out.print("\nDigite o título do livro: ");
        String titulo = scanner.nextLine();

        System.out.print("Digite o autor do livro: ");
        String autor = scanner.nextLine();

        System.out.print("Digite o ISBN do livro: ");
        String isbn = scanner.nextLine();

        // Cria o modelo do livro e tenta adicioná-lo através do serviço
        LivroModel livro = new LivroModel(isbn, titulo, autor);
        boolean sucesso = livroService.adicionarLivro(livro);

        // Exibe feedback visual de sucesso ou falha (caso o ISBN já exista)
        if (sucesso) {
            System.out.println("Livro cadastrado com sucesso!");
        } else {
            System.out.println("Já existe um livro com esse ISBN.");
        }
    }

    /**
     * Interface interativa para consultar os dados de um livro a partir do ISBN informado.
     */
    public void consultarLivro() {
        System.out.print("Digite o ISBN do livro: ");
        String isbn = scanner.nextLine();

        // Busca o livro no serviço correspondente
        LivroModel livro = livroService.buscarLivroPorIsbn(isbn);
        if (livro != null) {
            System.out.println(livro); // Exibe os detalhes do livro encontrado
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    /**
     * Exibe no console a lista completa de todos os livros cadastrados no sistema,
     * detalhando título, autor, ISBN e status de disponibilidade.
     */
    public void listarLivros() {
        var livros = livroService.listarLivros();
        // Verifica se a lista de livros está vazia
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }

        System.out.println("\nLista de livros:");
        // Percorre e exibe as informações de cada livro encontrado
        for (LivroModel l : livros) {
            System.out.println("Título: " + l.getTitulo()
                    + " | Autor: " + l.getAutor()
                    + " | ISBN: " + l.getIsbn()
                    + " | Disponível: " + l.isDisponivel());
        }
    }
}