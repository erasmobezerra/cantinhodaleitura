package test;

public class TestUtils {

    private static int totalTestes = 0;
    private static int testesPassados = 0;
    private static int testesFalharam = 0;

    public static int getTotalTestes() {
        return totalTestes;
    }

    public static int getTestesPassados() {
        return testesPassados;
    }

    public static int getTestesFalharam() {
        return testesFalharam;
    }

    // ********************************************************
    // Métodos de asserção para facilitar a escrita dos testes
    // ********************************************************
    //
    // Uma asserção (ou assertion, em inglês) é uma instrução usada em testes de software
    // para verificar se uma condição esperada é verdadeira durante a execução do programa.
    //
    // Significado
    // É como uma declaração de confiança: você afirma que algo deve ser verdadeiro em determinado ponto do código.
    // Se a condição não for satisfeita, a asserção falha e geralmente lança uma exceção ou interrompe o teste,
    // indicando que o comportamento do programa não está de acordo com o esperado.

    // Asserção para verificar se uma condição é verdadeira
    public static void assertTrue(boolean condicao, String mensagem) {
        registrarResultado(condicao, mensagem);
    }

    // Asserção para verificar se uma condição é falsa
    public static void assertFalse(boolean condicao, String mensagem) {
        registrarResultado(!condicao, mensagem);
    }

    // // Asserção para verificar se duas condições são iguais
    public static void assertEquals(Object esperado, Object atual, String mensagem) {
        boolean sucesso = false;

        if (esperado == null && atual == null) {
            sucesso = true;
        } else if (esperado != null && esperado.equals(atual)) {
            sucesso = true;
        }

        if (sucesso) {
            registrarResultado(true, "");
            return;
        }

        registrarResultado(false, mensagem + " | esperado: " + esperado + " | atual: " + atual);
    }

    // Registra o resultado de um teste
    private static void registrarResultado(boolean sucesso, String mensagem) {
        totalTestes++;
        if (sucesso) {
            testesPassados++;
        } else {
            testesFalharam++;
            throw new AssertionError(mensagem); // Lança uma exceção para indicar falha no teste
        }
    }

    // Reseta os contadores de testes para permitir uma nova execução limpa
    public static void resetContador() {
        totalTestes = 0;
        testesPassados = 0;
        testesFalharam = 0;
    }
}
