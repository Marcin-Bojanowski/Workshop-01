package pl.githubcom;

import java.util.Random;
import java.util.Scanner;

public class GuessNumberGame {
    public static void main(String[] args) {
        guess();
    }

    public static void guess() {
        Random r = new Random();
        int randomLiczba = 1 + r.nextInt(100);

        Scanner scan = new Scanner(System.in);
        int liczba = 0;
        while (true) {
                System.out.println("Zgadnij liczbę");
                while (!scan.hasNextInt()) {
                    String input=scan.nextLine();
                    System.out.println("To nie jest liczba");
                }
                liczba = scan.nextInt();
                if (liczba == randomLiczba) {
                    System.out.println("Gratulacje, zgadłeś");
                    break;
                } else if (liczba < randomLiczba) {
                    System.out.println("za mało");
                } else {
                    System.out.println("Za dużo");
                }
            }
        }
    }
