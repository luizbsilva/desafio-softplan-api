INSERT INTO permissao (codigo, descricao) values (9, 'ROLE_CADASTRAR_USUARIO');
INSERT INTO permissao (codigo, descricao) values (10, 'ROLE_REMOVER_USUARIO');
INSERT INTO permissao (codigo, descricao) values (11, 'ROLE_PESQUISAR_USUARIO');

-- admin
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 9);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 10);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 11);
-- maria
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2, 11);