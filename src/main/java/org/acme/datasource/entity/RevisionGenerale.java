package org.acme.datasource.entity;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity
public class RevisionGenerale {

    @Id
    public Integer id;
    @Property
    public String nom;
}
