INSERT INTO usuario (id,nome,senha,email,criado_em,ultimo_acesso) VALUES (1,'Luigi','$2a$10$2Luo6Kp6F6EuAF1MoNg.9uSbu6vGZhGeI1qJfDlRP.BvpGWR6i68e','luigitosin@hotmail.com',NOW(),null);

INSERT INTO perfil (id_usuario,id_perfil) VALUES (1,1);
INSERT INTO perfil (id_usuario,id_perfil) VALUES (1,2);

INSERT INTO grana (id,nome,id_usuario,criado_em,modificado_em,codigo_de_acesso) VALUES (1,'Grana 1',1,NOW(),NOW(),'7b487d398bfe1ff73eff10ca1a335f26');

INSERT INTO gasto (id,id_grana,tipo,valor,data) VALUES (1,1,'Aluguel',1000.00,NOW());
INSERT INTO gasto (id,id_grana,tipo,valor,data) VALUES (2,1,'Condominio',600.00,NOW());
INSERT INTO gasto (id,id_grana,tipo,valor,data) VALUES (3,1,'Luz',100.00,NOW());
INSERT INTO gasto (id,id_grana,tipo,valor,data) VALUES (4,1,'Internet',200.00,NOW());
INSERT INTO gasto (id,id_grana,tipo,valor,data) VALUES (5,1,'Diarista',200.00,NOW());

INSERT INTO pessoa (id,nome,usuario_criacao,email) VALUES (1,'Luigi1',1,'luigitosin@gmail.com');
INSERT INTO pessoa (id,nome,usuario_criacao,email) VALUES (2,'Luigi2',1,'luigitosin@gmail.com');

INSERT INTO gasto_pessoa (id_gasto,id_pessoa,valor,percentual,id) VALUES (1,1,500.00,0.5,1);
INSERT INTO gasto_pessoa (id_gasto,id_pessoa,valor,percentual,id) VALUES (1,2,500.00,0.5,2);

INSERT INTO gasto_pessoa (id_gasto,id_pessoa,valor,percentual,id) VALUES (2,1,300.00,0.5,3);
INSERT INTO gasto_pessoa (id_gasto,id_pessoa,valor,percentual,id) VALUES (2,2,300.00,0.5,4);

INSERT INTO gasto_pessoa (id_gasto,id_pessoa,valor,percentual,id) VALUES (3,1,40.00,0.4,5);
INSERT INTO gasto_pessoa (id_gasto,id_pessoa,valor,percentual,id) VALUES (3,2,60.00,0.6,6);

INSERT INTO gasto_pessoa (id_gasto,id_pessoa,valor,percentual,id) VALUES (4,1,200.00,1,7);

INSERT INTO gasto_pessoa (id_gasto,id_pessoa,valor,percentual,id) VALUES (5,2,200.00,1,8);

INSERT INTO gasto_pessoa (id_gasto,id_pessoa,valor,percentual,id) VALUES (4,2,0,0,9);
INSERT INTO gasto_pessoa (id_gasto,id_pessoa,valor,percentual,id) VALUES (5,1,0,0,10);