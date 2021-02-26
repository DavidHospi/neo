package org.acme.datasource.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity
public class Commune {
    @Property
    public Integer code;
    @Property
    public Integer cp;
    @Id
    public String nom;
}
