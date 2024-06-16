package views;

import java.util.Scanner;
import utils.ClearConsole;

public class CadastrarUsuario {

    private Scanner scanner;

    /**
     * Método para cadastrar um novo usuário.
     * 
     * @return Um array de Strings contendo os dados do usuário: [login, senha,
     *         tipo, nome].
     */
    public String[] cadastrarUsuario() {

        ClearConsole.clear();
        this.scanner = new Scanner(System.in);
        String[] dadosUsuario = new String[5];

        System.out.println("Informe o login usuário:");
        dadosUsuario[0] = scanner.nextLine();
        System.out.println("Informe a senha do usuário:");
        dadosUsuario[1] = scanner.nextLine();

        System.out.println("Informe o tipo do usuário:");

        do {
            System.out.println(
                    "Insira o número de acordo com o cargo desejado: \n\n1 - Aluno\n2 - Funcionario\n3 - Administrador\n");
            dadosUsuario[2] = scanner.nextLine();
            if (!dadosUsuario[2].equals("1") && !dadosUsuario[2].equals("2") && !dadosUsuario[2].equals("3")) {
                System.out.println("Erro: o número deve ser 1, 2 ou 3.");
            }
        } while (!dadosUsuario[2].equals("1") && !dadosUsuario[2].equals("2") && !dadosUsuario[2].equals("3"));

        switch (dadosUsuario[2]) {
            case "1":
                dadosUsuario[2] = "aluno";
                break;

            case "2":
                dadosUsuario[2] = "funcionario";
                break;

            case "3":
                dadosUsuario[2] = "administrador";
                break;

        }

        System.out.println("Informe o nome do usuário:");
        dadosUsuario[3] = scanner.nextLine();

        return dadosUsuario;

    }
}
