package service;

import model.UsuarioModel;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    private final List<UsuarioModel> usuarios = new ArrayList<>();

    public boolean adicionarUsuario(UsuarioModel usuarioModel) {
        if (usuarioModel == null) return false;

        if (buscarUsuarioPorCpf(usuarioModel.getCpf()) != null ||
                buscarUsuarioPorNome(usuarioModel.getNome()) != null) {
            return false; // já existe
        }

        usuarios.add(usuarioModel);
        return true;
    }

    public UsuarioModel buscarUsuarioPorNome(String nome) {
        return usuarios.stream()
                .filter(u -> u.getNome() != null && u.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    public UsuarioModel buscarUsuarioPorCpf(String cpf) {
        return usuarios.stream()
                .filter(u -> u.getCpf() != null && u.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }

    public List<UsuarioModel> listarUsuarios() {
        return new ArrayList<>(usuarios);
    }
}
