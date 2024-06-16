package controllers;

import dao.LivroDAO;

public class LivroController {
    private LivroDAO livroDAO = new LivroDAO();

    /**
     * Método para cadastrar um novo livro.
     * 
     * @param livroDadoStrings Array contendo os dados do livro a ser cadastrado.
     * @return true se o livro foi cadastrado com sucesso, false caso contrário.
     */
    public boolean cadastrarLivro(String[] livroDadoStrings) {
        return livroDAO.cadastrarLivro(livroDadoStrings);
    }

    /**
     * Método para registrar o empréstimo de um livro.
     * 
     * @param dados Array contendo os dados do empréstimo.
     * @return true se o empréstimo foi registrado com sucesso, false caso
     *         contrário.
     */
    public boolean emprestarLivro(String[] dados) {
        return livroDAO.emprestarLivro(dados);
    }

    /**
     * Método para registrar a devolução de um livro.
     * 
     * @param dados Array contendo os dados da devolução.
     * @return true se a devolução foi registrada com sucesso, false caso contrário.
     */
    public boolean devolverLivro(String[] dados) {
        return livroDAO.devolverLivro(dados);
    }

    /**
     * Método para alterar o prazo de devolução de um livro.
     * 
     * @param dados Array contendo os novos dados do prazo de devolução.
     * @return true se o prazo foi alterado com sucesso, false caso contrário.
     */
    public boolean alterarPrazoDevolucao(String[] dados) {
        return livroDAO.alterarPrazoDevolucao(dados);
    }

}