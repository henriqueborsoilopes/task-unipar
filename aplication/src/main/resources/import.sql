INSERT INTO USUARIO (nome, email, senha, is_logado) VALUES ('Henrique Lopes', 'henrique@example.com', 'senha123', FALSE);
INSERT INTO USUARIO (nome, email, senha, is_logado) VALUES ('Maria Silva', 'maria@example.com', 'senha123', FALSE);
INSERT INTO USUARIO (nome, email, senha, is_logado) VALUES ('João Santos', 'joao@example.com', 'senha123', FALSE);
INSERT INTO HABITOS (descricao, id_usuario) VALUES ('Exercícios Diários', 1);
INSERT INTO HABITOS (descricao, id_usuario) VALUES ('Leitura', 1);
INSERT INTO HABITOS (descricao, id_usuario) VALUES ('Meditação', 1);
INSERT INTO TAREFAS (descricao, data_inicio, data_limite, concluida, id_usuario) VALUES ('Finalizar projeto de trabalho', '2024-01-01T09:00:00Z', '2024-01-10T17:00:00Z', FALSE, 1);
INSERT INTO TAREFAS (descricao, data_inicio, data_limite, concluida, id_usuario) VALUES ('Exercitar-se 30 minutos', '2024-01-02 07:00:00', '2024-01-02 07:30:00', TRUE, 1);
INSERT INTO TAREFAS (descricao, data_inicio, data_limite, concluida, id_usuario) VALUES ('Ler 20 páginas de um livro', '2024-01-03 18:00:00', '2024-01-03 19:00:00', FALSE, 1);
INSERT INTO TAREFAS (descricao, data_inicio, data_limite, concluida, id_usuario) VALUES ('Meditar por 10 minutos', '2024-01-04 06:00:00', '2024-01-04 06:10:00', TRUE, 1);
INSERT INTO HABITO_HISTORICO (id_habito, id_usuario, data) VALUES (1, 1, '2024-01-01');
INSERT INTO HABITO_HISTORICO (id_habito, id_usuario, data) VALUES (2, 1, '2024-01-01');
INSERT INTO HABITO_HISTORICO (id_habito, id_usuario, data) VALUES (3, 1, '2024-01-02');
INSERT INTO HABITO_HISTORICO (id_habito, id_usuario, data) VALUES (1, 1, '2024-01-02');
INSERT INTO HABITO_HISTORICO (id_habito, id_usuario, data) VALUES (2, 1, '2024-01-02');
INSERT INTO HABITO_HISTORICO (id_habito, id_usuario, data) VALUES (3, 1, '2024-01-03');

