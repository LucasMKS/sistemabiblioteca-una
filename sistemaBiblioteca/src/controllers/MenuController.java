package controllers;

import java.util.Scanner;

public class MenuController {
    private static final Scanner scanner = new Scanner(System.in);

    public static void mostrarMenu(String tipo){

        boolean continuar = true;
        while (continuar) {
            System.out.println("\nMenu Administrador:");
            System.out.println("1. Gerenciar Funcionários (Adicionar/Atualizar)");
            System.out.println("2. Cadastrar Livro");
            System.out.println("3. Registrar Empréstimo");
            System.out.println("4. Registrar Devolução");
            System.out.println("5. Sair");
            
            //TODO

            if(tipo.equals("administrador"))
            {
                System.out.println("teste");
            }

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir linha
        }
    }
}
