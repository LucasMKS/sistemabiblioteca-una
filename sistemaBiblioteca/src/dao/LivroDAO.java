package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.Livro;
import utils.ConnectionSQL;

public class LivroDAO {
    private Connection connection;

    public LivroDAO() {
        this.connection = ConnectionSQL.getConnection(); // Obter conexão com o banco de dados
    }

    public boolean cadastrarLivro(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, isbn, categoria) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getISBN());
            stmt.setString(4, livro.getCategoria());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
