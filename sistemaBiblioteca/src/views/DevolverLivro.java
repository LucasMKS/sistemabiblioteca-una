package views;
import java.util.Scanner;

public class DevolverLivro {

    private Scanner scanner;

    public String[] devolverLivro() {

        String[] dados = new String[2]; 

        this.scanner = new Scanner(System.in);
        System.out.println("Informe o ID do aluno:");
        dados[0] = scanner.nextLine();
        System.out.println("Informe o ISBN do livro:");
        dados[1] = scanner.nextLine();

        return dados;

    }

}
