# Sistema de Gerenciamento de Biblioteca

Este projeto é um sistema de gerenciamento de biblioteca desenvolvido em Java, utilizando Maven e MySQL. O sistema permite autenticação de usuários, consulta, cadastro de livros, empréstimos e devoluções de livros.

## Funcionalidades

- **Autenticação de Usuário**: Login de usuários (funcionários e alunos).
- **Consulta de Livros**: Permite aos usuários buscar livros disponíveis na biblioteca.
- **Cadastro de Livros**: Funcionários podem cadastrar novos livros no sistema.
- **Empréstimo de Livros**: Usuários podem solicitar o empréstimo de livros.
- **Devolução de Livros**: Funcionários podem registrar a devolução de livros.
- **Manutenção de Informações**: Funcionários podem adicionar, editar ou remover informações sobre livros, editoras, alunos, etc.
- **Manutenção de Funcionários**: Administradores podem adicionar, editar ou remover informações sobre funcionários.
- **Geração de Multas**: O sistema calcula multas para atrasos na devolução de livros.

## Estrutura do Projeto

O projeto é dividido nas seguintes camadas:

- **Models**: Contém as classes que representam os dados do sistema, como `Livro`, `Usuario`, `Emprestimo`.
- **Controllers**: Contém as classes que controlam o fluxo de dados entre as models e as views, como `LivroController`, `UsuarioController`.
- **DAOs**: Contém as classes que gerenciam a persistência de dados no banco de dados, como `LivroDAO`, `UsuarioDAO`.
- **Views**: Contém as classes que gerenciam a interface do usuário, como menus e entradas de dados.
- **Utils**: Contém classes utilitárias, como `ConnectionSQL` para gerenciar a conexão com o banco de dados.

## Requisitos

- **Java**: Versão 8 ou superior
- **MySQL**

## Configuração do Ambiente

1. **Clone o Repositório**:
    ```bash
    git clone https://github.com/seu-usuario/seu-repositorio.git
    cd seu-repositorio
    ```

2. **Configuração do Banco de Dados**:
    - Crie um banco de dados no MySQL:
        ```sql
        CREATE DATABASE sistemabiblioteca;
        ```
    - Importe o arquivo `SistemaBiblioteca.sql` para criar as tabelas necessárias.
    - Talvez seja necessario configurar o login e senha do MySQL no `ConnectionSQL.java` em \utils

## Execução do Projeto

Para executar o projeto, execute o seguinte comando no diretório raiz do projeto:
```bash
mvn exec:java -Dexec.mainClass="com.edaa.sistemabiblioteca.Main"
```
Ou execute o *Main.java*

## Integrantes: 

**Professora Rafaela:**

- Alan Junio Vila Nova Pereira - 42025255
- Hugo Henrique Rodrigues de Almeida - 422140627
- João Victor Alves - 422142090
- Tiago de Britto Antunes - 
- Wallace Neres Pereira de Souza - 422220377


**Professor Daniel:**

- Gabriel Silva Galdino - 42124664
- Lucas Marques da Silva - 42125351
- César Augusto Ferreira Martins
