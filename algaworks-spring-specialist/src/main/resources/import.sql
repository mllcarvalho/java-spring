insert into estado (nome) values ('Acre');
insert into estado (nome) values ('São Paulo');

insert into cidade (nome, estado_id) values ('Rio Branco', 1);
insert into cidade (nome, estado_id) values ('São Paulo', 2);
insert into cidade (nome, estado_id) values ('Campinas', 2);

insert into cozinha (nome) values ('Brasileira');
insert into cozinha (nome) values ('Japonesa');
insert into cozinha (nome) values ('Italiana');

insert into restaurante (nome, taxa_frete, cozinha_id) values ('Restaurante 1', 10, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Restaurante 2', 20, 2);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Restaurante 3', 30, 1);

insert into forma_pagamento (descricao) values ('Dinheiro');
insert into forma_pagamento (descricao) values ('Cartão de crédito');
insert into forma_pagamento (descricao) values ('Cartão de débito');

insert into permissao (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');
insert into permissao (nome, descricao) values ('CADASTRAR_COZINHAS', 'Permite cadastrar cozinhas');