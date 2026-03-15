CREATE TABLE T_EXAME(
    EXA_CODIGO BIGINT NOT NULL IDENTITY(1,1),
    SET_CODIGO BIGINT NOT NULL,
    LAY_CODIGO BIGINT NOT NULL,
    EXA_PRINCIPAL TINYINT NOT NULL,
    EXA_TITULO VARCHAR(50) NOT NULL,
    EXA_SIGLA VARCHAR(5) NOT NULL UNIQUE, --Para cadastrar requisições, ao invés de digitar código ou outra informação, informa a sigla
    EXA_TEMPO_EXECUCAO_URGENTE SMALLINT NOT NULL,
    MET_CODIGO BIGINT NOT NULL,
    MAT_CODIGO BIGINT NOT NULL,
    EXA_DESCRICAO TEXT,
    EXA_PRECO DECIMAL(7,2) NOT NULL,
    EXA_TEMPO_EXECUCAO_NORMAL SMALLINT NOT NULL,

    PRIMARY KEY (EXA_CODIGO),
    FOREIGN KEY(SET_CODIGO) REFERENCES T_SETOR(SET_CODIGO),
    FOREIGN KEY(LAY_CODIGO) REFERENCES Layout(LAY_CODIGO),
    FOREIGN KEY(MET_CODIGO) REFERENCES T_METODO(MET_CODIGO),
    FOREIGN KEY(MAT_CODIGO) REFERENCES T_MATERIAL(MAT_CODIGO)
);

INSERT INTO T_EXAME(SET_CODIGO, LAY_CODIGO, EXA_PRINCIPAL, EXA_TITULO, EXA_SIGLA, EXA_TEMPO_EXECUCAO_URGENTE, MET_CODIGO, MAT_CODIGO, EXA_DESCRICAO, EXA_PRECO, EXA_TEMPO_EXECUCAO_NORMAL) VALUES 
(3, 1, 1, 'Hemograma', 'HEM', 15, 1, 3, 'O hemograma corresponde a um conjunto de testes laboratoriais que estabelece os aspectos quantitativos e qualitativos dos eritrócitos (eritrograma), dos leucócitos (leucograma) e das plaquetas (plaquetograma). O eritrograma inclui os testes laboratoriais que determinam o perfil hematológico da série vermelha no sangue periférico. É constituído por contagem de eritrócitos, dosagem de hemoglobina, hematócrito, índices hematimétricos e avaliação da morfologia eritrocitária. O leucograma engloba os testes laboratoriais que determinam o perfil hematológico da série branca no sangue periférico onde é feito a contagem global e diferencial de leucócitos juntamente com a análise das alterações morfológicas no sangue. Já o plaquetograma envolve a contagem de plaquetas, avaliação de sua morfologia feita por microscopia e as determinações do volume plaquetário médio e da variação entre seus volumes. O hemograma auxilia na elucidação do estado geral da saúde como, por exemplo, casos de anemias, inflamações, infecções, hematomas, hemorragias, leucemias, síndromes (doenças hereditárias) bem como, acompanhamento em tratamentos.', 30, 60);
