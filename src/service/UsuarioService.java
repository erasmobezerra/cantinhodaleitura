package service;

import model.UsuarioModel;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    // Lista interna em memória para armazenar os usuários cadastrados no sistema
    private final List<UsuarioModel> usuarios = new ArrayList<>();

    /**
     * Adiciona um novo usuário ao sistema após validar se o objeto não é nulo
     * e se já não existe outro cadastro com o mesmo CPF ou nome.
     */
    public boolean adicionarUsuario(UsuarioModel usuarioModel) {
        // Validação inicial para garantir que o objeto passado não é nulo
        if (usuarioModel == null) return false;

        // Verifica se já existe um usuário cadastrado com o mesmo CPF ou o mesmo nome
        if (buscarUsuarioPorCpf(usuarioModel.getCpf()) != null ||
                buscarUsuarioPorNome(usuarioModel.getNome()) != null) {
            return false; // Retorna falso indicando que o usuário já existe no sistema
        }

        // Adiciona o usuário à lista e retorna verdadeiro indicando sucesso
        usuarios.add(usuarioModel);
        return true;
    }

    /**
     * Busca um usuário pelo seu nome, ignorando diferenças entre maiúsculas e minúsculas.
     * Utiliza a Stream API para filtrar a lista.
     */
    public UsuarioModel buscarUsuarioPorNome(String nome) {
        return usuarios.stream()
                .filter(u -> u.getNome() != null && u.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null); // Retorna null caso nenhum usuário seja encontrado
    }

    /**
     * Busca um usuário pelo seu número de CPF.
     * Utiliza a Stream API para filtrar a lista por igualdade exata.
     */
    public UsuarioModel buscarUsuarioPorCpf(String cpf) {
        return usuarios.stream()
                .filter(u -> u.getCpf() != null && u.getCpf().equals(cpf))
                .findFirst()
                .orElse(null); // Retorna null caso nenhum usuário seja encontrado
    }

    /**
     * Retorna uma cópia da lista com todos os usuários cadastrados,
     * preservando o encapsulamento da lista interna.
     */
    public List<UsuarioModel> listarUsuarios() {
        return new ArrayList<>(usuarios);
    }
}