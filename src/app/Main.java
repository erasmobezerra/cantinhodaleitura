package app;

import data.Dados;
import service.BibliotecaService;
import service.LivroService;
import service.UsuarioService;
import ui.BibliotecaUI;
import ui.LivroUI;
import ui.MenuUI;
import ui.UsuarioUI;

public class Main {
    public static void main(String[] args) {
        LivroService livroService = new LivroService();
        UsuarioService usuarioService = new UsuarioService();
        BibliotecaService bibliotecaService = new BibliotecaService(livroService, usuarioService);

        LivroUI livroUI = new LivroUI(livroService);
        UsuarioUI usuarioUI = new UsuarioUI(usuarioService);
        BibliotecaUI bibliotecaUI = new BibliotecaUI(bibliotecaService);

        Dados dados = new Dados();

        dados.popularBiblioteca(usuarioService, livroService);

        MenuUI menu = new MenuUI(livroUI, usuarioUI, bibliotecaUI);
        menu.executar();
    }
}
