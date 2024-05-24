package views;

public class MenuAdministrador extends MenuFuncionario {
    public static void mostrarOpcoes() {
        MenuFuncionario.mostrarOpcoes();
        System.out.println("7. Alterar prazo devolucao");
        System.out.println("8. Alterar permissoes usuario");
        System.out.println("9. Cadastrar usuario");
    }


    public static void processarOpcao(int opcao) {
        // Processar opções específicas para funcionário
    }
}