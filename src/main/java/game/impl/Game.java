package game.impl;

import game.Startable;
import lombok.AllArgsConstructor;
import utils.SecretUtils;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

@AllArgsConstructor
public class Game implements Startable {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int EXIT = 0;

    private final List<String> entities;

    @Override
    public void start() {
        int computerMove = getComputerMove(entities.size());
        String key = SecretUtils.getSecretKey();
        String hmac = SecretUtils.getHmac(key, entities.get(computerMove));
        System.out.println("HMAC: " + hmac);
        printMenu();
        int userMove = getUserMove();
        if (isExit(userMove)) return;
        printResults(computerMove, userMove - 1);
        System.out.println("HMAC key: " + key);
    }

    private void printMenu() {
        System.out.println("Available moves:");
        entities.forEach(entity -> System.out.println(entities.indexOf(entity) + 1 + " - " + entity));
        System.out.println(EXIT + " - exit");
    }

    private int getUserMove() {
        int userMove = 0;
        do {
            System.out.print("Enter your move: ");
            if (SCANNER.hasNextInt()) {
                userMove = SCANNER.nextInt();
            }
        } while (userMove < 0 || userMove > entities.size());
        return userMove;
    }

    private int getComputerMove(int range) {
        return (int) (Math.random() * ((range)));
    }

    private int calculateResult(int computerMove, int userMove) {
        int half = entities.size() / 2;
        boolean isUserWin = IntStream.rangeClosed(computerMove, computerMove + half)
                .map(i -> i % entities.size())
                .anyMatch(i -> i == userMove);
        return isUserWin ? userMove : computerMove;
    }

    private boolean isExit(int choise) {
        return choise == EXIT;
    }

    private void printResults(int computerMove, int userMove) {
        System.out.println("Your move: " + entities.get(userMove));
        System.out.println("Computer move: " + entities.get(computerMove));
        if (computerMove == userMove) {
            System.out.println("Draw!");
        } else {
            int winResult = calculateResult(computerMove, userMove);
            String resultMessage = userMove == winResult ? "You win!" : "Computer win!";
            System.out.println(resultMessage);
        }
    }
}
