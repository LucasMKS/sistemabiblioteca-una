package views;

public class MenuFuncionario extends MenuAluno {
    public static void mostrarOpcoes() {
        MenuAluno.mostrarOpcoes();
        System.out.println("4. Cadastrar Livro");
        System.out.println("5. Registrar Emprestimo");
        System.out.println("6. Registrar Devolução");
    }


    public static void processarOpcao(int opcao) {
        // Processar opções específicas para funcionário
    }
}