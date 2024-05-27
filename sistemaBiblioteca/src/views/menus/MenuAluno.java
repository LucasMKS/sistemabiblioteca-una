package views.menus;

import utils.ClearConsole;

public class MenuAluno{

    public static void mostrarOpcoes() {
        ClearConsole.clear();
        System.out.println("1. Consultar Livros");
        System.out.println("2. Verificar Cadastro");
        System.out.println("3. Status Emprestimo");
    }

}