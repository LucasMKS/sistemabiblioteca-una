package controllers;

import dao.FuncionarioDAO;

public class AdministradorController extends FuncionarioController {
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

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
            FuncionarioController.processarOpcao(opcao);

            break;
            default:
                System.out.println("Opção inválida");
        }

        return -1;
    }

    public boolean adicionarFuncionario(String nome, String login, String senha, String tipo) {
        return funcionarioDAO.adicionarFuncionario(nome, login, senha, tipo);
    }

    public boolean atualizarFuncionario(int id, String nome, String login, String senha, String tipo) {
        return funcionarioDAO.atualizarFuncionario(id, nome, login, senha, tipo);
    }

    // Adicione métodos para deletar e listar funcionários conforme necessário
}
