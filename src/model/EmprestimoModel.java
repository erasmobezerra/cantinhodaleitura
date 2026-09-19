package model;

import java.time.LocalDate;

public class EmprestimoModel {
    private final UsuarioModel usuarioModel;
    private final LivroModel livroModel;
    private final LocalDate dataEmprestimo;
    private final LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    private boolean ativo;

    public EmprestimoModel(UsuarioModel usuarioModel, LivroModel livroModel) {
        this.usuarioModel = usuarioModel;
        this.livroModel = livroModel;
        this.dataEmprestimo = LocalDate.now();
        this.dataPrevistaDevolucao = this.dataEmprestimo.plusDays(7);
        this.dataDevolucao = null;
        this.ativo = true;
    }

    public UsuarioModel getUsuario() {
        return usuarioModel;
    }

    public LivroModel getLivro() {
        return livroModel;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void registrarDevolucao() {
        this.dataDevolucao = LocalDate.now();
        this.ativo = false;
    }

    @Override
    public String toString() {
        return "model.EmprestimoModel{" +
                "usuarioModel=" + usuarioModel.getNome() +
                ", livroModel=" + livroModel.getTitulo() +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataPrevistaDevolucao=" + dataPrevistaDevolucao +
                ", dataDevolucao=" + dataDevolucao +
                ", ativo=" + ativo +
                '}';
    }
}