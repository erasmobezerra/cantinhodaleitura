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
        // Inicializa as camadas de serviço responsáveis pelas regras de negócio de Livros e Usuários
        LivroService livroService = new LivroService();
        UsuarioService usuarioService = new UsuarioService();

        // Inicializa o serviço principal da biblioteca, injetando as dependências de livros e usuários
        BibliotecaService bibliotecaService = new BibliotecaService(livroService, usuarioService);

        // Inicializa as interfaces de usuário (UI) específicas para cada domínio, injetando seus respectivos serviços
        LivroUI livroUI = new LivroUI(livroService);
        UsuarioUI usuarioUI = new UsuarioUI(usuarioService);
        BibliotecaUI bibliotecaUI = new BibliotecaUI(bibliotecaService);

        // Instancia a classe utilitária responsável por carregar dados iniciais (mock/seed)
        Dados dados = new Dados();

        // Popula o sistema com dados iniciais utilizando os serviços de usuário e livro
        dados.popularBiblioteca(usuarioService, livroService);

        // Configura a interface do menu principal integrando todas as UIs parciais
        MenuUI menu = new MenuUI(livroUI, usuarioUI, bibliotecaUI);

        // Inicia a execução da aplicação exibindo o menu interativo para o usuário
        menu.executar();
    }
}
