package service;

import model.LivroModel;
import java.util.ArrayList;
import java.util.List;

public class LivroService {

    // Lista interna em memória para armazenar os livros cadastrados no sistema
    private final List<LivroModel> livros = new ArrayList<>();

    /**
     * Adiciona um novo livro ao sistema após validar se já não existe
     * um livro cadastrado com o mesmo ISBN.
     */
    public boolean adicionarLivro(LivroModel livroModel) {
        // Verifica se já existe um livro com o mesmo ISBN cadastrado (evita duplicidade)
        if (buscarLivroPorIsbn(livroModel.getIsbn()) != null) {
            return false; // Retorna falso indicando que o livro já existe
        }
        // Adiciona o livro à lista e retorna verdadeiro indicando sucesso
        livros.add(livroModel);
        return true;
    }

    /**
     * Busca um livro pelo seu código ISBN, ignorando maiúsculas e minúsculas.
     * Utiliza a Stream API para filtrar a lista.
     */
    public LivroModel buscarLivroPorIsbn(String isbn) {
        return livros.stream()
                .filter(l -> l.getIsbn() != null && l.getIsbn().equalsIgnoreCase(isbn))
                .findFirst()
                .orElse(null); // Retorna null caso nenhum livro seja encontrado
    }

    /**
     * Busca um livro pelo seu título, ignorando diferenças entre maiúsculas e minúsculas.
     * Utiliza a Stream API para filtrar a lista.
     */
    public LivroModel buscarLivroPorTitulo(String titulo) {
        return livros.stream()
                .filter(l -> l.getTitulo() != null && l.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null); // Retorna null caso nenhum livro seja encontrado
    }

    /**
     * Retorna uma cópia da lista com todos os livros cadastrados,
     * preservando o encapsulamento da lista interna.
     */
    public List<LivroModel> listarLivros() {
        return new ArrayList<>(livros);
    }
}