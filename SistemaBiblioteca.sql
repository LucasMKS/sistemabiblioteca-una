CREATE DATABASE SistemaBiblioteca;
USE SistemaBiblioteca;

# ------------------------------------------------------------------------ #

CREATE TABLE usuarios (
    id_usuarios INT AUTO_INCREMENT,
    login varchar(55) NOT NULL,
    senha varchar(55) NOT NULL,
    tipo varchar(30) NOT NULL,
    nome varchar(150) NOT NULL,
    ra INT DEFAULT NULL,
	PRIMARY KEY (`id_usuarios`)
);

SELECT * FROM usuarios;

# ------------------------------------------------------------------------ #

CREATE TABLE livros (
    id_livro INT NOT NULL AUTO_INCREMENT,
    titulo varchar(150) NOT NULL,
    autor varchar(150) NOT NULL,
    isbn INT NOT NULL,
    categoria varchar(50) NOT NULL,
    quantidade int DEFAULT '0',
	PRIMARY KEY (`id_livro`),
	KEY `idx_isbn` (`isbn`)
);

SELECT * FROM livros;

# ------------------------------------------------------------------------ #

CREATE TABLE emprestimos (
  id int NOT NULL AUTO_INCREMENT,
  aluno_id int DEFAULT NULL,
  isbn int DEFAULT NULL,
  data_emprestimo timestamp NULL DEFAULT NULL,
  data_devolucao timestamp NULL DEFAULT NULL,
  status boolean default true,
  PRIMARY KEY (id),
  KEY fk_usuarios (aluno_id),
  KEY fk_livros (isbn),
  CONSTRAINT fk_livros FOREIGN KEY (isbn) REFERENCES livros (isbn),
  CONSTRAINT fk_usuarios FOREIGN KEY (aluno_id) REFERENCES usuarios (id_usuarios)
);

SELECT * FROM emprestimos;

# ----------------------------------------- Select - Insert - Update  ----------------------------------------- #
SELECT * FROM usuarios;

INSERT INTO usuarios (login, senha, tipo, nome) VALUES ('adm', 'adm', 'administrador', 'Teste administrador');

UPDATE usuarios SET nome = 'José Luiz' WHERE id_usuarios = 1;
# ------------------------------------------------------------------------------------------------------------- #
SELECT * FROM livros;

INSERT INTO livros (titulo, autor, isbn, categoria) VALUES ('Dom Casmurro', 'Machado de Assis', 978871568, 'Literatura Brasileira');

UPDATE livros SET quantidade = 5 WHERE id_livro = 1;
# ------------------------------------------------------------------------------------------------------------- #
SELECT * FROM emprestimos;

UPDATE emprestimos SET status = 0 WHERE aluno_id = 1;

# ------------------------------------------------------------------------------------------------------------- #
# DROP TABLE usuarios

RENAME TABLE usuarios TO usuariosOLD;
