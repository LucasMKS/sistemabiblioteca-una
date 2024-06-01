package controllers;

import dao.FuncionarioDAO;

public class AdministradorController extends FuncionarioController {
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public int processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                break;
            case 2:
                // TO DO
                break;
            case 3:
                // TO DO
                break;
            case 4:
                FuncionarioController.processarOpcao(opcao);

                break;
            // ----------------------------------------------------------------------
            // CADASTRAR USÚARIO EM CONSTRUÇÃO --- INICIO
            // ----------------------------------------------------------------------
            case 9:

                boolean cadastrar = false;
                String[] dadosUsuario = new CadastrarUsuario().cadastrarUsuario();
                // login, senha, tipo, nome, RA
                if (dadosUsuario[2].equals("aluno")) {
                    cadastrar = insertUsuario(dadosUsuario);
                /*     
                } else if (dadosUsuario[2].equals("fun")) {
                    cadastrar = insertUsuario(dadosUsuario);
                    insertUsuario(dadosUsuario);
                } else if (dadosUsuario[2].equals("admin")) {
                    cadastrar = insertUsuario(dadosUsuario);
                    insertUsuario(dadosUsuario);
                }*/
                // UsuarioController novoUsuario = new UsuarioController();

                if (cadastrar) {
                    System.out.println("Usuário Cadastrado com sucesso!");
                }

                break;

            // ----------------------------------------------------------------------
            // CADASTRAR USÚARIO EM CONSTRUÇÃO --- FIM
            // ----------------------------------------------------------------------
            default:
                System.out.println("Opção inválida");
        }

        return -1;
    }

    public boolean adicionarFuncionario(String nome, String login, String senha, String tipo) {
        return funcionarioDAO.adicionarFuncionario(nome, login, senha, tipo);
    }
*/
    public boolean insertUsuario(String[] usuarioDadoStrings) {
        String sql = "INSERT INTO funcionarios (login, senha, tipo, nome, RA) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionSQL.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    // login, senha, tipo, nome, RA
            pstmt.setString(1, usuarioDadoStrings[0]);
            pstmt.setString(2, usuarioDadoStrings[1]);
            pstmt.setString(3, usuarioDadoStrings[2]);
            pstmt.setString(4, usuarioDadoStrings[3]);
            pstmt.setString(4, usuarioDadoStrings[4]);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean atualizarFuncionario(int id, String nome, String login, String senha, String tipo) {
        return funcionarioDAO.atualizarFuncionario(id, nome, login, senha, tipo);
    }

    // Adicione métodos para deletar e listar funcionários conforme necessário
}
