package org.io;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class thema_pane {
    public static void main(String[] args) {
        int countPerson = 0;
        List<List<Double>> allDoubles = new ArrayList<>();
        List<String> allNames = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter your full name: ");
            String fullName = scanner.nextLine();
            if (fullName.equals("END")) {
                scanner.close();
                if (countPerson < 2) {
                    System.out.println("You didn't enter at least two persons");
                    System.exit(0);
                }
                break;
            }
            if (!fullName.matches("[a-zA-Z]+\\s+[a-zA-Z]+")) {
                System.out.println("You must give a full name input without any special characters");
                System.exit(0);
            }
            String[] nameParts = fullName.split("\\s+");
            String firstName = nameParts[0];
            String lastName = nameParts[1];
            System.out.println("Full name detected.");
            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);
            allNames.add(fullName);
            countPerson++;
            List<Double> doublesList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                System.out.print("Enter a mark for lesson " + (i + 1) + ": ");
                double value = scanner.nextDouble();
                if (value < 1 || value > 20) {
                    System.out.println("Invalid input. Please enter a value between 1 and 20.");
                    System.exit(0);
                }
                doublesList.add(value);
            }
            scanner.nextLine(); // Clear the newline character
            allDoubles.add(doublesList);
        }
        printList(allNames, allDoubles);
    }
    public static void printList(List<String> names, List<List<Double>> data) {
        System.out.println("Printing everyone's average:");
        List<String> qualifiedNames = new ArrayList<>();
        double maxAverage = Double.MIN_VALUE;
        String highestName = "";
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            List<Double> marks = data.get(i);
            double average = marks.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            System.out.println("Full Name: " + name + "\tAverage: " + average);
            if (average > 15 && marks.stream().anyMatch(mark -> mark >= 12)) {
                qualifiedNames.add(name);
                if (average > maxAverage) {
                    maxAverage = average;
                    highestName = name;
                }
            }
        }
        if (!qualifiedNames.isEmpty()) {
            System.out.println("\nPrinting those who have average > 15 and at least one mark >= 12:");
            for (String name : qualifiedNames) {
                List<Double> marks = data.get(names.indexOf(name));
                double average = marks.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
                System.out.println("Full Name: " + name + "\tAverage: " + average);
            }
            System.out.println("\nIndividual with the highest average among those who meet the conditions:");
            System.out.println("Full Name: " + highestName + "\tAverage: " + maxAverage);
        } else {
            System.out.println("No one passed the exams");
        }
        double percentagePassed = (double) qualifiedNames.size() / names.size() * 100;
        System.out.println("\nPercentage of people who passed the conditions: " + percentagePassed + "%");
    }
}