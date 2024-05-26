package controllers;

import views.CadastrarLivro;
import views.EmprestarLivro;

public class FuncionarioController {

    public static int processarOpcao(int opcao) {
        switch (opcao) {
            case 4:
                
                CadastrarLivro livros = new CadastrarLivro();
                String[] dadosLivro = livros.cadastrarLivro();
                LivroController novoLivro = new LivroController();
                boolean cadastrar = novoLivro.cadastrarLivro(dadosLivro);

                if(cadastrar)
                {
                    System.out.println("Livro Cadastrado com sucesso!");
                }

                break;

            case 5:

                EmprestarLivro emprestimos = new EmprestarLivro();
                String[] dadosEmprestimo = emprestimos.emprestarLivro();
                LivroController novoEmprestimo = new LivroController();
                boolean emprestar = novoEmprestimo.emprestarLivro(dadosEmprestimo);
                
                if(emprestar)
                {
                    System.out.println("Livro Cadastrado com sucesso!");
                }

                break;

            case 6:

            break;
            default:
                System.out.println("Opção inválida");
        }

        return -1;
    }
    
    // Adicionar mais funcionalidades conforme a necessidade do sistema
}
