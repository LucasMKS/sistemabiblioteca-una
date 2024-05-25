package views.menus;

import controllers.FuncionarioController;

public class MenuFuncionario extends MenuAluno {

    public static void mostrarOpcoes() {
        MenuAluno.mostrarOpcoes();
        System.out.println("4. Cadastrar Livro");
        System.out.println("5. Registrar Emprestimo");
        System.out.println("6. Registrar Devolução");
        System.out.println("\nDigite sua opçao:");
    }

    public static void processarOpcao(int opcao) {
        
        switch (opcao) {
            case 1:
                MenuAluno.processarOpcao(opcao);
                break;
            case 2:
                MenuAluno.processarOpcao(opcao);
                break;
            case 3:
                MenuAluno.processarOpcao(opcao);
                break;
            case 4:
                if (FuncionarioController.cadastrarLivro()) {
                    System.out.println("Livro cadastrado com sucesso!");
                } else {
                    System.out.println("Erro ao cadastrar livro.");
                }
                break;
            case 5:
                if (FuncionarioController.registrarEmprestimo()) {
                    System.out.println("Emprestimo cadastrado com sucesso!");
                } else {
                    System.out.println("Erro ao cadastrar emprestimo.");
                }
            
                break;
            // case 6:
            //     if (FuncionarioController.registrarDevolucao()) {
            //         System.out.println("Devolução cadastrado com sucesso!");
            //     } else {
            //         System.out.println("Erro ao cadastrar devolução.");
            //     }
            
            //     break;
            default:
                System.out.println("Opção inválida");
        }
    }
}