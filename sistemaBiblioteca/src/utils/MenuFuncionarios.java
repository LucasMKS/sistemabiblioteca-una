package utils;

import controllers.FuncionarioController;
import models.Livro;
import java.sql.Date;
import java.util.Scanner;

public class MenuFuncionarios {
    private FuncionarioController funcionarioController;
    private Scanner scanner;

    public MenuFuncionarios(FuncionarioController funcionarioController, Scanner scanner) {
        this.funcionarioController = funcionarioController;
        this.scanner = scanner;
    }

    public void cadastrarLivro() {
        System.out.println("Informe o título do livro:");
        String titulo = scanner.nextLine();
        System.out.println("Informe o autor do livro:");
        String autor = scanner.nextLine();
        System.out.println("Informe o ISBN:");
        String isbn = scanner.nextLine();
        System.out.println("Informe a categoria:");
        String categoria = scanner.nextLine();

        Livro livro = new Livro(titulo, autor, isbn, categoria);
        if (funcionarioController.cadastrarLivro(livro)) {
            System.out.println("Livro cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar livro.");
        }
    }

    public void registrarEmprestimo() {
        System.out.println("Informe o ID do livro:");
        int livroId = Integer.parseInt(scanner.nextLine());
        System.out.println("Informe o ID do usuário:");
        int usuarioId = Integer.parseInt(scanner.nextLine());
        
        if (funcionarioController.registrarEmprestimo(livroId, usuarioId)) {
            System.out.println("Empréstimo registrado com sucesso!");
        } else {
            System.out.println("Erro ao registrar empréstimo.");
        }
    }

    public void registrarDevolucao() {
        System.out.println("Informe o ID do empréstimo:");
        int emprestimoId = Integer.parseInt(scanner.nextLine());
        System.out.println("Informe a data da devolução (aaaa-mm-dd):");
        String dataStr = scanner.nextLine();
        Date dataDevolucao = Date.valueOf(dataStr);

        if (funcionarioController.registrarDevolucao(emprestimoId, dataDevolucao)) {
            System.out.println("Devolução registrada com sucesso!");
        } else {
            System.out.println("Erro ao registrar devolução.");
        }
    }
}
