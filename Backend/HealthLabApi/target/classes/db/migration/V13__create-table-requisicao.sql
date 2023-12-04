create table Requisicao(
    RequisicaoId bigint not null auto_increment,
    RequisicaoMedicoId bigint,
    RequisicaoData datetime not null,
    RequisicaoUrgente tinyint not null,
    RequisicaoPessoaId bigint not null,
    RequisicaoUsuarioId bigint not null,
    RequisicaoPaga tinyint not null,
    RequisicaoPrecoTotal decimal(7,2) not null,

    primary key(RequisicaoId),
    foreign key(RequisicaoMedicoId) references Medico(MedicoId),
    foreign key(RequisicaoPessoaId) references Pessoa(PessoaId),
    foreign key(RequisicaoUsuarioId) references Usuario(UsuarioId)
);
create table RequisicaoExame(
    RequisicaoExameId bigint not null auto_increment,
    RequisicaoExameLayoutVigenteId bigint not null,
    RequisicaoExameDataHoraColeta datetime,
    RequisicaoExameMotivoRecoletaId bigint,
    RequisicaoExameImpresso tinyint not null,
    RequisicaoExameBioquimicoId bigint,
    RequisicaoExameBioquimicoAssinatura longtext,
    RequisicaoExameRequisicaoId bigint not null,
    RequisicaoExameExameId bigint not null,
    RequisicaoExameStatusCodigo varchar(2) not null,
    RequisicaoExameDataHoraInclusao datetime not null,
    RequisicaoExameDataHoraTriagem datetime,
    
    primary key(RequisicaoExameId),
    foreign key(RequisicaoExameLayoutVigenteId) references Layout(LayoutId),
    foreign key(RequisicaoExameMotivoRecoletaId) references MotivoRecoleta(MotivoRecoletaId),
    foreign key(RequisicaoExameBioquimicoId) references Bioquimico(BioquimicoId),
    foreign key(RequisicaoExameRequisicaoId) references Requisicao(RequisicaoId),
    foreign key(RequisicaoExameExameId) references Exame(ExameId),
    foreign key(RequisicaoExameStatusCodigo) references Status(StatusCodigo)
);
create table RequisicaoExameItensResultado(
    RequisicaoExameItensResultadoId bigint not null auto_increment,
    RequisicaoExameItensResultadoRequisicaoExameId bigint not null,
    RequisicaoExameItensResultadoResultado varchar(100),
    RequisicaoExameItensResultadoCodigoCampo bigint not null,

    primary key(RequisicaoExameItensResultadoId),
    foreign key(RequisicaoExameItensResultadoRequisicaoExameId) references RequisicaoExame(RequisicaoExameId)
);