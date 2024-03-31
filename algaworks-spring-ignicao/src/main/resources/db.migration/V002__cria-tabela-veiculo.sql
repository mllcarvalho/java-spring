create table veiculo(
    id int not null auto_increment,
    proprietario_id int not null,
    placa varchar(7) not null,
    marca varchar(50) not null,
    modelo varchar(50) not null,
    status varchar(20) not null,
    data_cadastro datetime not null,
    data_apreensao datetime,

    primary key (id)
);

alter table veiculo add constraint fk_veiculo_proprietario foreign key (proprietario_id) references proprietario (id);

alter table veiculo add constraint uk_veiculo_placa unique (placa);