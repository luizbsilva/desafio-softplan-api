INSERT INTO permissao (codigo, descricao) values (9, 'ROLE_CADASTRAR_USUARIO');
INSERT INTO permissao (codigo, descricao) values (10, 'ROLE_REMOVER_USUARIO');
INSERT INTO permissao (codigo, descricao) values (11, 'ROLE_PESQUISAR_USUARIO');

INSERT INTO permissao (codigo, descricao) values (12, 'ROLE_CADASTRAR_PERMISSAO');
INSERT INTO permissao (codigo, descricao) values (13, 'ROLE_REMOVER_PERMISSAO');
INSERT INTO permissao (codigo, descricao) values (14, 'ROLE_PESQUISAR_PERMISSAO');

-- admin
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 9);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 10);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 11);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 12);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 13);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 14);
-- maria
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 11);