create table Pessoa(
    PessoaId bigint not null auto_increment,
    PessoaNome varchar(50) not null,
    PessoaCPF varchar(11) not null unique,
    PessoaEmail varchar(50) not null,
    PessoaTelefone varchar(11) not null,
    PessoaDadosGerais varchar(300),
    PessoaDataNascimento date not null,
    PessoaObservacao varchar(300),

    primary key (PessoaId)
);

insert into Pessoa(PessoaNome, PessoaCPF, PessoaEmail, PessoaTelefone, PessoaDadosGerais, PessoaDataNascimento, PessoaObservacao) values('Teste da Silva', '24061982001', 'testedasilva@gmail.com', '49988365688', 'Sangue O+, Altura 175cm, 75KG', '2000-01-01', 'Observação do teste da silva');
insert into Pessoa(PessoaNome, PessoaCPF, PessoaEmail, PessoaTelefone, PessoaDataNascimento, PessoaObservacao) values('Joao Antonio', '51586830007', 'joaozinho@outlook.com', '49988365653', '2002-05-30', 'Observação do joao');
insert into Pessoa(PessoaNome, PessoaCPF, PessoaEmail, PessoaTelefone, PessoaDadosGerais, PessoaDataNascimento, PessoaObservacao) values('Marcos Almeida', '79212503004', 'marcos@gmail.com', '47988368934', 'Obesidade grau 1', '2000-01-01', 'Fumante');
