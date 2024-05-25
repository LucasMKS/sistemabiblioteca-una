package views.menus;

public class MenuAluno{


    public static void mostrarOpcoes() {
        System.out.println("1. Consultar Livros");
        System.out.println("2. Verificar Cadastro");
        System.out.println("3. Status Emprestimo");
    }

    public static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                // Implementar funcionalidade de consultar livros
                System.out.println("Consultando livros...");
                break;
            case 2:
                // Implementar funcionalidade de verificar cadastro
                System.out.println("Verificando cadastro...");
                break;
            case 3:
                // Implementar funcionalidade de verificar status de empréstimo
                System.out.println("Verificando status de empréstimo...");
                break;
            default:
                System.out.println("Opção inválida");
        }
    }

}