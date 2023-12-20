package com.example.katana;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P1436_Destination_City {
    public String destCity(List<List<String>> paths) {
        Map<String, Integer> destinations = new HashMap<>();
        List<String> sources = new ArrayList<>();

        for (List<String> path : paths) {
            String source = path.get(0);
            String destination = path.get(1);
            destinations.put(source, destinations.getOrDefault(source, 0) + 1);
            destinations.put(destination, destinations.getOrDefault(destination, 0) + 1);
            sources.add(source);
        }

        return destinations.entrySet().stream().filter(entry -> entry.getValue() == 1)
                .filter(entry -> !sources.contains(entry.getKey())).findFirst().get().getKey();
    }
}
