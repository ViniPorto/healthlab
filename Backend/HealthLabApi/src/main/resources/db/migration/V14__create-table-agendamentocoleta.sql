create table AgendamentoColeta(
    AgendamentoColetaId bigint not null auto_increment,
    AgendamentoColetaDataHoraColeta datetime,
    AgendamentoColetaPessoaId bigint not null,
    AgendamentoColetaRequisicaoId bigint not null,
    AgendamentoColetaUsuarioId bigint not null,

    primary key(AgendamentoColetaId),
    foreign key(AgendamentoColetaPessoaId) references Pessoa(PessoaId),
    foreign key(AgendamentoColetaRequisicaoId) references Requisicao(RequisicaoId),
    foreign key(AgendamentoColetaUsuarioId) references Usuario(UsuarioId)
);