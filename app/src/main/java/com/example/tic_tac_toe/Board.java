package com.example.tic_tac_toe;

class Board {
    int[][] boxes;

    Board() {
        boxes = new int[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                boxes[i][j] = 0;
    }

    int isGameCompleted() {
        for (int i = 0; i < 3; i++) {
            if (boxes[i][0] != 0 && boxes[i][0] == boxes[i][1] && boxes[i][0] == boxes[i][2]) {
                return boxes[i][0];
            } else if (boxes[0][i] != 0 && boxes[0][i] == boxes[1][i] && boxes[1][i] == boxes[2][i]) {
                return boxes[0][i];
            }
        }
        if (boxes[0][0] != 0 && boxes[0][0] == boxes[1][1] && boxes[1][1] == boxes[2][2]) {
            return boxes[0][0];
        } else if (boxes[2][0] != 0 && boxes[2][0] == boxes[1][1] && boxes[1][1] == boxes[0][2]) {
            return boxes[2][0];
        } else {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (boxes[i][j] == 0)
                        return -1;
        }
        return 0;
    }
}
