package pl.githubcom;

import java.util.Arrays;
import java.util.Random;

public class Cube {
    private static final int quantityPosition = 0;
    private static final int wallsPosition = 1;
    private static final int addPosition = 2;
    private static final int arraySize = 3;
    private static final int[] cubeTypes = {3, 4, 6, 8, 10, 12, 20, 100};

    public static void main(String[] args) {

        String param = args[0];
        System.out.println(param);
        try {
            System.out.println(result(param));
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
        }
    }

    public static int result(String param) {
        int[] operations = new int[arraySize];
        int result = 0;
        if (!param.contains("D")) {
            throw new IllegalArgumentException("Kod musi zawierać wskaźnik D");
        }
        int flag = 0;
        char[] parameters = param.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < parameters.length; i++) {
            if (Character.isDigit(parameters[i])) {
                builder.append(parameters[i]);
            } else if (parameters[i] == 'D' && flag == 0) {
                operations[quantityPosition] = setQuantity(builder);
                flag++;
                builder.setLength(0);
            } else if (parameters[i] == '+' && flag == 1) {
                operations[wallsPosition] = setWalls(builder);
                if (i == parameters.length - 1) {
                    throw new IllegalArgumentException("Błędne dane, za krótki kod");
                }
                builder.setLength(0);
                flag++;
            } else {
                throw new IllegalArgumentException("Błędne dane");
            }
            if (i == parameters.length - 1 && flag == 2) {
                operations[addPosition] = Integer.parseInt(builder.toString());
            } else if (i == parameters.length-1 && flag == 1) {
                operations[wallsPosition] = setWalls(builder);
            }

        }
        System.out.println(Arrays.toString(operations));
        Random r = new Random();
        for (int i = 1; i <= operations[quantityPosition]; i++) {
            result += 1 + r.nextInt(operations[wallsPosition]);
        }

        return result + operations[addPosition];
    }

    public static int setQuantity(StringBuilder builder) {
        if (builder.length() == 0) {
            return 1;
        } else if (Integer.parseInt(builder.toString()) > 99) {
            throw new IllegalArgumentException("Zbyt duża ilość rzutów");
        } else {
            return Integer.parseInt(builder.toString());
        }
    }

    public static int setWalls(StringBuilder builder) {

        if (builder.length() == 0) {
            throw new IllegalArgumentException("Nie podałeś liczby ścian kostki");
        } else {
            int walls = Integer.parseInt(builder.toString());
            if (Arrays.stream(cubeTypes).anyMatch(x -> x == walls)) {
                return walls;
            } else {
                throw new IllegalArgumentException("Niedozwolony rozmiar kostki");
            }
        }
    }
}