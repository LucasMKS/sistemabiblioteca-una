package controllers;

import java.util.List;
import dao.LivroDAO;
import dao.AlunoDAO;
import models.Credenciais;
import utils.ClearConsole;

public class AlunoController {
    /**
     * Processa a opção escolhida pelo aluno.
     * 
     * @param opcao              A opção escolhida pelo aluno.
     * @param credenciaisValidas As credenciais válidas do aluno.
     */
    public void processarOpcao(int opcao, Credenciais credenciaisValidas) {
        switch (opcao) {
            case 1:
                ClearConsole.clear();
                LivroDAO.listarLivros();
                break;
            case 2:
                ClearConsole.clear();
                AlunoDAO.verificarCadastro(credenciaisValidas);
                break;
            case 3:
                ClearConsole.clear();
                LivroDAO statusEmprestimo = new LivroDAO();
                List<String> livrosEmprestados = statusEmprestimo.verificarStatusEmprestimo(credenciaisValidas);

                if (livrosEmprestados.isEmpty()) {
                    System.out.println("Nenhum livro emprestado no momento!");
                } else {
                    System.out.println("Livros emprestados:");
                    for (String livro : livrosEmprestados) {
                        System.out.println(livro);
                    }
                }
                break;
            default:
                System.out.println("Opção inválida");
        }
    }

}
