package org.acme.datasource.entity;

import org.acme.datasource.entity.relationship.*;
import org.neo4j.driver.types.Point;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.types.spatial.CartesianPoint2d;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Gare {

    @Relationship("A_POUR_RG")
    public Set<RSGareRG> revisionGenerales = new HashSet<>();

    @Relationship("A_POUR_DG")
    public Set<RSGareDG> directionGenerales = new HashSet<>();

    @Relationship("A_POUR_DRG")
    public Set<RSGareDRG> segmentDRGS = new HashSet<>();

    @Relationship("A_POUR_DPT")
    public Set<RSGareDepartements> departements = new HashSet<>();

    @Relationship("SE_SITUE_A")
    public Set<RSGareCommune> communes = new HashSet<>();

    @Relationship("A_POUR_REGION_SNCF")
    public Set<RSGareRegionSNCF> regionSNCFS = new HashSet<>();

    @Id
    public String codeUIC;
    @Property
    public String codePlateforme;
    @Property
    public String intitulePlateforme;
    @Property
    public Float longitude;
    @Property
    public Float latitude;
    @Property(name="geoPoint")
    @Transient
    public Point geoPoint;
    @Property
    public String wgs84;
    @Property
    public String tvs;
    @Property
    public String intitule;
    @Property
    public String intituleFrontonGare;
    @Property
    public Integer niveauService;
    @Property
    public String ut;
    @Property
    public String nbPlatformes;
    @Property
    public String uniteGare;



}
