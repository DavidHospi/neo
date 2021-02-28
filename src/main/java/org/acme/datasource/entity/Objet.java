package org.acme.datasource.entity;


import org.acme.datasource.entity.relationship.RSObjetGare;
import org.acme.datasource.entity.relationship.RSObjetType;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Objet {

    @Relationship("TROUVE_A_GARE")
    public Set<RSObjetGare> gares = new HashSet<>();

    @Relationship("EST_DE_TYPE")
    public Set<RSObjetType> typeObjetSet = new HashSet<>();

    @Id
    public String natureObjet;
    @Property
    public String perduLe;
    @Property
    public String restitue;



}
