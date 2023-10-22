create table Material(
    MaterialId bigint not null auto_increment,
    MaterialNome varchar(50) not null,
    MaterialDescricao text,

    primary key(MaterialId)
);

insert into Material(MaterialNome, MaterialDescricao) values ('Soro', 'Plasma sem fibrinogênio, liberado após a coagulação do sangue, enquanto o plasma é preparado para coagulação do sangue, e com função no sistema imunológico');
insert into Material(MaterialNome, MaterialDescricao) values ('Urina', 'Urina');
insert into Material(MaterialNome, MaterialDescricao) values ('Sangue Total', ' É o sangue doado sem nenhuma modificação, que será processado nos hemocomponentes descritos abaixo.
Praticamente em desuso. Existem poucas indicações de transfusão de sangue total. A quantidade de fatores de coagulação
não é suficiente e as plaquetas não estão mais viáveis. O concentrado de hemácias supre de forma mais eficaz que o
sangue total, a reposição de eritrócitos, com a vantagem de ser infundido menor volume.');
