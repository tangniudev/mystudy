package com.sensors.mystudy.chatGpt;

import java.util.Scanner;

public class ChessGame {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;
        while (!gameOver) {
            board.printBoard();
            System.out.print("Enter start position (e.g. a2): ");
            String start = scanner.nextLine();
            System.out.print("Enter end position (e.g. a4): ");
            String end = scanner.nextLine();
            int startX = start.charAt(0) - 'a';
            int startY = start.charAt(1) - '1';
            int endX = end.charAt(0) - 'a';
            int endY = end.charAt(1) - '1';
            board.movePiece(startX, startY, endX, endY);
        }
    }
}
