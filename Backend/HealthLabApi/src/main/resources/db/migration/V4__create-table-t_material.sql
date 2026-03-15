CREATE TABLE T_MATERIAL(
    MAT_CODIGO BIGINT NOT NULL IDENTITY(1,1),
    MAT_NOME VARCHAR(50) NOT NULL,
    MAT_DESCRICAO text,

    PRIMARY KEY (MAT_CODIGO)
);

INSERT INTO T_MATERIAL(MAT_NOME, MAT_DESCRICAO) VALUES 
('Soro', 'Plasma sem fibrinogênio, liberado após a coagulação do sangue, enquanto o plasma é preparado para coagulação do sangue, e com função no sistema imunológico'),
('Urina', 'Urina'),
('Sangue Total', ' É o sangue doado sem nenhuma modificação, que será processado nos hemocomponentes descritos abaixo.
Praticamente em desuso. Existem poucas indicações de transfusão de sangue total. A quantidade de fatores de coagulação
não é suficiente e as plaquetas não estão mais viáveis. O concentrado de hemácias supre de forma mais eficaz que o
sangue total, a reposição de eritrócitos, com a vantagem de ser infundido menor volume.');
