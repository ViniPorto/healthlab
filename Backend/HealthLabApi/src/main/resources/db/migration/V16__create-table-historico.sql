create table Historico(
    HistoricoId bigint not null auto_increment,
    HistoricoReferenciaId bigint not null,
    HistoricoTabela varchar(50) not null,
    HistoricoUsuarioId bigint not null,
    HistoricoAcaoRealizada varchar(20) not null,
    HistoricoAcaoDataHora datetime not null,
    HistoricoDadosAtualizados text not null,

    primary key (HistoricoId),
    foreign key(HistoricoUsuarioId) references Usuario(UsuarioId)
);