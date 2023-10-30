create table MotivoRecoleta(
    MotivoRecoletaId bigint not null auto_increment,
    MotivoRecoletaNome varchar(50) not null,
    MotivoRecoletaDescricao text,

    primary key(MotivoRecoletaId)
);

insert into MotivoRecoleta(MotivoRecoletaNome) values('Amostra coagulada');
insert into MotivoRecoleta(MotivoRecoletaNome, MotivoRecoletaDescricao) values('Amostra com fibrina' ,'A fibrina ocorre devido ao processo incompleto da coagulação do sangue. Os filetes de fibrina presentes na amostra, são praticamente imperceptíveis visualmente, mas ela interfere na execução do exame. Para evitá-la deve-se seguir alguns cuidados como: Respeitar a proporção anticoagulante e material; Seguir o tempo de retração do coágulo - normalmente 30 minutos; Promover a homogeneização suave por inversão e promover a centrifugação de forma adequada para cada tipo de amostra.');
insert into MotivoRecoleta(MotivoRecoletaNome) values('Amostra acidentada');
