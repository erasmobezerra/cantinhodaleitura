package model;

public class LivroModel {

    private final String isbn;
    private final String titulo;
    private final String autor;

    private boolean disponivel;

    public LivroModel(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "\nmodel.LivroModel {" +
                "\n  ISBN: " + isbn +
                "\n  Título: " + titulo +
                "\n  Autor: " + autor +
                "\n  Disponível: " + (disponivel ? "Sim" : "Não") +
                "\n}";
    }
}

