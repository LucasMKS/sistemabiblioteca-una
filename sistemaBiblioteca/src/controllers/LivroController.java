package controllers;

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