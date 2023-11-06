package com.game;

public class Matrix {
    private final boolean[][] booleanGrid;
    public Matrix(boolean[][] booleanGrid) {
        this.booleanGrid = booleanGrid;
    }

    public boolean[][] getNewBooleanGrid() {
        boolean[][] newBooleanGrid = new boolean[booleanGrid.length][booleanGrid[0].length];

        for (int i = 0; i < booleanGrid.length; i++) {
            for (int j = 0; j < booleanGrid[i].length; j++) {
                newBooleanGrid[i][j] = willCellLive(i,j);
            }
        }
        return newBooleanGrid;
    }
    public boolean willCellLive(int x, int y) {
        int i = countNeighbours(x, y);
        return i == 3 || i == 2;
    }
    public int countNeighbours(int x, int y) {
        int counter = 0;

        int startX = (x>0) ? x-1 : x;
        int endX = (x+1 < booleanGrid.length) ? x+1 : x;
        int startY = (y>0) ? y-1 : y;
        int endY = (y+1 < booleanGrid[0].length) ? y+1 : y;

        for (int j = startX; j <= endX; j++) {
            for (int i = startY; i <= endY; i++) {
                if  (j == x && i == y) { continue; }
                if (booleanGrid[j][i]) { counter++; }
            }
        }
        return counter;
    }
}
