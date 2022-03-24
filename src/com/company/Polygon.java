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

    public boolean containsWindingNumber(Point target) {
        if (target == null || target.x <= 0 || target.y <= 0) {
            return false;
        }

        int winding = 0;
        for (int i = 0; i < polygon.size(); i++) {
            if (polygon.size() > i + 1) {
                int ccw = Point.ccw(polygon.get(i), polygon.get(i + 1), target);
                if (polygon.get(i + 1).y > target.y && target.y >= polygon.get(i).y) // upward crossing
                    if (ccw == +1)
                        winding++;
                if (polygon.get(i + 1).y <= target.y && target.y < polygon.get(i).y) // downward crossing
                    if (ccw == -1)
                        winding--;
            }
        }
        return winding != 0;
    }

    public boolean containsRayCast(Point test) {
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

    public java.awt.Polygon getPolygon() {
        java.awt.Polygon polygonAwt = new java.awt.Polygon();
        for (var point :polygon) {
            polygonAwt.addPoint(point.x, point.y);
        }
        return polygonAwt;
    }

}
