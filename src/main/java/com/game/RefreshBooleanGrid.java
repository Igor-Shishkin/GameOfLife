package com.game;

public class RefreshBooleanGrid {
    private final boolean[][] booleanGrid;
    public RefreshBooleanGrid(boolean[][] booleanGrid) {
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
    public boolean willCellLive(int row, int column) {
        int i = countNeighbours(row, column);
        if (booleanGrid[row][column] && (i==2 || i==3)) { return true; }
        else return !booleanGrid[row][column] && i == 3;
    }
    public int countNeighbours(int row, int column) {
        int counter = 0;

        int startX = Math.max(0, column-1);
        int endX = Math.min(column+1, booleanGrid[0].length-1);
        int startY = Math.max(0, row-1);
        int endY = Math.min(row+1, booleanGrid.length-1);

        for (int j = startY; j <= endY; j++) {
            for (int i = startX; i <= endX; i++) {
                if  (j == row && i == column) { continue; }
                if (booleanGrid[j][i]) {
                    counter++; }
            }
        }
        return counter;
    }
}
