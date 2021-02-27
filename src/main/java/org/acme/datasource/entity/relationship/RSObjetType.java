package org.acme.datasource.entity.relationship;

import org.acme.datasource.entity.Objet;
import org.acme.datasource.entity.TypeObjet;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity("EST_DE_TYPE")
public class RSObjetType {

    @StartNode
    private Objet objet;

    @EndNode
    public TypeObjet typeObjet;

    private RSObjetType() {
    }
    public RSObjetType(TypeObjet typeObjet) {
        this.typeObjet = typeObjet;
    }
}
