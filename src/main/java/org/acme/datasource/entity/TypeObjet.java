package org.acme.datasource.entity;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class TypeObjet {
    
    @Id
    public String nom;
}
