create table LayoutCampos(
    LayoutCamposId bigint not null auto_increment,
    LayoutCamposCodigoCampo smallint not null,
    LayoutCamposLayoutId bigint not null,
    LayoutCamposTipoCampo varchar(1) not null,
    LayoutCamposPosicao smallint not null,
    LayoutCamposAltura smallint not null,
    LayoutCamposLargura smallint not null,
    LayoutCamposTexto text,
    LayoutCamposFonteCor varchar(2) not null,
    LayoutCamposFonteTamanho tinyint not null,

    primary key(LayoutCamposId),

    foreign key(LayoutCamposLayoutId) references Layout(LayoutId)
);

insert into LayoutCampos(LayoutCamposLayoutId, LayoutCamposCodigoCampo, LayoutCamposTipoCampo, LayoutCamposPosicao, LayoutCamposAltura, LayoutCamposLargura, LayoutCamposTexto, LayoutCamposFonteCor, LayoutCamposFonteTamanho)
values (1, 1, 'T', 23, 1, 40, 'Hemograma simples', 'PT', 4);
insert into LayoutCampos(LayoutCamposLayoutId, LayoutCamposCodigoCampo, LayoutCamposTipoCampo, LayoutCamposPosicao, LayoutCamposAltura, LayoutCamposLargura, LayoutCamposTexto, LayoutCamposFonteCor, LayoutCamposFonteTamanho)
values (1, 2, 'T', 64, 1, 10, 'Contagem Plaquetas:', 'PT', 3);
insert into LayoutCampos(LayoutCamposLayoutId, LayoutCamposCodigoCampo, LayoutCamposTipoCampo, LayoutCamposPosicao, LayoutCamposAltura, LayoutCamposLargura, LayoutCamposFonteCor, LayoutCamposFonteTamanho)
values (1, 3, 'R', 75, 1, 10, 'PT', 3);
insert into LayoutCampos(LayoutCamposLayoutId, LayoutCamposCodigoCampo, LayoutCamposTipoCampo, LayoutCamposPosicao, LayoutCamposAltura, LayoutCamposLargura, LayoutCamposTexto, LayoutCamposFonteCor, LayoutCamposFonteTamanho)
values (1, 4, 'T', 85, 1, 10, 'Contagem Leucocitos:', 'PT', 3);
insert into LayoutCampos(LayoutCamposLayoutId, LayoutCamposCodigoCampo, LayoutCamposTipoCampo, LayoutCamposPosicao, LayoutCamposAltura, LayoutCamposLargura, LayoutCamposFonteCor, LayoutCamposFonteTamanho)
values (1, 5, 'R', 96, 1, 10, 'PT', 3);
insert into LayoutCampos(LayoutCamposLayoutId, LayoutCamposCodigoCampo, LayoutCamposTipoCampo, LayoutCamposPosicao, LayoutCamposAltura, LayoutCamposLargura, LayoutCamposTexto, LayoutCamposFonteCor, LayoutCamposFonteTamanho)
values (1, 6, 'T', 106, 1, 10, 'Contagem hemacias:', 'PT', 3);
insert into LayoutCampos(LayoutCamposLayoutId, LayoutCamposCodigoCampo, LayoutCamposTipoCampo, LayoutCamposPosicao, LayoutCamposAltura, LayoutCamposLargura, LayoutCamposFonteCor, LayoutCamposFonteTamanho)
values (1, 7, 'R', 117, 1, 10, 'PT', 3);


