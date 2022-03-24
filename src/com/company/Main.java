package com.company;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        var num =numberOfPoints();
        var polygon = getPolygon(num);
        System.out.println("Enter target point: ");
        var target = getPoint();
        if (polygon != null && polygon.isConvex()) {
            var contain = polygon.containsRayCast(target);
            System.out.println(">Using Ray Cast algorithm, target is in polygon?" + (contain ? YesNo.YES:YesNo.NO));
            contain = polygon.containsWindingNumber(target);
            System.out.println(">Using Winding number algorithm, target je u polygon?" + (contain ? YesNo.YES:YesNo.NO));
            contain = polygon.get_vertices().contains(target.x, target.y);
            System.out.println(">Using java.awt.Polygon class, target is in polygon?" + (contain ? YesNo.YES:YesNo.NO));
        } else {
            System.out.println(">Incorrect entry");
        }
    }

    public static int numberOfPoints() {
        System.out.println("Enter number of points:");
        var input = scanner.nextLine();
        if (input == null || input.trim().isEmpty()) {
            return -1;
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static Polygon getPolygon (int numberOfPoints) {
        if (numberOfPoints < 3) {
            System.out.println("Polygon must contain at least 3 points");
            return null;
        }
        System.out.println("Enter point coordinates by x, y axis: ");
        System.out.println("(Format needs to be x,y and whole numbers)");
        var polygon = new Polygon();

        do {

            polygon.addPoint(getPoint());
        } while (polygon.getSizeOfPoints() < numberOfPoints);
            System.out.println("Coordinates of polygon: " + " " + polygon.readPolygon());

            return polygon;
    }

    public static Point getPoint () {

        Point point;

        do {
            var input = scanner.nextLine();
            if (input == null || input.trim().isEmpty() || !input.contains(",")) {
                System.out.println("Not correct format");
                continue;
            }
            Pattern pattern = Pattern.compile("\\d,\\d");
            Matcher matcher = pattern.matcher(input);
            boolean machFound = matcher.find();
            if (!machFound) {
                System.out.println("Not correct format");
                continue;
            }
            var arrayOfNumbers = input.split(",");
            var x = Integer.parseInt(arrayOfNumbers[0]);
            var y = Integer.parseInt(arrayOfNumbers[1]);
            point = new Point(x,y);
            break;

        } while (true);
            System.out.println("Point: " + " " + point.toString());

        return point;
    }




}


