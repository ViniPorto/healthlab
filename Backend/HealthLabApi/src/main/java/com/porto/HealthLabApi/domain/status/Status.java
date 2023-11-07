package com.porto.HealthLabApi.domain.status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Status")
@Entity(name = "Status")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "codigo")
public class Status {
    
    @Id
    @Column(name = "StatusCodigo")
    private String codigo;
    @Column(name = "StatusNome")
    private String nome;
    @Column(name = "StatusCor")
    @Enumerated(EnumType.STRING)
    private StatusCor status;

}
