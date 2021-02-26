package org.acme.datasource.entity.relationship;

import org.acme.datasource.entity.Gare;
import org.acme.datasource.entity.RevisionGenerale;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity("A_POUR_RG")
public class RSGareRG {

    @StartNode
    private Gare gare;
    @EndNode
    public RevisionGenerale revisionGenerale;

    private RSGareRG() {
    }
    public RSGareRG(RevisionGenerale revisionGenerale) {
        this.revisionGenerale = revisionGenerale;
    }

}
