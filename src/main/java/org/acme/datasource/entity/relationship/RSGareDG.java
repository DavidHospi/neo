package org.acme.datasource.entity.relationship;

import org.acme.datasource.entity.DirectionGeneral;
import org.acme.datasource.entity.Gare;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity("A_POUR_DG")
public class RSGareDG {
    @StartNode
    private Gare gare;
    @EndNode
    public DirectionGeneral directionGeneral;
    private RSGareDG() {}
    public RSGareDG(DirectionGeneral directionGeneral) {
        this.directionGeneral = directionGeneral;
    }
}
