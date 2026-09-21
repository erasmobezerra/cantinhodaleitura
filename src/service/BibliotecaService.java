package service;

import model.EmprestimoModel;
import model.LivroModel;
import model.UsuarioModel;

import java.util.ArrayList;
import java.util.Scanner; // Importação presente, embora não utilizada diretamente nesta classe

public class BibliotecaService {

    // Lista que armazena o histórico e o estado de todos os empréstimos realizados
    private final ArrayList<EmprestimoModel> emprestimoModels;
    // Serviço responsável por gerenciar as operações e buscas de livros
    private final LivroService livroService;
    // Serviço responsável por gerenciar as operações e buscas de usuários
    private final UsuarioService usuarioService;

    /**
     * Construtor que permite a injeção de dependências dos serviços de livro e usuário (padrão ideal para testes e reaproveitamento).
     */
    public BibliotecaService(LivroService livroService, UsuarioService usuarioService) {
        this.emprestimoModels = new ArrayList<>();
        this.livroService = livroService;
        this.usuarioService = usuarioService;
    }

    /**
     * Realiza o empréstimo de um livro para um usuário, validando se o usuário existe, se já não possui
     * um livro emprestado, se o livro existe e se ele está disponível.
     */
    public boolean emprestarLivro(String nomeUsuario, String isbnLivro) {
        // Busca o usuário pelo nome cadastrado
        UsuarioModel usuario = usuarioService.buscarUsuarioPorNome(nomeUsuario);
        // Valida se o usuário existe ou se já tem algum livro emprestado (retorna falso caso afirmativo)
        if (usuario == null || usuario.getLivroEmprestado() != null) return false;

        // Busca o livro utilizando o ISBN informado
        LivroModel livro = livroService.buscarLivroPorIsbn(isbnLivro);
        // Valida se o livro existe ou se já está emprestado/indisponível
        if (livro == null || !livro.isDisponivel()) return false;

        // Cria o registro do empréstimo vinculando o usuário e o livro
        EmprestimoModel emprestimo = new EmprestimoModel(usuario, livro);
        // Adiciona o empréstimo à lista geral da biblioteca
        emprestimoModels.add(emprestimo);
        // Atualiza o estado do usuário para refletir que ele pegou um livro emprestado
        usuario.emprestarLivro(livro);
        return true;
    }

    /**
     * Realiza a devolução de um livro emprestado por um usuário, validando se ambos existem,
     * se o usuário realmente está com o livro e se há um empréstimo ativo correspondente.
     */
    public boolean devolverLivro(String nomeUsuario, String isbnLivro) {
        // Busca o usuário pelo nome
        UsuarioModel usuario = usuarioService.buscarUsuarioPorNome(nomeUsuario);
        // Valida se o usuário existe e se possui algum livro emprestado
        if (usuario == null || usuario.getLivroEmprestado() == null) return false;

        // Busca o livro pelo ISBN
        LivroModel livro = livroService.buscarLivroPorIsbn(isbnLivro);
        // Valida se o livro existe no sistema
        if (livro == null) return false;

        // Busca o registro de empréstimo ativo que vincula este usuário a este livro específico
        EmprestimoModel emprestimoAtivo = buscarEmprestimoAtivo(usuario, livro);
        // Se não encontrar um empréstimo ativo válido, interrompe a operação
        if (emprestimoAtivo == null) return false;

        // Marca o empréstimo como finalizado/devolvido
        emprestimoAtivo.registrarDevolucao();
        // Torna o livro disponível novamente no sistema
        livro.setDisponivel(true);
        // Remove a referência do livro emprestado no perfil do usuário
        usuario.devolverLivro();
        return true;
    }

    /**
     * Retorna a lista completa de empréstimos registrados na biblioteca.
     */
    public ArrayList<EmprestimoModel> listarEmprestimos() {
        return emprestimoModels;
    }

    /**
     * Método auxiliar (privado) para encontrar um empréstimo ativo específico combinando o usuário e o livro.
     * Utiliza Stream API para filtrar a lista de empréstimos.
     */
    private EmprestimoModel buscarEmprestimoAtivo(UsuarioModel usuario, LivroModel livro) {
        return emprestimoModels.stream()
                .filter(e -> e.isAtivo() && e.getUsuario() == usuario && e.getLivro() == livro)
                .findFirst()
                .orElse(null);
    }
}