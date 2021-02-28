package org.acme.datasource.entity.relationship;

import org.acme.datasource.entity.Departement;
import org.acme.datasource.entity.Gare;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity("A_POUR_DPT")
public class RSGareDepartements {

    @StartNode
    private Gare gare;
    @EndNode
    public Departement departement;
    private RSGareDepartements() {}
    public RSGareDepartements(Departement departement) {
        this.departement = departement;
    }
}
