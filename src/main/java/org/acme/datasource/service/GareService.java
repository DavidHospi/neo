package org.acme.datasource.service;

import org.acme.datasource.entity.Gare;
import org.acme.datasource.utils.ResultUtils;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class GareService {

    @Inject
    SessionFactory sessionFactory;

    private List<Gare> resultList(Iterable<Gare> result) {
        ArrayList<Gare> communeArrayList = new ArrayList<>();
        result.forEach(communeArrayList::add);
        return communeArrayList;
    }

    public List<Map<String, Object>> getGareOrderByNbObjetTrouve() {
        Session session = sessionFactory.openSession();

        Result result = session.query("MATCH (o:Objet)-[:TROUVE_A_GARE]->(g:Gare)\n"
                        + "MATCH (g)-[adpt:A_POUR_DPT]->(d:Departement)\n"
                        + "MATCH (g)-[apdrg:A_POUR_DRG]->(sdrg:SegmentDRG)\n"
                        + "MATCH (g)-[ssa:SE_SITUE_A]->(c:Commune)\n"
                        + "MATCH (g)-[aprg:A_POUR_RG]->(rg:RevisionGenerale)\n"
                        + "MATCH (g)-[apdg:A_POUR_DG]->(dg:DirectionGeneral)\n"
                        + "MATCH (g)-[aprsncf:A_POUR_REGION_SNCF]->(rsncf:RegionSNCF)\n"

                        + "RETURN  COUNT(o) as nbObjet, [{intitule:g.intitule, codeUIC:g.codeUIC, wgs84:g.wgs84," +
                        " Departement:[{code:d.code, nom:d.nom}], SegmentDRG:[{code:sdrg.code}]," +
                        " Commune:[{code:c.code,cp:c.cp,nom:c.nom}],RevisionGenerale:[{nom:rg.nom}]," +
                        " DirectionGenerale:[{nom:dg.nom}], RegionSNCF:[{nom:rsncf.nom}]}] as Gare\n"
                        +"ORDER BY nbObjet DESC"
               , Map.of());

        return ResultUtils.feedListFromResult(result);
    }


    public List<Gare> getGare() {
        Session session = sessionFactory.openSession();

        Iterable<Gare> result = session.query(Gare.class,"MATCH (o:Objet)-[:TROUVE_A_GARE]->(g:Gare)\n"
                        + "WITH count(o) as nbObjet\n"
                        + "MATCH (g)-[adpt:A_POUR_DPT]->(d:Departement)\n"
                        + "MATCH (g)-[apdrg:A_POUR_DRG]->(sdrg:SegmentDRG)\n"
                        + "MATCH (g)-[ssa:SE_SITUE_A]->(c:Commune)\n"
                        + "MATCH (g)-[aprg:A_POUR_RG]->(rg:RevisionGenerale)\n"
                        + "MATCH (g)-[apdg:A_POUR_DG]->(dg:DirectionGeneral)\n"
                        + "MATCH (g)-[aprsncf:A_POUR_REGION_SNCF]->(rsncf:RegionSNCF)\n"
                        + "RETURN nbObjet, g, collect(adpt),collect(d), collect(sdrg), collect(apdrg), collect(ssa), collect(c), collect(aprg), collect(rg)," +
                        "collect(apdg), collect(dg),collect(aprsncf), collect(rsncf), collect(nbObjet)\n"
                        +"ORDER BY nbObjet"
                , Collections.EMPTY_MAP);


        return this.resultList(result);
    }
}
