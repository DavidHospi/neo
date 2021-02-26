package org.acme.datasource.entity;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity
public class Departements {

    @Id
    public Integer id;
    @Property
    public Integer code;
    @Property
    public String nom;

}
