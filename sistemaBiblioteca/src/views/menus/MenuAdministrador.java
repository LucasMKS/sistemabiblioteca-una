package views.menus;

import controllers.AdministradorController;

public class MenuAdministrador extends MenuFuncionario {
    public static void mostrarOpcoes() {
        MenuFuncionario.mostrarOpcoes();
        System.out.println("7. Alterar prazo devolucao");
        System.out.println("8. Alterar permissoes usuario");
        System.out.println("9. Cadastrar usuario");
    }

    public static void processarOpcao(int opcao) {
        
        switch (opcao) {
            case 1:
                MenuAluno.processarOpcao(opcao);
            case 2:
                MenuAluno.processarOpcao(opcao);
            case 3:
                MenuAluno.processarOpcao(opcao);
            case 4:
                MenuFuncionario.processarOpcao(opcao);
            case 5:
                MenuFuncionario.processarOpcao(opcao);
            case 6:
                MenuFuncionario.processarOpcao(opcao);
            case 7:
                // if (AdministradorController.alterarPrazoDevolucao()) {
                //     System.out.println("Prazo de devolução alterado com sucesso!");
                // } else {
                //     System.out.println("Erro ao alterar prazo de devolução.");
                // }
                // break;
            case 8:
                break;
            case 9:
                break;
            default:
                System.out.println("Opção inválida");
        }
    }
}