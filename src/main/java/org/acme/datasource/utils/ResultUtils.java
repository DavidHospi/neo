package org.acme.datasource.utils;

import org.neo4j.ogm.model.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultUtils {


    public static List<Map<String, Object>> feedListFromResul(Result result) {
        List<Map<String, Object>> list = new ArrayList<>();

        result.queryResults().forEach(queryRes ->{
            queryRes.entrySet().forEach(entry -> {
                Map<String, Object> map = new HashMap<>();
                map.put(entry.getKey(), entry.getValue());
                list.add(map);
            });
        });

        return list;
    }
}
