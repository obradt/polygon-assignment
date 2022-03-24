package com.company;

import java.util.ArrayList;
import java.util.List;

public class Polygon {

    private final List<Point> polygon;

    public Polygon() {
        polygon = new ArrayList<>();
    }

    public void addPoint(Point point) {
        polygon.add(point);
    }

    public int getSizeOfPoints() {
        return polygon.size();
    }

    public String readPolygon() {
        StringBuilder result = new StringBuilder();
        for (var point:polygon) {
            result.append(point.toString());
        }
        return result.toString();
    }

    public boolean contains(Point test) {
        if (test == null || test.x < 0 || test.y < 0) {
            return false;
        }
        int i;
        int j;
        boolean result = false;
        for (i = 0, j = polygon.size() - 1; i < polygon.size(); j = i++) {
            if ((polygon.get(i).y > test.y) != (polygon.get(j).y > test.y) &&
                    (test.x < (polygon.get(j).x - polygon.get(i).x) * (test.y - polygon.get(i).y)
                            / (polygon.get(j).y-polygon.get(i).y) + polygon.get(i).x)) {
                result = !result;
            }
        }
        return result;
    }
}
