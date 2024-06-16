package controllers;

import models.Credenciais;
import utils.ConnectionSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutenticacaoController {
    /**
     * Autentica um usuário com base nas credenciais fornecidas.
     * 
     * @param credenciais as credenciais do usuário a serem autenticadas
     * @return as credenciais do usuário autenticado, ou null se a autenticação
     *         falhar
     */
    public Credenciais autenticarUsuario(Credenciais credenciais) {
        String query = "SELECT nome, tipo, id_usuarios FROM usuarios WHERE login = ? AND senha = ?";
        Credenciais usuarioAutenticado = null;

        try (Connection connection = ConnectionSQL.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, credenciais.getLogin());
            statement.setString(2, credenciais.getSenha());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String tipo = resultSet.getString("tipo");
                    int id = resultSet.getInt("id_usuarios");
                    usuarioAutenticado = new Credenciais(credenciais.getLogin(), credenciais.getSenha(), nome, tipo,
                            id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarioAutenticado;
    }
}