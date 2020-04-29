package pl.githubcom;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class lottoSimulator {
    public static void main(String[] args) {
        lotto();
    }

    public static void lotto() {
        Scanner scan = new Scanner(System.in);
        String[] inputArray;
        int[] numberArray = new int[6];
        boolean running = true;
        while (running) {
            System.out.println("Wprowadź 6 liczb");
            String input = scan.nextLine();
            inputArray = input.split(" ");
            if (arrayCheck(inputArray)) {
                numberArray = strToInt(inputArray);
                running = false;
            }
        }
        Arrays.sort(numberArray);
        int[] numbers = new int[49];
        int nextVal=1;
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = nextVal;
            nextVal++;
        }
        int used = 0;
        int[] lottoNumbers = new int[6];
        Random r = new Random();
        for (int i = 0; i < lottoNumbers.length; i++) {
            int index = r.nextInt(numbers.length - used);
            lottoNumbers[i] = numbers[index];
            numbers[index] = numbers[numbers.length - 1 - used];
            used++;
        }
        Arrays.sort(lottoNumbers);
        StringBuilder numbersBuilder = new StringBuilder();
        StringBuilder lottoBuilder = new StringBuilder();
        for (int liczby : numberArray) {
            numbersBuilder.append(liczby).append(" ");
        }
        System.out.println(numbersBuilder);
        for (int liczby : lottoNumbers) {
            lottoBuilder.append(liczby).append(" ");
        }
        System.out.println(lottoBuilder);
        int counter = winnerCheck(numberArray,lottoNumbers);
        if (counter==6){
            System.out.println("Gratulacje, trafiłeś 6!");
        } else if (counter==5){
            System.out.println("Gratulacje, trafiłeś 5!");
        } else if (counter==4){
            System.out.println("Gratulacje, trafiłeś 4");
        } else if (counter==3){
            System.out.println("Gratulacje, trafiłeś 3");
        }else {
            System.out.println("Niestety, nic nie trafiłeś");
        }

    }


    public static boolean arrayCheck(String[] inputArray) {
        if (inputArray.length != 6) {
            System.out.println("Niewłaściwa ilość liczb");
            return false;
        }
        for (int i = 0; i < inputArray.length; i++) {
            try {
                Integer.parseInt(inputArray[i]);
            } catch (NumberFormatException ex) {
                System.out.println("To nie jest liczba");
                return false;
            }
            if (Integer.parseInt(inputArray[i]) < 1 || Integer.parseInt(inputArray[i]) > 49) {
                System.out.println("Liczba spoza zakresu");
                return false;
            }
        }
        contains(inputArray);
        return true;
    }

    public static boolean contains(String[] inputArray) {
        for (int i = 0; i < inputArray.length - 1; i++) {
            for (int j = i + 1; j < inputArray.length; j++) {
                if (Integer.parseInt(inputArray[i]) == Integer.parseInt(inputArray[j])) {
                    System.out.println("Wartosci nie mogą sie powtarzać");
                    return false;
                }
            }
        }
        return true;
    }

    public static int[] strToInt(String[] inputArray) {
        int[] number = new int[6];
        for (int i = 0; i < inputArray.length; i++) {
            number[i] = Integer.parseInt(inputArray[i]);
        }
        return number;
    }

    public static int winnerCheck(int[] myArray, int[] randomArray) {
        int counter=0;
        for (int i = 0; i<myArray.length-1; i++){
            for (int j=i+1; j<randomArray.length;j++){
                if (myArray[i]==randomArray[j]){
                    counter++;
                }
            }
        }
        return counter;
    }
}