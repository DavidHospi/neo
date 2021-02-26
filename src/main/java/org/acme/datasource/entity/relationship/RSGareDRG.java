package org.acme.datasource.entity.relationship;


import org.acme.datasource.entity.Gare;
import org.acme.datasource.entity.SegmentDRG;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity("A_POUR_DRG")
public class RSGareDRG {

    @StartNode
    private Gare gare;
    @EndNode
    public SegmentDRG segmentDRG;

    //@Property
    //public String exempleProperty

    private RSGareDRG() {

    }
    public RSGareDRG(SegmentDRG segmentDRG) {
        this.segmentDRG = segmentDRG;
        //Set other properties
    }
}
