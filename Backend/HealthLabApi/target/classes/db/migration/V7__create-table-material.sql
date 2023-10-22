create table Material(
    MaterialId bigint not null auto_increment,
    MaterialNome varchar(50) not null,
    MaterialDescricao text,

    primary key(MaterialId)
);