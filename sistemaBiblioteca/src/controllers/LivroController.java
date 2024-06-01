package controllers;

import models.Livro;
import utils.ConnectionSQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dao.LivroDAO;

public class LivroController {
    private LivroDAO livroDAO = new LivroDAO();

    public boolean cadastrarLivro(String[] livroDadoStrings) {
        return livroDAO.cadastrarLivro(livroDadoStrings);
    }  

    // Método para registrar um empréstimo de livro
    public boolean emprestarLivro(String[] dados) {
        return livroDAO.emprestarLivro(dados);
    }
    

    // Método para registrar a devolução de um livro
    public boolean devolverLivro(String[] dados) {
        return livroDAO.devolverLivro(dados);
    }


}