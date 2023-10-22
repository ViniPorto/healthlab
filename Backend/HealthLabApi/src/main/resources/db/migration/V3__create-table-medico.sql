create table Medico(
    MedicoId bigint not null auto_increment,
    MedicoEmail varchar(50),
    MedicoTelefone varchar(11),
    MedicoCRM varchar(6) not null,
    MedicoUF varchar(2) not null,
    MedicoNome varchar(50) not null,

    primary key (MedicoId)
);

insert into Medico (MedicoEmail, MedicoTelefone, MedicoCRM, MedicoUF, MedicoNome) values ('luciano.martin@gmail.com', '49988763423', '26862', 'SC', 'Luciano Martin');
insert into Medico (MedicoEmail, MedicoTelefone, MedicoCRM, MedicoUF, MedicoNome) values ('andersonsoares@outlook.com', '47988769223', '27632', 'SC', 'Anderson Soares');
insert into Medico (MedicoCRM, MedicoUF, MedicoNome) values ('18888', 'SC', 'Joaquim Souza');
