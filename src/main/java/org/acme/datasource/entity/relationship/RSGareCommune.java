package org.acme.datasource.entity.relationship;

import org.acme.datasource.entity.Commune;
import org.acme.datasource.entity.Gare;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity("SE_SITUE_A")
public class RSGareCommune {
    @StartNode
    private Gare gare;
    @EndNode
    public Commune commune;
    private RSGareCommune() {}
    public RSGareCommune(Commune commune) {
        this.commune = commune;
    }
}
