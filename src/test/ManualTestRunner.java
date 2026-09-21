package test;

public class ManualTestRunner {

    public static void main(String[] args) {
        TestUtils.resetContador();

        try {
            LivroServiceTest.executar();
            UsuarioServiceTest.executar();
            BibliotecaServiceTest.executar();

            System.out.println("\nResumo dos testes: " + TestUtils.getTotalTestes() +
                    " executados | " + TestUtils.getTestesPassados() + " passaram | " +
                    TestUtils.getTestesFalharam() + " falharam.");
            System.out.println("TODOS OS TESTES MANUAIS FORAM EXECUTADOS COM SUCESSO.");
        } catch (AssertionError erro) {
            System.err.println("\nFALHA NO TESTE MANUAL: " + erro.getMessage());
            System.err.println("Resumo dos testes: " + TestUtils.getTotalTestes() +
                    " executados | " + TestUtils.getTestesPassados() + " passaram | " +
                    TestUtils.getTestesFalharam() + " falharam.");
            erro.printStackTrace();
            System.exit(1);
        } catch (Exception erro) {
            System.err.println("\nERRO INESPERADO DURANTE OS TESTES: " + erro.getMessage());
            System.err.println("Resumo dos testes: " + TestUtils.getTotalTestes() +
                    " executados | " + TestUtils.getTestesPassados() + " passaram | " +
                    TestUtils.getTestesFalharam() + " falharam.");
            erro.printStackTrace();
            System.exit(1);
        }
    }
}
