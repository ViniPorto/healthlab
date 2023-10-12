create table usuario(
    UsuarioId bigint not null auto_increment,
    UsuarioLogin varchar(20) not null unique,
    UsuarioSenha varchar(255) not null,
    UsuarioAtivo tinyint not null,
    UsuarioAdministrador tinyint not null,
    UsuarioNome varchar(50) not null,

    primary key(UsuarioId)
);