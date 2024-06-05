package controllers;

import models.Credenciais;
import views.CadastrarLivro;
import views.EmprestarLivro;
import views.DevolverLivro;

public class FuncionarioController {

    public void processarOpcao(int opcao, Credenciais credenciaisValidas) {
        AlunoController alunoController = new AlunoController();
        switch (opcao) {
            case 1:
                alunoController.processarOpcao(opcao, credenciaisValidas);
                break;
            case 2:
                alunoController.processarOpcao(opcao, credenciaisValidas);
                break;
            case 3:
                alunoController.processarOpcao(opcao, credenciaisValidas);
                break;
            case 4:
                CadastrarLivro livros = new CadastrarLivro();
                String[] dadosLivro = livros.cadastrarLivro();
                LivroController novoLivro = new LivroController();
                boolean cadastrar = novoLivro.cadastrarLivro(dadosLivro);
                if (cadastrar) {
                    System.out.println("Livro Cadastrado com sucesso!");
                }
                break;
            case 5:
                EmprestarLivro emprestimos = new EmprestarLivro();
                String[] dadosEmprestimo = emprestimos.emprestarLivro();
                LivroController novoEmprestimo = new LivroController();
                novoEmprestimo.emprestarLivro(dadosEmprestimo);
                break;
            case 6:
                DevolverLivro devolucoes = new DevolverLivro();
                String[] dadosDevolucao = devolucoes.devolverLivro();
                LivroController novaDevolucao = new LivroController();
                novaDevolucao.devolverLivro(dadosDevolucao);
                break;
            default:
                System.out.println("Opção inválida");
         
        }
    }
    // Adicionar mais funcionalidades conforme a necessidade do sistema
}
