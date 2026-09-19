package service;

import model.LivroModel;
import java.util.ArrayList;
import java.util.List;

public class LivroService {

    private final List<LivroModel> livros = new ArrayList<>();

    public boolean adicionarLivro(LivroModel livroModel) {
        if (buscarLivroPorIsbn(livroModel.getIsbn()) != null) {
            return false; // já existe
        }
        livros.add(livroModel);
        return true;
    }

    public LivroModel buscarLivroPorIsbn(String isbn) {
        return livros.stream()
                .filter(l -> l.getIsbn() != null && l.getIsbn().equalsIgnoreCase(isbn))
                .findFirst()
                .orElse(null);
    }

    public LivroModel buscarLivroPorTitulo(String titulo) {
        return livros.stream()
                .filter(l -> l.getTitulo() != null && l.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
    }

    public List<LivroModel> listarLivros() {
        return new ArrayList<>(livros);
    }
}
