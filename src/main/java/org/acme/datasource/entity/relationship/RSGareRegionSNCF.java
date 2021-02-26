package org.acme.datasource.entity.relationship;

import org.acme.datasource.entity.Gare;
import org.acme.datasource.entity.RegionSNCF;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity("A_POUR_REGION_SNCF")
public class RSGareRegionSNCF {
    @StartNode
    private Gare gare;
    @EndNode
    public RegionSNCF regionSNCF;

    private RSGareRegionSNCF() {
    }
    public RSGareRegionSNCF(RegionSNCF regionSNCF) {
        this.regionSNCF = regionSNCF;
    }
}
