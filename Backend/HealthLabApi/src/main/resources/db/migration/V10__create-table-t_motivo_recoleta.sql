CREATE TABLE T_MOTIVO_RECOLETA(
    MRE_CODIGO BIGINT NOT NULL IDENTITY(1,1),
    MRE_NOME VARCHAR(50) NOT NULL,
    MRE_DESCRICAO TEXT,

    PRIMARY KEY (MRE_CODIGO)
);

INSERT INTO T_MOTIVO_RECOLETA(MRE_NOME, MRE_DESCRICAO) VALUES
('Amostra coagulada', NULL),
('Amostra com fibrina' ,'A fibrina ocorre devido ao processo incompleto da coagulação do sangue. Os filetes de fibrina presentes na amostra, são praticamente imperceptíveis visualmente, mas ela interfere na execução do exame. Para evitá-la deve-se seguir alguns cuidados como: Respeitar a proporção anticoagulante e material; Seguir o tempo de retração do coágulo - normalmente 30 minutos; Promover a homogeneização suave por inversão e promover a centrifugação de forma adequada para cada tipo de amostra.'),
('Amostra acidentada', NULL);
