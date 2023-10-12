package com.sensors.mystudy.chatGpt;

public class ChessBoard {
    private ChessPiece[][] board;

    public ChessBoard() {
        board = new ChessPiece[8][8];
        // 初始化棋盘
        board[0][0] = new ChessPiece("black", "rook");
        board[0][1] = new ChessPiece("black", "knight");
        board[0][2] = new ChessPiece("black", "bishop");
        board[0][3] = new ChessPiece("black", "queen");
        board[0][4] = new ChessPiece("black", "king");
        board[0][5] = new ChessPiece("black", "bishop");
        board[0][6] = new ChessPiece("black", "knight");
        board[0][7] = new ChessPiece("black", "rook");
        for (int i = 0; i < 8; i++) {
            board[1][i] = new ChessPiece("black", "pawn");
        }
    }

    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].getType() + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    public void movePiece(int startX, int startY, int endX, int endY) {
        ChessPiece piece = board[startX][startY];
        board[startX][startY] = null;
        board[endX][endY] = piece;
    }
}
