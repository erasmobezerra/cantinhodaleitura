package test;

/**
 * Classe responsável por executar todos os testes manuais do sistema.
 *
 * Este runner centraliza a execução das classes de teste criadas manualmente
 * (LivroServiceTest, UsuarioServiceTest, BibliotecaServiceTest), utilizando
 * os métodos de asserção definidos em TestUtils.
 *
 * O objetivo é simular o comportamento de um framework de testes,
 * fornecendo um resumo final com o total de testes executados,
 * quantos passaram e quantos falharam.
 *
 * Em caso de falha em alguma asserção, o runner interrompe a execução
 * e exibe detalhes do erro. Caso contrário, informa que todos os testes
 * foram concluídos com sucesso.
 */

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
