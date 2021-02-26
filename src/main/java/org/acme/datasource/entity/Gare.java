package org.acme.datasource.entity;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;


import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@NodeEntity
public class Gare {

    @Id
    public Integer id;

    @Relationship("A_POUR_RG")
    public Set<RevisionGenerale> revisionGenerales = new HashSet<>();

    @Relationship("A_POUR_DG")
    public Set<DirectionGenerale> directionGenerales = new HashSet<>();

    @Relationship("A_POUR_DRG")
    public Set<SegmentDRG> segmentDRGS = new HashSet<>();

    @Relationship("A_POUR_DPT")
    public Set<Departements> departements = new HashSet<>();

    @Relationship("SE_SITUE_A")
    public Set<Commune> communes = new HashSet<>();

    @Relationship("A_POUR_REGION_SNCF")
    public Set<RegionSNCF> regionSNCFS = new HashSet<>();

    @Property
    public String codePlateforme;
    @Property
    public String codeUIC;
    @Property
    public String intitulePlateforme;
    @Property
    public Float longitude;
    @Property
    public Float latitude;
    @Property
    public Map<String, Float> geoPoint;
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
    public String nbPlateformes;
    @Property
    public String uniteGare;


}
