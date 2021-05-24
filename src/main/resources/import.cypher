MATCH(n)
DETACH DELETE(n);

CREATE CONSTRAINT contrainte_gare_uic IF NOT EXISTS ON (g: Gare) ASSERT g.codeUIC is UNIQUE;
CREATE CONSTRAINT contrainte_gare_palteforme IF NOT EXISTS ON (g: Gare) ASSERT g.codePlateforme is UNIQUE;

CREATE CONSTRAINT contrainte_segment_drg IF NOT EXISTS ON (sdrg: SegmentDRG) ASSERT sdrg.code is UNIQUE;

CREATE CONSTRAINT contrainte_code_departement IF NOT EXISTS ON (d: Departement) ASSERT d.code is UNIQUE;
CREATE CONSTRAINT contrainte_code_departement IF NOT EXISTS ON (d: Departement) ASSERT d.nom is UNIQUE;

CREATE CONSTRAINT contrainte_code_postal IF NOT EXISTS ON (c: Commune) ASSERT c.cp is UNIQUE;

CREATE CONSTRAINT contrainte_rg IF NOT EXISTS ON (rg: RevisionGenerale) ASSERT rg.nom is UNIQUE;

CREATE CONSTRAINT contrainte_dg IF NOT EXISTS ON (dtg: DirectionGeneral) ASSERT dtg.nom is UNIQUE;

CREATE CONSTRAINT contrainte_region_sncf IF NOT EXISTS ON (rsncf: RegionSNCF) ASSERT rsncf.nom is UNIQUE;

:auto USING PERIODIC COMMIT 500
LOAD CSV WITH HEADERS FROM 'file:///referentiel-gares-voyageurs.csv' AS gare FIELDTERMINATOR ';'
MERGE (g: Gare {
codePlateforme: gare.`Code plate-forme`,
codeUIC: gare.`Code UIC`
})
set g += {

intitulePlateforme: gare.`Intitulé plateforme`,
longitude: toFloat(gare.Longitude),
latitude: toFloat(gare.Latitude),
geoPoint: point({
latitude: g.longitude,
longitude: g.latitude
}),
wgs84: gare.`WGS 84`,

tvs: coalesce(gare.TVS, "ND"),
niveauService: toInteger(coalesce(gare.`Niveau de service`, '0')),

intitule: gare.`Intitulé gare`,
intituleFrontonGare: gare.`Intitulé fronton de gare`,

ut: coalesce(gare.UT, "nd"),
nbPlatformes: gare.`Nbre plateformes`,
uniteGare: coalesce(gare.`Unité gare`, "nd")
}

MERGE(sdrg: SegmentDRG {
code: toUpper(gare.`Segment DRG`)

})
MERGE (g)-[:A_POUR_DRG]->(sdrg)

MERGE(d: Departement {
code: gare.`Code département`,
nom: gare.Département
})
MERGE (g)-[:A_POUR_DPT]->(d)

MERGE(c: Commune {
cp: toInteger(gare.`Code postal`)
})
SET c += {
nom: gare.Commune,
code: toInteger(gare.`Code Commune`)
}
MERGE (g)-[:SE_SITUE_A]->(c)

MERGE(rg: RevisionGenerale {
nom: gare.RG
})
MERGE (g)-[:A_POUR_RG]->(rg)

MERGE(dtg: DirectionGeneral {
nom: gare.DTG
})
MERGE (g)-[:A_POUR_DG]->(dtg)

MERGE(rsncf: RegionSNCF {
nom: coalesce(gare.`Région SNCF`, "ND")
})
MERGE (g)-[:A_POUR_REGION_SNCF]->(rsncf);


DROP CONSTRAINT contrainte_gare_uic;
CREATE INDEX index_code_uic IF NOT EXISTS FOR (gare: Gare) ON (gare.codeUIC);
CREATE INDEX index_perdu_date IF NOT EXISTS FOR (objet: Objet) ON (objet.perduLe);
CREATE INDEX index_restitue_objet IF NOT EXISTS FOR (objet: Objet) ON (objet.restitue);

:auto USING PERIODIC COMMIT 500
LOAD CSV WITH HEADERS FROM 'file:///objets-trouves-restitution.csv' AS objet FIELDTERMINATOR ';'

CREATE (o: Objet {
natureObjet: objet.`Nature d'objets`,
perduLe : objet.Date,
restitue: (coalesce(objet.`Date et heure de restitution`, 'false')),
codeUIC: objet.codeUIC
})

MERGE(gare: Gare{
codeUIC: (coalesce(objet.`Code UIC`, '0000000000'))
})
MERGE (o)-[:TROUVE_A_GARE]->(gare)

MERGE(type: TypeObjet {
nom: objet.`Type d'objets`
})
MERGE (o)-[:EST_DE_TYPE]->(type)

:auto USING PERIODIC COMMIT 500
LOAD CSV WITH HEADERS FROM 'file:///Departements.csv' AS objet FIELDTERMINATOR ';'

CREATE (p: Population {
    nombre: toLong(objet.`PTOT`)
})

MERGE (d:Departement {
    code: objet.`CODDEP`
})
MERGE (d)-[:PEUPLE_PAR]->(p)