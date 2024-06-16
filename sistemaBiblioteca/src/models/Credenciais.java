package models;

public class Credenciais {
    private String login;
    private String senha;
    private String nome;
    private String tipo;
    private int id;

    /**
     * Construtor para criar credenciais com nome e tipo.
     * 
     * @param nome O nome do usuário.
     * @param tipo O tipo de credencial.
     */
    public Credenciais(String nome, String tipo) {
        this(nome, tipo, "", "", 0);
    }

    /**
     * Construtor para criar credenciais com todos os detalhes.
     * 
     * @param login O login do usuário.
     * @param senha A senha do usuário.
     * @param nome  O nome do usuário.
     * @param tipo  O tipo de credencial.
     * @param id    O identificador do usuário.
     */
    public Credenciais(String login, String senha, String nome, String tipo, int id) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.tipo = tipo;
        this.id = id;
    }

    /**
     * Obtém o login do usuário.
     * 
     * @return O login do usuário.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Define o login do usuário.
     * 
     * @param login O novo login do usuário.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Obtém a senha do usuário.
     * 
     * @return A senha do usuário.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define a senha do usuário.
     * 
     * @param senha A nova senha do usuário.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Obtém o nome do usuário.
     * 
     * @return O nome do usuário.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do usuário.
     * 
     * @param nome O novo nome do usuário.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o tipo de credencial do usuário.
     * 
     * @return O tipo de credencial do usuário.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define o tipo de credencial do usuário.
     * 
     * @param tipo O novo tipo de credencial do usuário.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtém o identificador do usuário.
     * 
     * @return O identificador do usuário.
     */
    public int getId() {
        return id;
    }
}