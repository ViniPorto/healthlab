create table Setor(
    SetorId bigint not null auto_increment,
    SetorNome varchar(50) not null,
    SetorDescricao text,

    primary key(SetorId)
);

insert into Setor(SetorNome) values ('Bioquimica');
insert into Setor(SetorNome) values ('Urinalise');
insert into Setor(SetorNome) values ('Hematologia');