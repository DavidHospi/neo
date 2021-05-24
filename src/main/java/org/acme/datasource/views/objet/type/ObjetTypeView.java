package org.acme.datasource.views.objet.type;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.model.Result;

import lombok.AllArgsConstructor;
import lombok.Data;

@NodeEntity
@Data
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ObjetTypeView {
    
    String type;
    Long nombre;
    Double percentPop;

    public static List<ObjetTypeView> map(Result result) {
        List<ObjetTypeView> listTest = new ArrayList<>();
        result.queryResults().forEach(queryRes ->{
            listTest.add(new ObjetTypeView((String) queryRes.get("type"), (Long) queryRes.get("nb"), null));
        });
        return listTest.stream()
            .sorted(Comparator.comparingLong(ObjetTypeView::getNombre)
                .reversed())
            .collect(Collectors.toList());
    }

}
