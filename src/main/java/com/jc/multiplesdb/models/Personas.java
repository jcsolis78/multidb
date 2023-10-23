package com.jc.multiplesdb.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tpl_personas", schema = "gestion_humana")


@Getter
@Setter

public class Personas {

    @Id
    private Long id;

    private String nombre;
    private String apellido;

    private String numeroIdentificacion;


}
