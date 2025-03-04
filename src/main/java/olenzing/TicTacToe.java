package olenzing;

import javax.swing.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String res = null;
        int xs = 0;
        int os = 0;

        // printing out an empty grid
        String emptyGridString = "_________";
        char[][] grid = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = emptyGridString.charAt(i * 3 + j);
            }
        }
        gridPrinter(grid);

        // starting the game

        System.out.println("Enter two numbers from 1 to 3 (e.g., 3 1):");

        boolean hasWinner = false;
        // taking turns one by one
        for (int k = 1; k <= 9; k++) {
            if (hasWinner) {
                break;
            }
            char symbol = (k % 2 != 0) ? 'X' : 'O';
            grid = gridStringUpdater(grid, symbol);
            gridPrinter(grid);


            // checking the rows for victory
            for (int i = 0; i < 3; i++) {
                int rowSum = 0;
                for (int j = 0; j < 3; j++) {
                    rowSum += grid[i][j];
                }
                if (rowSum == 264) {
                    res = "X wins";
                    hasWinner = true;
                    break;
                } else if (rowSum == 237) {
                    res = "O wins";
                    hasWinner = true;
                    break;
                }
            }
            if (hasWinner) break;

            // checking the columns for victory
            for (int i = 0; i < 3; i++) {
                int columnSum = 0;
                for (int j = 0; j < 3; j++) {
                    columnSum += grid[j][i];
                }
                if (columnSum == 264) {
                    res = "X wins";
                    hasWinner = true;
                    break;
                } else if (columnSum == 237) {
                    res = "O wins";
                    hasWinner = true;
                    break;
                }
            }
            if (hasWinner) break;

            // checking the diagonals left to right for victory

            int diagonalSumRight = 0;
            for (int i = 0; i < 3; i++) {
                if (grid[i][i] != '_') {
                    diagonalSumRight += grid[i][i];
                }
            }
            if (diagonalSumRight == 264) {
                res = "X wins";
                hasWinner = true;
                break;
            } else if (diagonalSumRight == 237) {
                res = "O wins";
                hasWinner = true;
                break;
            }

            // checking the diagonals right to left for victory

            int diagonaSumLeft = 0;
            for (int i = 0; i < 3; i++) {
                if (grid[i][2 - i] != '_') {
                    diagonaSumLeft += grid[i][2 - i];
                }
            }
            if (diagonaSumLeft == 264) {
                res = "X wins";
                hasWinner = true;
                break;
            } else if (diagonaSumLeft == 237) {
                res = "O wins";
                hasWinner = true;
                break;
            }
        }
        if (!hasWinner) {
            res = "Draw";
        }
        System.out.println(res);
        scanner.close();
    }

    static void gridPrinter(char[][] grid) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    static char[][] gridStringUpdater(char[][] grid, char newSymbol) {
        Scanner scanner = new Scanner(System.in);
        boolean valid = false;
        int num1 = 0;
        int num2 = 0;

        while (!valid) {
            String input = scanner.nextLine().trim();
            String[] tokens = input.split("\\s+");


            // Check that exactly two tokens were entered.
            if (tokens.length != 2) {
                System.out.println("You should enter numbers! ");
                continue;
            }

            try {
                num1 = Integer.parseInt(tokens[0]);
                num2 = Integer.parseInt(tokens[1]);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            // Check if the numbers are within the range 1 to 3.
            if (num1 < 1 || num1 > 3 || num2 < 1 || num2 > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            //checking if the cell is occupied
            if (grid[num1 - 1][num2 - 1] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            } else {
                valid = true;
                grid[num1 - 1][num2 - 1] = newSymbol;
            }
        }
        return grid;
    }
}
