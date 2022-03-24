package com.company;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        var num =numberOfPoints();
        var polygon = getPolygon(num);
        System.out.println("Unesite target tacku: ");
        var target = getPoint();
        if (polygon != null) {
            var contain = polygon.contains(target);
            System.out.println(contain ? "Tacka se nalazi u poligonu" : "Tacka se nalazi izvan poligona");
        }
    }

    public static int numberOfPoints() {
        System.out.println("Unesite broj tacaka:");
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
            System.out.println("Poligon mora da sadrzi minimum 3 tacke");
            return null;
        }
        System.out.println("Unesite kordinate za tacke po x,y osi: ");
        System.out.println("(Format treba da bude x,y i celi brojevi)");
        var polygon = new Polygon();

        do {

            polygon.addPoint(getPoint());
        } while (polygon.getSizeOfPoints() < numberOfPoints);
            System.out.println("Kordinate poligona su sledece: " + " " + polygon.readPolygon());

            return polygon;
    }

    public static Point getPoint () {

        Point point;

        do {
            var input = scanner.nextLine();
            if (input == null || input.trim().isEmpty() || !input.contains(",")) {
                System.out.println("Niste uneli pravilan format");
                continue;
            }
            Pattern pattern = Pattern.compile("\\d,\\d");
            Matcher matcher = pattern.matcher(input);
            boolean machFound = matcher.find();
            if (!machFound) {
                System.out.println("Niste uneli pravilan format");
                continue;
            }
            var arrayOfNumbers = input.split(",");
            var x = Integer.parseInt(arrayOfNumbers[0]);
            var y = Integer.parseInt(arrayOfNumbers[1]);
            point = new Point(x,y);
            break;

        } while (true);
            System.out.println("Tacka je: " + " " + point.toString());

        return point;
    }


}


