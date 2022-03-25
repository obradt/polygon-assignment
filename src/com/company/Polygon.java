package com.company;

import java.util.ArrayList;
import java.util.List;

public class Polygon {

    private final List<Point> _vertices;

    public Polygon() {
        _vertices = new ArrayList<>();
    }

    public void addPoint(Point point) {
        _vertices.add(point);
    }

    public int getSizeOfPoints() {
        return _vertices.size();
    }

    public String readPolygon() {
        StringBuilder result = new StringBuilder();
        for (var point: _vertices) {
            result.append(point.toString());
        }
        return result.toString();
    }

    public boolean containsWindingNumber(Point target) {
        if (target == null || target.x <= 0 || target.y <= 0) {
            return false;
        }

        int winding = 0;
        for (int i = 0; i < _vertices.size(); i++) {
            if (_vertices.size() > i + 1) {
                int ccw = Point.ccw(_vertices.get(i), _vertices.get(i + 1), target);
                if (_vertices.get(i + 1).y > target.y && target.y >= _vertices.get(i).y) // upward crossing
                    if (ccw == +1)
                        winding++;
                if (_vertices.get(i + 1).y <= target.y && target.y < _vertices.get(i).y) // downward crossing
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
        for (i = 0, j = _vertices.size() - 1; i < _vertices.size(); j = i++) {
            if ((_vertices.get(i).y > test.y) != (_vertices.get(j).y > test.y) &&
                    (test.x < (_vertices.get(j).x - _vertices.get(i).x) * (test.y - _vertices.get(i).y)
                            / (_vertices.get(j).y- _vertices.get(i).y) + _vertices.get(i).x)) {
                result = !result;
            }
        }
        return result;
    }

    public java.awt.Polygon get_vertices() {
        java.awt.Polygon polygonAwt = new java.awt.Polygon();
        for (var point : _vertices) {
            polygonAwt.addPoint(point.x, point.y);
        }
        return polygonAwt;
    }

    public boolean isConvex()
    {

        if (_vertices.size() < 3)
            return false;

        if (_vertices.size() < 4)
            return true;

        boolean sign = false;
        int n = _vertices.size();

        for(int i = 0; i < n; i++)
        {
            double dx1 = _vertices.get((i + 2) % n).x - _vertices.get((i + 1) % n).x;
            double dy1 = _vertices.get((i + 2) % n).y - _vertices.get((i + 1) % n).y;
            double dx2 = _vertices.get(i).x - _vertices.get((i + 1) % n).x;
            double dy2 = _vertices.get(i).y - _vertices.get((i + 1) % n).y;
            double zcrossproduct = dx1 * dy2 - dy1 * dx2;

            if (i == 0)
                sign = zcrossproduct > 0;
            else if (sign != (zcrossproduct > 0))
                return false;
        }

        return true;
    }

    public boolean isTargetVertices(Point target) {
        for (var point:_vertices) {
            if (point.x == target.x && point.y == target.y){
                return true;
            }
        }
        return false;
    }

}
