INSERT INTO categoria (nome) VALUES ('Eletrônicos');
INSERT INTO categoria (nome) VALUES ('Livros');

INSERT INTO usuario (username, password, roles) VALUES ('admin', '$2a$10$6ioCHUACtilwIUW.H7Bis.Hbgx4/rSuE8niOMHTgnj71Lff0w4Bpm', 'ROLE_ADMIN,ROLE_USER');
INSERT INTO usuario (username, password, roles) VALUES ('user', '$2a$10$eAkeXtVJy/SJylI5PuNdx.N3C0qj4jRtI08WEYCQbKbNqMexXlxuG', 'ROLE_USER');