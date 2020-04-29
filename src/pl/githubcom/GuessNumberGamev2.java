package pl.githubcom;

import java.util.Random;
import java.util.Scanner;

public class GuessNumberGamev2 {
    public static void main(String[] args) {
        guessNumber();
    }

    public static void guessNumber() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Zapisz swoja liczbę");
        while (!scan.hasNextInt()) {
            String input = scan.nextLine();
            System.out.println("To nie jest liczba");
        }
        int userNumber = scan.nextInt();
        System.out.println("Twoja liczba to: " + userNumber);
        Random r = new Random();
        int lowerBound = 1;
        int upperBound = 100;
        int counter = 0;
        while (true) {
            int randomLiczba = lowerBound + r.nextInt(upperBound);
            System.out.println("Czy Twoja liczba to " + randomLiczba + " ?");
            System.out.println("Jeśli tak, naciśnij 0\nMniej, naciśnij 1\nWięcej, naciśnij 2");
            while (!scan.hasNextInt()) {
                String input = scan.nextLine();
                System.out.println("To nie jest liczba");
            }
            int userChoice = scan.nextInt();
            counter++;
            if (userChoice == 0) {
                System.out.println("Zgadłem!");
                break;
            } else if (userChoice == 1) {
                upperBound = randomLiczba - lowerBound;
            } else if ((userChoice == 2)) {
                upperBound = upperBound - (randomLiczba - lowerBound + 1);
                lowerBound = randomLiczba + 1;
            }
            if (counter == 10) {
                System.out.println("Przegrałem, za dużo prób");
                break;
            }
        }
    }
}
