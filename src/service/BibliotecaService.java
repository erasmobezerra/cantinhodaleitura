package service;

import model.EmprestimoModel;
import model.LivroModel;
import model.UsuarioModel;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaService {

    private final ArrayList<EmprestimoModel> emprestimoModels;
    private final LivroService livroService;
    private final UsuarioService usuarioService;

    public BibliotecaService() {
        this(new LivroService(), new UsuarioService());
    }

    public BibliotecaService(LivroService livroService, UsuarioService usuarioService) {
        this.emprestimoModels = new ArrayList<>();
        this.livroService = livroService;
        this.usuarioService = usuarioService;
    }

    public boolean emprestarLivro(String nomeUsuario, String isbnLivro) {
        UsuarioModel usuario = usuarioService.buscarUsuarioPorNome(nomeUsuario);
        if (usuario == null || usuario.getLivroEmprestado() != null) return false;

        LivroModel livro = livroService.buscarLivroPorIsbn(isbnLivro);
        if (livro == null || !livro.isDisponivel()) return false;

        EmprestimoModel emprestimo = new EmprestimoModel(usuario, livro);
        emprestimoModels.add(emprestimo);
        usuario.emprestarLivro(livro);
        return true;
    }

    public boolean devolverLivro(String nomeUsuario, String isbnLivro) {
        UsuarioModel usuario = usuarioService.buscarUsuarioPorNome(nomeUsuario);
        if (usuario == null || usuario.getLivroEmprestado() == null) return false;

        LivroModel livro = livroService.buscarLivroPorIsbn(isbnLivro);
        if (livro == null) return false;

        EmprestimoModel emprestimoAtivo = buscarEmprestimoAtivo(usuario, livro);
        if (emprestimoAtivo == null) return false;

        emprestimoAtivo.registrarDevolucao();
        livro.setDisponivel(true);
        usuario.devolverLivro();
        return true;
    }

    public ArrayList<EmprestimoModel> listarEmprestimos() {
        return emprestimoModels;
    }

    private EmprestimoModel buscarEmprestimoAtivo(UsuarioModel usuario, LivroModel livro) {
        return emprestimoModels.stream()
                .filter(e -> e.isAtivo() && e.getUsuario() == usuario && e.getLivro() == livro)
                .findFirst()
                .orElse(null);
    }

}

