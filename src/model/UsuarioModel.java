package model;

public class UsuarioModel {

    private final String nome;
    private final String cpf;
    private LivroModel livroModelEmprestado;

    public UsuarioModel(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.livroModelEmprestado = null;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LivroModel getLivroEmprestado() {
        return livroModelEmprestado;
    }

    @Override
    public String toString() {
        return "\nmodel.UsuarioModel {" +
                "\n  Nome: " + nome +
                "\n  CPF: " + cpf +
                "\n  model.LivroModel Emprestado: " + (livroModelEmprestado != null ? livroModelEmprestado : "Nenhum") +
                "\n}";
    }

    public void emprestarLivro(LivroModel livroModel) {
        if (livroModel.isDisponivel()) {
            this.livroModelEmprestado = livroModel;
            livroModel.setDisponivel(false);
            System.out.println(this.nome + " fez empréstimo do livroModel " + livroModel.getTitulo() + ".");
        } else {
            System.out.println("O livroModel " + livroModel.getTitulo() + " não está disponível para empréstimo. Boa leitura!");
        }
    }

    public void devolverLivro() {
        if (this.livroModelEmprestado != null) {
            this.livroModelEmprestado.setDisponivel(true);
            System.out.println(this.nome + " devolveu o livro " + this.livroModelEmprestado.getTitulo() + ".");
            this.livroModelEmprestado = null;
        } else {
            System.out.println(this.nome + " não tem nenhum livro emprestado para devolver.");
        }
    }
}

