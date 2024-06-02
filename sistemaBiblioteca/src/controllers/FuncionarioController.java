package controllers;

import views.CadastrarLivro;
import views.EmprestarLivro;
import views.DevolverLivro;

public class FuncionarioController {

    public void processarOpcao(int opcao) {
        switch (opcao) {
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
                boolean emprestar = novoEmprestimo.emprestarLivro(dadosEmprestimo);

                if (emprestar) {
                    System.out.println("Emprestimo realizado com sucesso!");
                }

                break;

            case 6:

                DevolverLivro devolucoes = new DevolverLivro();
                String[] dadosDevolucao = devolucoes.devolverLivro();
                LivroController novaDevolucao = new LivroController();
                boolean devolucao = novaDevolucao.devolverLivro(dadosDevolucao);

                if (devolucao) {
                    System.out.println("Emprestimo realizado com sucesso!");
                }

                break;
            default:
                System.out.println("Opção inválida");
         
        }
    }
    // Adicionar mais funcionalidades conforme a necessidade do sistema
}
