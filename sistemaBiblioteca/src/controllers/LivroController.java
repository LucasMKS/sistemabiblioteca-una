package controllers;

import models.Livro;
import dao.LivroDAO;

public class LivroController {
    private LivroDAO livroDAO;

    public LivroController() {
        this.livroDAO = new LivroDAO();
    }

    public boolean cadastrarLivro(String titulo, String autor, String isbn, String categoria) {
        Livro livro = new Livro(titulo, autor, isbn, categoria);
        return livroDAO.cadastrarLivro(livro);
    }
}