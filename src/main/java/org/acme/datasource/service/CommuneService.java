package org.acme.datasource.service;

import org.acme.datasource.entity.Commune;
import org.acme.datasource.utils.ResultUtils;
import org.neo4j.ogm.cypher.query.SortOrder;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class CommuneService {

    @Inject
    SessionFactory sessionFactory;

    public List<Map<String, Object>>  getCommuneAvecLePlusDeGare() {
        Session session = sessionFactory.openSession();
        Result result = session.query("MATCH (gare: Gare)-[:SE_SITUE_A]->(commune: Commune)\n"
                + "RETURN commune, count(gare) as nbGares\n"
                +"ORDER BY count(gare) DESC;", Map.of());

        return ResultUtils.feedListFromResult(result);
    }

    private List<Commune> resultList(Iterable<Commune> result) {
        ArrayList<Commune> communeArrayList = new ArrayList<>();
        result.forEach(communeArrayList::add);
        return communeArrayList;
    }

    public List<Map<String, Object>> getCommuneAvecLePlusDeGareByVille(String ville) {
        Session session = sessionFactory.openSession();
        Result result = session.query("MATCH (gare: Gare)-[:SE_SITUE_A]->(commune: Commune)\n"
                + "WHERE toLower(commune.nom) = toLower($ville) \n"
                + "RETURN commune, count(gare) as nbGares\n"
                +"ORDER BY count(gare) DESC;", Map.of("ville", ville));

        return ResultUtils.feedListFromResult(result);
    }

    public List<Map<String, Object>> getCommuneOrderByObjetTrouve() {
        Session session = sessionFactory.openSession();
        Result result = session.query("MATCH(o:Objet)-[tag:TROUVE_A_GARE]->(g:Gare)-[:SE_SITUE_A]->(c:Commune)\n" +
                "RETURN c, count(o) as nbObjet\n" +
                "ORDER BY nbObjet DESC", Map.of());
        return ResultUtils.feedListFromResult(result);
    }
}
