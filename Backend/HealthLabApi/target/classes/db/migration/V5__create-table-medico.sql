create table Medico(
    MedicoId bigint not null auto_increment,
    MedicoEmail varchar(50),
    MedicoTelefone varchar(11),
    MedicoCRM varchar(6) not null,
    MedicoUF varchar(2) not null,
    MedicoNome varchar(50) not null,

    primary key (MedicoId)
);