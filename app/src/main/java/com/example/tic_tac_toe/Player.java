package com.example.tic_tac_toe;

import android.graphics.Point;

class Player {
    private Board board;

    Player(Board board){
        this.board=board;
    }
    Point getMove() {
        Point p = new Point();
        int bestValue = -1000;
        int alpha = -10000, beta = 10000;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                if (board.boxes[i][j] == 0) {
                    board.boxes[i][j] = 2;
                    int moveValue = alphabeta(alpha, beta, false);
                    if (moveValue >= bestValue) {
                        bestValue = moveValue;
                        p.x = i;
                        p.y = j;
                    }
                    board.boxes[i][j] = 0;
                }
            }
        return p;
    }

    private int alphabeta(int alpha, int beta,boolean isMax) {
        int k = evaluate(board);
        if (k == 1000 || k == -1000 || k == 0) return k;
        if (isMax) {
            int bestValue = -1000;
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++) {
                    if (board.boxes[i][j] == 0) {
                        board.boxes[i][j] = 2;
                        int moveValue = alphabeta(alpha, beta, false);
                        board.boxes[i][j] = 0;
                        bestValue = Math.max(bestValue, moveValue);
                        alpha = Math.max(alpha, bestValue);
                        if (beta <= alpha) break;
                    }
                }
            return bestValue;
        } else {
            int bestValue = 1000;
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++) {
                    if (board.boxes[i][j] == 0) {
                        board.boxes[i][j] = 1;
                        int moveValue = alphabeta(alpha, beta, true);
                        board.boxes[i][j] = 0;
                        bestValue = Math.min(bestValue, moveValue);
                        alpha = Math.min(beta, bestValue);
                        if (beta <= alpha) break;
                    }
                }
            return bestValue;
        }
    }

    private int evaluate(Board board) {
        int k = board.isGameCompleted();
        if (k == 0) return 0;
        if (k == 1) return -1000;
        if (k == 2) return 1000;
        return -1;
    }
}
