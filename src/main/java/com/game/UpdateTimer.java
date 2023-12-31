package com.game;

import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

public class UpdateTimer extends TimerTask {
    private final JPanel[][] cells;
    private final boolean[][] booleanGrid;

    public UpdateTimer(JPanel[][] cells, boolean[][] booleanGrid) {
        this.cells = cells;
        this.booleanGrid = booleanGrid;
    }

    @Override
    public void run() {
        RefreshBooleanGrid matrix = new RefreshBooleanGrid(booleanGrid);
        boolean[][] tempBooleanGrid = matrix.getNewBooleanGrid();

        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[row].length; column++) {
                booleanGrid[row][column] = tempBooleanGrid[row][column];
                Color colorForCell = (booleanGrid[row][column]) ? Color.RED : Color.WHITE;
                cells[row][column].setBackground(colorForCell);
            }
        }
    }
}
