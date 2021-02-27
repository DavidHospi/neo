package org.acme.datasource.service;


import org.neo4j.ogm.session.SessionFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class SegmentDRGService {

    @Inject
    SessionFactory sessionFactory;

    public List<Map<String, Object>> getSegmentDRGAvecLePlusDeGare() {
            return null;
    }
}
