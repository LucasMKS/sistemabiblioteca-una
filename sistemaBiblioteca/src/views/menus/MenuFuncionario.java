package views.menus;

import utils.ClearConsole;

public class MenuFuncionario extends MenuAluno {

    public void mostrarOpcoes() {
        ClearConsole.clear();
        new MenuAluno().mostrarOpcoes();
        System.out.println("4. Cadastrar Livro");
        System.out.println("5. Registrar Emprestimo");
        System.out.println("6. Registrar Devolução");
    }
}