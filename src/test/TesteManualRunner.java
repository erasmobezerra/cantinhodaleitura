package test;

public class TesteManualRunner {

    public static void main(String[] args) {
        TesteHelper.resetContador();

        try {
            TesteLivroService.executar();
            TesteUsuarioService.executar();
            TesteBibliotecaService.executar();

            System.out.println("\nResumo dos testes: " + TesteHelper.getTotalTestes() +
                    " executados | " + TesteHelper.getTestesPassados() + " passaram | " +
                    TesteHelper.getTestesFalharam() + " falharam.");
            System.out.println("TODOS OS TESTES MANUAIS FORAM EXECUTADOS COM SUCESSO.");
        } catch (AssertionError erro) {
            System.err.println("\nFALHA NO TESTE MANUAL: " + erro.getMessage());
            System.err.println("Resumo dos testes: " + TesteHelper.getTotalTestes() +
                    " executados | " + TesteHelper.getTestesPassados() + " passaram | " +
                    TesteHelper.getTestesFalharam() + " falharam.");
            erro.printStackTrace();
            System.exit(1);
        } catch (Exception erro) {
            System.err.println("\nERRO INESPERADO DURANTE OS TESTES: " + erro.getMessage());
            System.err.println("Resumo dos testes: " + TesteHelper.getTotalTestes() +
                    " executados | " + TesteHelper.getTestesPassados() + " passaram | " +
                    TesteHelper.getTestesFalharam() + " falharam.");
            erro.printStackTrace();
            System.exit(1);
        }
    }
}
