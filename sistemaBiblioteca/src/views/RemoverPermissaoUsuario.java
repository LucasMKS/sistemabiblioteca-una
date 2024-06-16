package views;

import java.util.Scanner;
import utils.ClearConsole;

public class RemoverPermissaoUsuario {

    private Scanner scanner;

    /**
     * Método para remover permissão do usuário.
     * 
     * @return Um array de Strings contendo os dados do usuário.
     */
    public String[] removerPermissao() {

        ClearConsole.clear(); // Limpa o console
        this.scanner = new Scanner(System.in); // Inicializa o scanner
        String[] dadosUsuario = new String[2]; // Array para armazenar os dados do usuário
        boolean dadosValidos = false; // Flag para verificar se os dados são válidos

        while (!dadosValidos) {
            System.out.println("Informe a Matricula do usuário:"); // Solicita a matrícula do usuário
            dadosUsuario[0] = scanner.nextLine(); // Lê a matrícula do usuário
        }

        return dadosUsuario; // Retorna os dados do usuário
    }
}
