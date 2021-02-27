package org.acme.datasource.service;

import org.acme.datasource.utils.ResultUtils;
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
public class ObjetService {

    @Inject
    SessionFactory sessionFactory;

    public List<Map<String, Object>>  getObjetPerduLePlusParType() {
        Session session = sessionFactory.openSession();
        Result result = session.query("MATCH(n:TypeObjet)<-[:EST_DE_TYPE]-(o:Objet)-[tag:TROUVE_A_GARE]->(gare: Gare)\n" +
                "RETURN count(tag) as nbObjet, n.nom as Type\n" +
                "ORDER BY nbObjet DESC\n", Map.of());

        return ResultUtils.feedListFromResul(result);
    }
}
