package org.acme.datasource.service;

import org.acme.datasource.entity.Commune;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class CommuneService {

    @Inject
    SessionFactory sessionFactory;

    public List<Commune> getCommuneAvecLePlusDeGare() {
        Session session = sessionFactory.openSession();
        Iterable<Commune> result = session.query(Commune.class, "MATCH (gare: Gare)-[:SE_SITUE_A]->(commune: Commune)\n"
                + "RETURN commune, count(gare) as nbGares\n"
                +"ORDER BY count(gare) DESC;", Map.of());
        return resultList(result);
    }

    private List<Commune> resultList(Iterable<Commune> result) {
        ArrayList<Commune> communeArrayList = new ArrayList<>();
        result.forEach(communeArrayList::add);
        return communeArrayList;
    }
}
