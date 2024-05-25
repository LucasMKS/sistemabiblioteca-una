package controllers;

import models.Livro;
import utils.ConnectionSQL;
import views.CadastrarLivro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;


public class FuncionarioController {

    public static int processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                break;
            case 2:
            //TO DO
                break;
            case 3:
            //TO DO
                break;
            case 4:
            CadastrarLivro livros = new CadastrarLivro();
            String[] dadosLivro = livros.cadastrarLivro();
            LivroController novoLivro = new LivroController();
            boolean cadastrar = novoLivro.cadastrarLivro(dadosLivro);
            

            break;
            default:
                System.out.println("Opção inválida");
        }

        return -1;
    }
    
    // Adicionar mais funcionalidades conforme a necessidade do sistema
}
