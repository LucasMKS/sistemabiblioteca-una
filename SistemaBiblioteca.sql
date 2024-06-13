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

INSERT INTO usuarios (login, senha, tipo, nome) VALUES ('adm', 'adm', 'administrador', 'Teste administrador');
SELECT * FROM usuarios;

# ------------------------------------------------------------------------ #

CREATE TABLE livros (
    id_livro INT NOT NULL AUTO_INCREMENT,
    titulo varchar(150) NOT NULL,
    autor varchar(150) NOT NULL,
    isbn BIGINT NOT NULL,
    categoria varchar(50) NOT NULL,
    quantidade int DEFAULT '0',
	PRIMARY KEY (`id_livro`),
	KEY `idx_isbn` (`isbn`)
);

INSERT INTO livros (titulo, autor, isbn, categoria) VALUES ('Dom Casmurro', 'Machado de Assis', 978871568, 'Literatura Brasileira');
SELECT * FROM livros;
# ------------------------------------------------------------------------ #

CREATE TABLE emprestimos (
  id int NOT NULL AUTO_INCREMENT,
  aluno_id int DEFAULT NULL,
  isbn bigint DEFAULT NULL,
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
