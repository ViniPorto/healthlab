create table Pessoa(
    UsuarioId bigint not null auto_increment,
    PessoaNome varchar(50) not null,
    PessoaCPF varchar(11) not null unique,
    PessoaEmail varchar(50) not null,
    PessoaTelefone varchar(11) not null,
    PessoaDadosGerais varchar(300),
    PessoaDataNascimento date not null,
    PessoaObservacao varchar(300),

    primary key (UsuarioId)
);