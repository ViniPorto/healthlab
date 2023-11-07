create table Status(
    StatusCodigo varchar(2) not null,
    StatusNome varchar(50) not null,
    StatusCor varchar(20) not null,

    primary key(StatusCodigo)
);

insert into Status(StatusCodigo, StatusNome, StatusCor) values('CD', 'EXAME CADASTRADO', 'LARANJA');
insert into Status(StatusCodigo, StatusNome, StatusCor) values('MC', 'MATERIAL COLETADO', 'AZULESCURO');
insert into Status(StatusCodigo, StatusNome, StatusCor) values('MT', 'MATERIAL TRIADO', 'AMARELO');
insert into Status(StatusCodigo, StatusNome, StatusCor) values('RI', 'RESULTADO INFORMADO', 'ROSA');
insert into Status(StatusCodigo, StatusNome, StatusCor) values('RL', 'RESULTADO LIBERADO', 'VERDE');
insert into Status(StatusCodigo, StatusNome, StatusCor) values('SR', 'SOLICITADO RECOLETA', 'ROXO');
insert into Status(StatusCodigo, StatusNome, StatusCor) values('CA', 'CANCELADO', 'VERMELHO');
