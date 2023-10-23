create table Exame(
    ExameId bigint not null auto_increment,
    ExameSetorId bigint not null,
    ExameLayoutId bigint not null,
    ExamePrincipal tinyint not null,
    ExameTitulo varchar(50) not null,
    ExameSigla varchar(5) not null unique,
    ExameTempoExecucaoUrgente smallint not null,
    ExameMetodoId bigint not null,
    ExameMaterialId bigint not null,
    ExameDescricao text,
    ExamePreco decimal(7,2) not null,
    ExameTempoExecucaoNormal smallint not null,

    primary key(ExameId),
    foreign key(ExameSetorId) references Setor(SetorId),
    foreign key(ExameLayoutId) references Layout(LayoutId),
    foreign key(ExameMetodoId) references Metodo(MetodoId),
    foreign key(ExameMaterialId) references Material(MaterialId)
);

insert into Exame(ExameSetorId, ExameLayoutId, ExamePrincipal, ExameTitulo, ExameSigla, ExameTempoExecucaoUrgente, ExameMetodoId, ExameMaterialId, ExameDescricao, ExamePreco, ExameTempoExecucaoNormal)
values(3, 1, 1, 'Hemograma', 'HEM', 15, 1, 3, 'INTERPRETAÇÃO
O hemograma corresponde a um conjunto de testes laboratoriais que estabelece os aspectos quantitativos e qualitativos dos eritrócitos (eritrograma), dos leucócitos (leucograma) e das plaquetas (plaquetograma). O eritrograma inclui os testes laboratoriais que determinam o perfil hematológico da série vermelha no sangue periférico. É constituído por contagem de eritrócitos, dosagem de hemoglobina, hematócrito, índices hematimétricos e avaliação da morfologia eritrocitária. O leucograma engloba os testes laboratoriais que determinam o perfil hematológico da série branca no sangue periférico onde é feito a contagem global e diferencial de leucócitos juntamente com a análise das alterações morfológicas no sangue. Já o plaquetograma envolve a contagem de plaquetas, avaliação de sua morfologia feita por microscopia e as determinações do volume plaquetário médio e da variação entre seus volumes. O hemograma auxilia na elucidação do estado geral da saúde como, por exemplo, casos de anemias, inflamações, infecções, hematomas, hemorragias, leucemias, síndromes (doenças hereditárias) bem como, acompanhamento em tratamentos. ', 30, 60);
