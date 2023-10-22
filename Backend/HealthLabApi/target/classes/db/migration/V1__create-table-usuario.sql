create table Usuario(
    UsuarioId bigint not null auto_increment,
    UsuarioLogin varchar(20) not null unique,
    UsuarioSenha varchar(255) not null,
    UsuarioAtivo tinyint not null,
    UsuarioAdministrador tinyint not null,
    UsuarioNome varchar(50) not null,

    primary key(UsuarioId)
);

insert into Usuario(UsuarioLogin, UsuarioSenha, UsuarioAtivo, UsuarioAdministrador, UsuarioNome) values ('admin', '$2a$12$KQSM9izb33mPNP6HbUUiduE/JuxxVExwhBaPXdRHcoDUddgyH2EVS', 1, 1, 'administrador');
