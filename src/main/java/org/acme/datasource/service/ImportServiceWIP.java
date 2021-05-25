package org.acme.datasource.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

@ApplicationScoped
public class ImportServiceWIP {

    @Inject
    SessionFactory sessionFactory;

    public Object importData() {

        StringBuilder sb = new StringBuilder("");
        try {
            File file = new File("../../../../resources/import.cypher");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                sb.append(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Response.status(500).build();
        }

        // C:\Users\Clair\Desktop\LPIOT\NoSQL\neo\src\main\java\org\acme\datasource\service\ImportService.java
        // C:\Users\Clair\Desktop\LPIOT\NoSQL\neo\src\main\resources\import.cypher

        String[] runs = sb.toString().split("// CUT");

        Session session = sessionFactory.openSession();
        for(String s : runs) {
            String[] tmp = s.split(";");
            for(String s2 : tmp) {
                Result result = session.query(s2, Map.of());
            }
        }

        return Response.ok().build();
    }
    
}
