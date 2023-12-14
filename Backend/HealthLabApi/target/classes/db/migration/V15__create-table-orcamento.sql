create table Orcamento(
    OrcamentoId bigint not null auto_increment,
    OrcamentoUsuarioId bigint not null,
    OrcamentoPessoaId bigint not null,
    OrcamentoMedicoId bigint,
    OrcamentoData date not null,
    OrcamentoPrecoTotal decimal(7, 2) not null,

    primary key(OrcamentoId),
    foreign key(OrcamentoUsuarioId) references Usuario(UsuarioId),
    foreign key(OrcamentoPessoaId) references Pessoa(PessoaId),
    foreign key(OrcamentoMedicoId) references Medico(MedicoId)
);

create table OrcamentoExame(
    OrcamentoExameId bigint not null auto_increment,
    OrcamentoExameOrcamentoId bigint not null,
    OrcamentoExameExameId bigint not null,

    primary key(OrcamentoExameId),
    foreign key(OrcamentoExameOrcamentoId) references Orcamento(OrcamentoId),
    foreign key(OrcamentoExameExameId) references Exame(ExameId)
);