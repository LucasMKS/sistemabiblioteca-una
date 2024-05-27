package controllers;

import views.CadastrarLivro;
import views.CadastrarUsuario;
import views.EmprestarLivro;
import views.DevolverLivro;

public class FuncionarioController {

    public static int processarOpcao(int opcao) {
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

                // ----------------------------------------------------------------------
                // CADASTRAR USÚARIO EM CONSTRUÇÃO --- INICIO
                // ----------------------------------------------------------------------
            case 9:

                CadastrarUsuario usuario = new CadastrarUsuario();
                String[] dadosUsuario = usuario.cadastrarUsuario();
                if (dadosUsuario[2].equals("aluno")) {
                    AlunoController novoUsuario = new AlunoController();
                    cadastrar = novoUsuario.cadastrarUsuario(dadosUsuario);
                }
               // UsuarioController novoUsuario = new UsuarioController();
                

                if (cadastrar) {
                    System.out.println("Usuário Cadastrado com sucesso!");
                }

                break;
        }

        // ----------------------------------------------------------------------
        // CADASTRAR USÚARIO EM CONSTRUÇÃO --- FIM
        // ----------------------------------------------------------------------

        return -1;
    }

    // Adicionar mais funcionalidades conforme a necessidade do sistema
}
