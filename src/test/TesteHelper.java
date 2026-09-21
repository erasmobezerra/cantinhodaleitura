package test;

public class TesteHelper {

    private static int totalTestes = 0;
    private static int testesPassados = 0;
    private static int testesFalharam = 0;

    public static void resetContador() {
        totalTestes = 0;
        testesPassados = 0;
        testesFalharam = 0;
    }

    public static int getTotalTestes() {
        return totalTestes;
    }

    public static int getTestesPassados() {
        return testesPassados;
    }

    public static int getTestesFalharam() {
        return testesFalharam;
    }

    private static void registrarResultado(boolean sucesso, String mensagem) {
        totalTestes++;
        if (sucesso) {
            testesPassados++;
        } else {
            testesFalharam++;
            throw new AssertionError(mensagem);
        }
    }

    public static void assertTrue(boolean condicao, String mensagem) {
        registrarResultado(condicao, mensagem);
    }

    public static void assertFalse(boolean condicao, String mensagem) {
        registrarResultado(!condicao, mensagem);
    }

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
}
