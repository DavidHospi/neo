package org.acme.datasource.service;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.acme.datasource.utils.ResultUtils;
import org.acme.datasource.views.objet.type.ObjetTypeView;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

@ApplicationScoped
public class ObjetService {

    @Inject
    SessionFactory sessionFactory;
    
    public Object getAll() {
        Session session = sessionFactory.openSession();
        Result result = session.query("MATCH(o:Objet) RETURN o"
            , Map.of());
        return ResultUtils.feedListFromResult(result);    
    }

    /******************** Recherches suivant le type ********************/

    public List<ObjetTypeView> getObjetPerduLePlusParType(boolean populationAdjusted) {
        Session session = sessionFactory.openSession();
        Result result = session.query(
            "MATCH(n:TypeObjet)<-[:EST_DE_TYPE]-(o:Objet)-[tag:TROUVE_A_GARE]->(gare: Gare)\n" +
            "RETURN count(o) as nb, n.nom as type\n" +
            "ORDER BY nb DESC\n", Map.of());
        if(!populationAdjusted) return ObjetTypeView.map(result);
        List<ObjetTypeView> tmp =  ObjetTypeView.map(result);    
        Result population = session.query(
            "match (d:Departement)-[:PEUPLE_PAR]->(p:Population) return sum(p.nombre) as res", Map.of());
        long totalPop = 0l;
        for(Map<String, Object> entry : population.queryResults()) {
            totalPop += (Long) entry.get("res");
        }
        final long totalPopFinal = totalPop;
        if(totalPop != 0) {
            tmp.stream().forEach(data -> {
                data.setPercentPop((double) data.getNombre() / totalPopFinal);
            });
        }
        return tmp;
    }

    public List<ObjetTypeView> getObjetPerduLePlusParTypeParCommune(Long codePostal) {
        Session session = sessionFactory.openSession();
        Result result = session.query("MATCH(n:TypeObjet)<-[:EST_DE_TYPE]-(o:Objet)-[:TROUVE_A_GARE]->(gare: Gare)-[:SE_SITUE_A]->(c:Commune)" + 
        " where c.cp = " + codePostal + 
        " RETURN n.nom as type, count(o) as nb, c.nom as commune order by count(o) DESC" , Map.of());
        return ObjetTypeView.map(result);
    }

    public List<ObjetTypeView> getObjetPerduLePlusParTypeParDepartement(boolean populationAdjusted, String dpt) {
        Session session = sessionFactory.openSession();
        Result result = session.query("MATCH(n:TypeObjet)<-[:EST_DE_TYPE]-(o:Objet)-[:TROUVE_A_GARE]->(gare: Gare)-[:A_POUR_DPT]->(d:Departement)" + 
        " where d.code = \"" + dpt + "\"" +  
        " RETURN n.nom as type, count(o) as nb, d.nom as departement order by count(o) DESC" , Map.of());
        if(!populationAdjusted) return ObjetTypeView.map(result);
        List<ObjetTypeView> tmp = ObjetTypeView.map(result);
        Result population = session.query(
            "match (d:Departement)-[:PEUPLE_PAR]->(p:Population) where d.code = \"" + dpt + "\" return sum(p.nombre) as res ", Map.of());
            long totalPop = 0l;
            for(Map<String, Object> entry : population.queryResults()) {
                totalPop += (Long) entry.get("res");
            }
        for(ObjetTypeView otv : tmp) {
            if(totalPop != 0) {
                otv.setPercentPop((double) otv.getNombre() / totalPop);          
            }
        }
        return tmp;
    }

    /******************** Recherches suivant la restitution ********************/

    public List<Map<String, Object>> getNbObjetRestitue() {
        Session session = sessionFactory.openSession();
        Result result = session.query("MATCH(o:Objet)\n" +
                "WHERE o.restitue <> 'false'\n" +
                "RETURN count(o) as `Nombre d'objet restitué`\n"
                , Map.of());

        return ResultUtils.feedListFromResult(result);
    }

    public List<Map<String, Object>> getNbObjetNonRestitue() {
        Session session = sessionFactory.openSession();
        Result result = session.query("MATCH(o:Objet)\n" +
                        "WHERE o.restitue = 'false'\n" +
                        "RETURN count(o) as `Nombre d'objet non récupéré`\n"
                , Map.of());

        return ResultUtils.feedListFromResult(result);
    }
}
