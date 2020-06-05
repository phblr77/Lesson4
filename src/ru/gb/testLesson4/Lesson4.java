package ru.gb.testLesson4;
import java.util.Random;
import java.util.Scanner;
//Hey here are some updates:)
public class Lesson4 {
    
    // Какими символами (фигками) играет игрок
    private static final char DOT_HUMAN = 'X';
    // Что вводит компьютер
    private static final char DOT_AI = 'O';
    // Символ пустой клетка
    private static final char DOT_EMPTY = ' ';
    private static final int WIN_NUMBER = 3;
    // Упрощения вместо использования field.length
    private static int fieldSizeX;
    private static int fieldSizeY;
    // Игровое поля
    private static char[][] field;

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();

    //main
    public static void main(String[] args) {
        int fieldSize = 3;
        while (true) {
            init(fieldSize);
            printField();
            playOnce(fieldSize);
            System.out.println("Играть сначала?");
            if (SCANNER.next().equals("no")) {
                break;
            }
        }
    }

    private static void playOnce(int fieldSize) {
        while (true) {
            humanTurn();
            printField();
            if (isWinnerExists(DOT_HUMAN)) {
                System.out.println("Победил Игрок!");
                break;
            }
            if (isDraw()) {
                System.out.println("Ничья!");
                break;
            }

            aiTurn();
            printField();
            if (isWinnerExists(DOT_AI)) {
                System.out.println("Победил Компьютер!");
                break;
            }
            if (isDraw()) {
                System.out.println("Ничья!");
                break;
            }

        }
    }

    //initField
    private static void init(int fieldSize) {
        fieldSizeX = fieldSize;
        fieldSizeY = fieldSize;

        field = new char[fieldSizeY][fieldSizeX];

        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = DOT_EMPTY;
            }
        }
    }

    //printField
    private static void printField() {
        System.out.println("=============");

        for (int y = 0; y < fieldSizeY; y++) {
            System.out.print("| ");
            for (int x = 0; x < fieldSizeX; x++) {
                System.out.print(field[y][x] + " | ");
            }
            System.out.println();
        }
    }

    //isValidField
    // Проврека, что координаты находятся в допустимом диапазоне
    private static boolean isValidField(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    //isNotEmptyField
    // Проврека, что поле занято (знак в поле не соответствует DOT_EMPTY);
    private static boolean isNotEmptyField(int x, int y) {
        return field[y][x] != DOT_EMPTY;
    }

    //humanTurn
    // Проврека, что поле занято (знак в поле не соответствует DOT_EMPTY);
    private static void humanTurn() {
        // 3 1
        int x;
        int y;
        do {
            System.out.print("Введите координаты хода X и Y (от 1 до " + fieldSizeY + ") через пробел >>> ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isValidField(x, y) || isNotEmptyField(x, y));
        field[y][x] = DOT_HUMAN;
    }

    //aiTurn
    private static void aiTurn() {
        int x;
        int y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (isNotEmptyField(x, y));
        field[y][x] = DOT_AI;
    }

    //isDraw
    private static boolean isDraw() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    //checkWin
    private static boolean isWinnerExists(char symb) {
        for (int i = 0; i < fieldSizeX; i++) {
            int counterX = 0;
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == symb) counterX++;
                if (counterX == WIN_NUMBER) return true;
            }
        }
        for (int i = 0; i < fieldSizeX; i++) {
            int counterY = 0;
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[j][i] == symb) counterY++;
                if (counterY == WIN_NUMBER) return true;
            }
        }
        for (int i = 0; i < fieldSizeX; i++) {
            int counterDiagonalLeft = 0;
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == symb) counterDiagonalLeft++;
                i++;
                if (counterDiagonalLeft == WIN_NUMBER) return true;
            }
        }
        for (int i = fieldSizeX - 1; i > 0; i--) {
            int counterDiagonalRight = 0;
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == symb) counterDiagonalRight++;
                i--;
                if (counterDiagonalRight == WIN_NUMBER) return true;
            }
        }
        return false;
    }


}
