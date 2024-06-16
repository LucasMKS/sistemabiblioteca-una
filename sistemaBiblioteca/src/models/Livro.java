package models;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private String ISBN;
    private String categoria;
    private String quantidade;

    /**
     * Construtor da classe Livro.
     * 
     * @param titulo     O título do livro.
     * @param autor      O autor do livro.
     * @param ISBN       O ISBN do livro.
     * @param categoria  A categoria do livro.
     * @param quantidade A quantidade de exemplares do livro.
     */
    public Livro(String titulo, String autor, String ISBN, String categoria, String quantidade) {
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
        this.categoria = categoria;
        this.quantidade = quantidade;
    }

    /**
     * Obtém o ID do livro.
     * 
     * @return O ID do livro.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do livro.
     * 
     * @param id O ID do livro.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o título do livro.
     * 
     * @return O título do livro.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Define o título do livro.
     * 
     * @param titulo O título do livro.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtém o autor do livro.
     * 
     * @return O autor do livro.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Define o autor do livro.
     * 
     * @param autor O autor do livro.
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Obtém o ISBN do livro.
     * 
     * @return O ISBN do livro.
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * Define o ISBN do livro.
     * 
     * @param ISBN O ISBN do livro.
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Obtém a categoria do livro.
     * 
     * @return A categoria do livro.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define a categoria do livro.
     * 
     * @param categoria A categoria do livro.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtém a quantidade de exemplares do livro.
     * 
     * @return A quantidade de exemplares do livro.
     */
    public String getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade de exemplares do livro.
     * 
     * @param quantidade A quantidade de exemplares do livro.
     */
    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
}