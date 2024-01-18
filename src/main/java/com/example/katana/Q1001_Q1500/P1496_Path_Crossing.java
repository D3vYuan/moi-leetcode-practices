package com.example.katana.Q1001_Q1500;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}

public class P1496_Path_Crossing {

    public boolean isPathCrossing(String path) {
        Map<Character, Coordinates> mappings = new HashMap<>();
        mappings.put('N', new Coordinates(0, 1));
        mappings.put('S', new Coordinates(0, -1));
        mappings.put('E', new Coordinates(1, 0));
        mappings.put('W', new Coordinates(-1, 0));

        Coordinates point = new Coordinates(0, 0);
        Set<Coordinates> coordinates = new HashSet<>();
        coordinates.add(point);

        for (int i = 0; i < path.length(); i++) {
            Character currentDirection = path.charAt(i);
            Coordinates nextDirection = mappings.get(currentDirection);
            Coordinates nextPoint = new Coordinates(point.getX() + nextDirection.getX(),
                    point.getY() + nextDirection.getY());
            if (coordinates.stream().filter(
                    coordinate -> coordinate.getX() == nextPoint.getX() && coordinate.getY() == nextPoint.getY())
                    .findFirst().isPresent()) {
                return true;
            }
            coordinates.add(nextPoint);
            point = nextPoint;
        }

        return false;
    }
}
