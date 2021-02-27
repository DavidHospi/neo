package org.acme.datasource.entity.relationship;

import org.acme.datasource.entity.Gare;
import org.acme.datasource.entity.Objet;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity("TROUVE_A_GARE")
public class RSObjetGare {

    @StartNode
    private Objet objet;

    @EndNode
    public Gare gare;

    private RSObjetGare() {
    }
    public RSObjetGare(Gare gare) {
        this.gare = gare;
    }
}
